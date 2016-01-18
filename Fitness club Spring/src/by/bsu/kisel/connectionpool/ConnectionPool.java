package by.bsu.kisel.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import by.bsu.kisel.constants.ErrorConstants;
import by.bsu.kisel.constants.MessageConstants;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyTechnicalException;
import by.bsu.kisel.exception.ResourceCreationException;
import by.bsu.kisel.manager.DatabaseManager;

/**
 * This class implements Connection Pool.
 * 
 * @author Anastasiya Kisel
 */
@Component
public class ConnectionPool {

	private static Logger logger = Logger.getLogger(ConnectionPool.class);

	private final static int MAX_RESOURCES = 15;

	private final Semaphore sem = new Semaphore(MAX_RESOURCES, true);

	private final Queue<Connection> resources = new ConcurrentLinkedQueue<Connection>();

	private static ConnectionPool poolInstance = null;

	/**
	 * Constructs Connection Pool instance with prefilled queue of DB
	 * connections.
	 * 
	 * @throws DAOSQLException
	 */
	private ConnectionPool() throws DAOSQLException {
		try {
			Class.forName(DatabaseManager.getInstance().getProperty()
					.getDriver());
			for (int i = 0; i < MAX_RESOURCES; i++) {
				try {
					Connection connection = null;
					connection = DriverManager.getConnection(DatabaseManager
							.getInstance().getProperty().getUrl(),
							DatabaseManager.getInstance().getProperty()
									.getUser(), DatabaseManager.getInstance()
									.getProperty().getPassword());
					resources.add(connection);
				} catch (SQLException ex) {
					throw new DAOSQLException(ex.getMessage());
				}
			}
		} catch (MyTechnicalException ex) {
			logger.error(ErrorConstants.LOGGER_TECHNICAL_ERROR);
		} catch (ClassNotFoundException ex) {
			logger.error(ErrorConstants.LOGGER_CLASS_NOT_FOUND);
		}
	}

	/**
	 * Acquires Connection Pool instance.
	 * 
	 * @return Connection Pool instance
	 * @throws DAOSQLException
	 */
	public static synchronized ConnectionPool getInstance()
			throws DAOSQLException {
		if (poolInstance == null) {
			poolInstance = new ConnectionPool();
		}
		return poolInstance;
	}

	/**
	 * Acquires DB Connection.
	 * 
	 * @param maxWaitMillis - max time in milliseconds to wait for a DB connection
	 * @return DB Connection instance
	 * @throws ResourceCreationException
	 */
	public Connection getConnection(long maxWaitMillis)
			throws ResourceCreationException {
		try {
			// First, get permission to take or create a resource
			sem.tryAcquire(maxWaitMillis, TimeUnit.MILLISECONDS);
		} catch (InterruptedException ex) {
			logger.error(ex.getMessage());
		}

		// Then, actually take one if available...
		Connection res = resources.poll();
		if (res == null) {
			throw new ResourceCreationException(
					MessageConstants.ERROR_MESSAGE_POOL_IS_EMPTY);
		}
		return res;
	}

	/**
	 * Releases the DB connection back to the Connection Pool.
	 * 
	 * @param res - DB connection
	 */
	public void releaseConnection(Connection res) {
		resources.add(res);
		sem.release();
	}

	/**
	 * Releases Connection Pool instance - removes the queue of DB connections.
	 */
	public void releasePool() {
		while (!resources.isEmpty())
			resources.remove();
	}

}
