package by.bsu.kisel.util;

import java.sql.SQLException;
import java.sql.Statement;

import by.bsu.kisel.constants.MessageConstants;
import by.bsu.kisel.exception.DAOSQLException;

/**
 * Util class for JDBC related operations.
 * 
 * @author Anastasiya Kisel
 * 
 */
public class JdbcUtil {
	/**
	 * Closes the SQL statement.
	 * 
	 * @param st
	 *            - statement
	 * @throws DAOSQLException
	 */
	public static void closeStatement(Statement st) throws DAOSQLException  {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException ex) {
				throw new DAOSQLException(MessageConstants.SQLCONNECTION_ERROR_MESSAGE,
						ex);
			}
		}
	}

}