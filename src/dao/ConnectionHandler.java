package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHandler {
	
	private final static String URL = "jdbc:mysql://localhost:3306/instabook";
	private final static String USERNAME = "root";
	private final static String PASSWORD = "root";
	private static Connection connection;
	
	public static Connection getConnection() {
		try {
			if (connection == null) {
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				System.out.println("Connection successful!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

}
