package by.bsu.kisel.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.bsu.kisel.connectionpool.ConnectionPool;
/**
 * Parent of all DAO JDBC classes in the application.
 * @author Anastasiya Kisel
 *
 */
@Component
public class DAOJdbc {
	
	public static final int MAX_CONNECTION_WAIT=1000;
	
	protected ConnectionPool connectionPool;
	/**
	 * Sets connection po
	 * @param connectionPool - connection pool instance
	 */
	@Autowired
	public void setConnectionPool(ConnectionPool connectionPool) {
		this.connectionPool = connectionPool;
	}	
	
}
