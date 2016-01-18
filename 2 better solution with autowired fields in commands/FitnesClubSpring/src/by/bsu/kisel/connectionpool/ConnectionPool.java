/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.connectionpool;

import by.bsu.kisel.constants.LoggerConstants;
import by.bsu.kisel.controller.Controller;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyTechnicalException;
import by.bsu.kisel.exception.ResourceCreationException;
import by.bsu.kisel.manager.DatabaseManager;
import by.bsu.kisel.manager.MessageManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 *
 * @author Kisel Anastasia
 */
@Component
public class ConnectionPool {
  
  private static Logger log = Logger.getLogger(ConnectionPool.class);
  
  private final static int MAX_RESOURCES=15;
  
  private final Semaphore sem =new Semaphore(MAX_RESOURCES, true);
  
  //FIFO -queue with nonfixed length
  private final Queue<Connection> resources =new ConcurrentLinkedQueue<Connection>();
  
  private static ConnectionPool poolInstance = null;
  /**
  * constructor
  */
  private ConnectionPool() throws DAOSQLException{
        try {
            Class.forName(DatabaseManager.getInstance().getProperty().getDriver());
            for (int i=0; i<MAX_RESOURCES; i++){
                try {
                	Connection connection = null;
                	connection = DriverManager.getConnection(
                            DatabaseManager.getInstance().getProperty().getUrl(), 
                            DatabaseManager.getInstance().getProperty().getUser(),
                            DatabaseManager.getInstance().getProperty().getPassword());
                    resources.add(connection);
                } catch (SQLException ex) {
                    throw new DAOSQLException(ex.getMessage());
                }
            }
        } catch (MyTechnicalException ex) {
            log.error(LoggerConstants.LOGGER_TECHNICAL_ERROR);
        } catch (ClassNotFoundException ex) {
            log.error(LoggerConstants.LOGGER_CLASS_NOT_FOUND);
        }
  }
   /**
   * @return instance
   */
  public static synchronized ConnectionPool getInstance() throws DAOSQLException {
      if (poolInstance==null){
          poolInstance=new ConnectionPool();
      }
      return poolInstance;
  }
  /**
  * @return connection from the pool
  *@param maxWaitMillis 
  */
  public Connection getConnection(long maxWaitMillis) throws ResourceCreationException {
   try {
      // First, get permission to take or create a resource
      sem.tryAcquire(maxWaitMillis, TimeUnit.MILLISECONDS);
    } catch (InterruptedException ex) {
        log.error(ex.getMessage());
    }

    // Then, actually take one if available...
    Connection res = resources.poll();
    if (res == null){
       throw new ResourceCreationException(Controller.MSG_MANAGER.getProperty
              (MessageManager.ERROR_MESSAGE_POOL_IS_EMPTY));
       }
    return res;    
  }
  
  /**
  * return the connection to the pool
  * @param res
  */
  public void releaseConnection(Connection res) {
    resources.add(res);
    sem.release();
  }
  /**
  * release the connection pool
  */
  public void releasePool(){
      while(!resources.isEmpty())
          resources.remove();
  }
  
}
