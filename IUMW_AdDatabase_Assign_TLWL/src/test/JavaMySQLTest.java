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
			int updateTest;
			// Query to be sent
			String sql;
			
			sql = "SELECT ID FROM employees WHERE store_number = '0' OR store_number = '1' ORDER BY ID";
			test = query.executeQuery(sql);
			List<String> myList = new ArrayList<String>();
			while(test.next()) {
				myList.add(test.getString(1));
			}
			System.out.println(myList);
			
			/*
			sql = "SELECT DISTINCT Store_Number FROM sales";
			test = query.executeQuery(sql);
			List<String> myList = new ArrayList<String>();
			while(test.next()) {
				myList.add(test.getString(1));
			}
			System.out.println(myList);
			*/
			
			/*
			sql = "SELECT Amount FROM sales WHERE Store_Number = '1'";
			test = query.executeQuery(sql);
			List<String> myList = new ArrayList<String>();
			while(test.next()) {
				myList.add(test.getString(1));
			}
			System.out.println(myList);
			double total = 0;
			for(int i=0; i<myList.size(); i++) {
				total += Double.parseDouble(myList.get(i));
			}
			System.out.println("Total = " + total);
			*/
			
			/*
			sql = "SELECT ID FROM employees";
			test = query.executeQuery(sql);
			List<String> myList = new ArrayList<String>();
			while(test.next()) {
				myList.add(test.getString(1));
			}
			System.out.println(myList);
			*/
			
			/*
			// Updates stock of ISBN = 9780134601533 and Store_number = 0, from Table Book_stock
			sql = "UPDATE book_stock SET stock = 69 WHERE (ISBN = 9780134601533 AND store_number = 0)";
			updateTest = query.executeUpdate(sql);
			System.out.println(updateTest);
			*/
			
			/*
			sql = "SELECT ISBN FROM book_ctlg WHERE ISBN NOT IN (SELECT ISBN FROM book_stock)";
			test = query.executeQuery(sql);
			List<String> myList = new ArrayList<String>();
			while(test.next()) {
				myList.add(test.getString(1));
			}
			System.out.println(myList);
			*/
			
			/*
			// Columns Names
			sql = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = 'uptown_books' AND TABLE_NAME = 'book_ctlg'";
			test = query.executeQuery(sql);
			List<String> myList = new ArrayList<String>();
			while(test.next()) {
				myList.add(test.getString(1));
			}
			System.out.println(myList);
			// Row Names
			sql = "SELECT * FROM book_ctlg";
			test = query.executeQuery(sql);
			List<List<String>> my2DList = new ArrayList<List<String>>();
			List<String> tempList = new ArrayList<String>();
			while(test.next()) {
				for(int i=0; i<myList.size(); i++) {
					tempList.add(test.getString(myList.get(i)));
					//System.out.println(myList.get(i) + ": " + test.getString(myList.get(i)));
				}
				//System.out.println(tempList);
				my2DList.add(tempList);
				tempList = new ArrayList<String>();
			}
			System.out.println(my2DList);
			// Turn List to Array
			System.out.println("Array ColNames -");
			String[] colNames = myList.toArray(new String[0]);
			for(String i : colNames) {
				System.out.println(i);
			}
			System.out.println("Array RowNames -");
			//String[][] rowNames = my2DList.toArray(new String[0][0]);
			String[][] rowNames = my2DList.stream().map(l -> l.stream().toArray(String[]::new)).toArray(String[][]::new);
			for(int i=0; i<rowNames.length; i++) {
				for(int j=0; j<rowNames[i].length; j++) {
					System.out.println(rowNames[i][j]);
				}
			}
			*/
			
			/*
			sql = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = 'uptown_books' AND TABLE_NAME = 'offices'";
			test = query.executeQuery(sql);
			List<String> myList = new ArrayList<String>();
			//System.out.println(test);
			
			while(test.next()) {
				myList.add(test.getString(1));
			}
			
			for(String i : myList) {
				System.out.println(i);
			}
			*/
			
			/*
			sql = "SELECT * FROM offices";
			test = query.executeQuery(sql);
			List<String> myList = new ArrayList<String>();
			
			while(test.next()) {
				myList.add(test.getString("City"));
			}
			
			for(String i : myList) {
				System.out.println(i);
			}
			*/
			
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
