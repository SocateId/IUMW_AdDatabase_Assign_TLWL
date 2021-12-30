package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JavaMySQLTest {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/uptown_books";
		String username = "root";
		String password = "";
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			
			System.out.println("Connected to Database!");
			
			// Query Object
			Statement query = connection.createStatement();
			ResultSet test;
			// Query to be sent
			String sql;
			
			sql = "SELECT name FROM employees WHERE store_number = '0'";
			test = query.executeQuery(sql);
			List<String> myList = new ArrayList<String>();
			
			while(test.next()) {
				myList.add(test.getString(1));
			}
			for(String i : myList) {
				System.out.println(myList);
			}
			
			/*
			sql = "SELECT count(*) FROM employees";
			test = query.executeQuery(sql);
			test.next();
			System.out.println("Table 'Employees' has " + test.getInt(1) + " rows");
			*/
			
			/*
			sql = "select count(*) from offices";
			
			// Result of Query
			test = query.executeQuery(sql);
			test.next();
			System.out.println("Table 'Offices' has " + test.getInt(1) + " rows");
			String[] DB_rows = new String[test.getInt(1)];
			
			// Display the rows of a column
			sql = "select city from offices";
			test = query.executeQuery(sql);
			for(int i=0; i<DB_rows.length; i++) {
				test.next();
				DB_rows[i] = test.getString(1);
			}
			
			for(String i : DB_rows) {
				System.out.println(i);
			}
			*/
			
			/*
			sql = "select * from offices";
			test = query.executeQuery(sql);
			test.next();
			System.out.println(test.getString(3));
			test.next();
			System.out.println(test.getString(3));
			*/
			
			/*
			List<String> myList = new ArrayList<String>();
			for(int i=1; i<numRows+1; i++) {
				test.next();
				myList.add(test.getString(i));
				System.out.println(test.getString(i));	
			}*/
			
		} catch(SQLException e) {
			System.out.println("!Connection Error!");
			e.printStackTrace();
		}
	}
}
