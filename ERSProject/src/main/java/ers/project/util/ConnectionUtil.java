package ers.project.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	// Create instances for cleaner try / catch 
	private static String db_url = System.getenv("db_url");
	private static String db_username = System.getenv("db_username");
	private static String db_passwrd = System.getenv("db_passwrd");
	
	private static Connection conn = null;
	
	public static Connection getNewConnection() throws SQLException {
		
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			conn = DriverManager.getConnection(db_url, db_username, db_passwrd);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	
	
	
}