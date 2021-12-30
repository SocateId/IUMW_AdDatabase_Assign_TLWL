package assignment;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


// The RMI Server
public class UptownBooks_Server extends UnicastRemoteObject implements Interface_Remote {
	// Database Credentials
	private static String url = "jdbc:mysql://localhost:3306/uptown_books";
	private static String username = "root";
	private static String password = "";
	static Connection connect;
	// Testing RMI Stuff
	public static final String MESSAGE = "HELLO WORLD";
	
	public UptownBooks_Server() throws Exception {
		super(0);
	}
	
	@Override
	public void connectDB() throws RemoteException {
		// Connect to Database
		try {
			connect = DriverManager.getConnection(url, username, password);
			System.out.println("!DB Connection Succesfull!");
		} catch(SQLException e) {
			System.out.println("!DB Connection Error!");
			e.printStackTrace();
		}
	}
	
	@Override
	public List<String> selectQuery(String sql) throws RemoteException {
		try {
			Statement query = connect.createStatement();
			ResultSet queryResult = query.executeQuery(sql);
			System.out.println("!Select Query Successful! " + queryResult);
			
			List<String> result = new ArrayList<String>();
			while(queryResult.next()) {
				result.add(queryResult.getString(1));
			}
			
			return result;
			
		} catch(SQLException e) {
			System.out.println("!Select Query Error!");
			e.printStackTrace();
			return null;
		}	
	}
	
	@Override
	public List<List<String>> selectQuery_allRows(String table) throws RemoteException {
		try {
			// Column Names
			List<String> colNames = selectQuery("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = 'uptown_books' AND TABLE_NAME = '" + table + "'");
			
			// Execute Query
			Statement query = connect.createStatement();
			ResultSet queryResult = query.executeQuery("SELECT * FROM " + table);
			System.out.println("!Select All Rows Query Successful! " + queryResult);
			
			// Row Values
			List<List<String>> rowValues = new ArrayList<List<String>>();
			List<String> tempList = new ArrayList<String>();
			while(queryResult.next()) {
				for(int i=0; i<colNames.size(); i++) {
					tempList.add(queryResult.getString(colNames.get(i)));
				}
				rowValues.add(tempList);
				tempList = new ArrayList<String>();
			}
			return rowValues;
			
		} catch(SQLException e) {
			System.out.println("!Select All Rows Query Error!");
			e.printStackTrace();
			return null;
		}
		
	}
	
	@Override
	public int updateQuery(String sql) throws RemoteException {
		try {
			Statement query = connect.createStatement();
			int queryResult = query.executeUpdate(sql);
			return queryResult;
			
		} catch(SQLException e) {
			System.out.println("!Update Query Error!");
			e.printStackTrace();
			return 0;
		}
	}
	
	
	@Override
	public String getMessage() throws RemoteException {
		return "Remote Invocation of getMessage(), " + MESSAGE;
	}
	
	
	
	@Override
	public void sendMessage() throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	
	
	public static void main(String[] args) throws Exception {
		System.out.println("RMI server started");
		try {
			LocateRegistry.createRegistry(1099);
			System.out.println("Java RMI registry created.");
		} catch(RemoteException e) {
			System.out.println("Java RMI registry already exist.");
		}
		
		// Starts the Server
		Interface_Remote server = new UptownBooks_Server();
		
		// Binds object instance to RMI_Server
		Naming.rebind("//localhost/UptownBooks_Server", server);
		System.out.println("PeerServer bound in registry");
		
		// Connect to Database
		try {
			connect = DriverManager.getConnection(url, username, password);
			System.out.println("!DB Connection Succesfull!");
		} catch(SQLException e) {
			System.out.println("!DB Connection Error!");
			e.printStackTrace();
		}
	}
}
