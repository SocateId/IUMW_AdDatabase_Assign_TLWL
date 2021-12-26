package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JavaMySQLTest {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/uptown_books";
		String username = "root";
		String password = "";
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			
			System.out.println("Connected to Database!");
			
			
		} catch (SQLException e) {
			System.out.println("!Connection Error!");
			e.printStackTrace();
		}
	}
}
