package by.bsu.kisel.util;

import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import by.bsu.kisel.controller.Controller;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.manager.MessageManager;

public class JdbcUtil {
	private final static Logger log = Logger.getLogger(JdbcUtil.class);

	/**
	 * close PreparedStement
	 * 
	 * @param st
	 */
	public static void closeStatement(Statement st) throws DAOSQLException {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException ex) {
				throw new DAOSQLException(
						Controller.MSG_MANAGER.getProperty(MessageManager.DAO_ERROR_MESSAGE), ex);
			}
		}
	}
	
}
