package persistence.beans;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	
	public static Connection getConnection() throws Exception
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "hello", "hello1234");
		return conn1;
	}
}
