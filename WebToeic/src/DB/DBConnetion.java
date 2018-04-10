package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnetion {
	
	 public static Connection getSQLServerConnection()
	         throws SQLException, ClassNotFoundException {
	     String hostName = "localhost";
	     String sqlInstanceName = "SQLEXPRESS";
	     String database = "projectwebtoeic";
	     String userName = "sa";
	     String password = "123456789";
	 
	     return getSQLServerConnection(hostName, sqlInstanceName,
	             database, userName, password);
	 }
	 

	 public static Connection getSQLServerConnection(String hostName,
	         String sqlInstanceName, String database, String userName,
	         String password) throws ClassNotFoundException, SQLException {
		 
		 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		 String connectionURL = "jdbc:sqlserver://" + hostName + ":1433"
	             + ";instance=" + sqlInstanceName + ";databaseName=" + database;
		 
		/* String connectionURL = "jdbc:sqlserver://" + hostName + ":1433"
	             +";databaseName=" + database;*/
		 
		/* String connectionURL = "jdbc:sqlserver://localhost;databaseName=" + database + ";user=sa;password=123456789";*/
	 
	     Connection conn = DriverManager.getConnection(connectionURL, userName,
	             password);
	     if(conn!=null)System.out.println("Connection is OK");
	     else System.out.println("Cannot connect database");
	     return conn;
	 }

}
