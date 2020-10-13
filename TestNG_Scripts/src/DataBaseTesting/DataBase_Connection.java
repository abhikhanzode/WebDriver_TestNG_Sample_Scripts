package DataBaseTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.testng.annotations.Test;


public class DataBase_Connection {
	public static Connection connection;
	@Test
	public void Connection_1() {
		String databaseURL = "jdbc:mysql://localhost:3306/";
		String user = "root";
		String password = "root";
		connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to Database...");
			connection = DriverManager.getConnection(databaseURL, user, password);
			if (connection != null) {
				System.out.println("Connected to the Database...");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
}
