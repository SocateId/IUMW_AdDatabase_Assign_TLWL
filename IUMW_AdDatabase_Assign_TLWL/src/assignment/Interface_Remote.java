package assignment;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.util.List;

// Allow For Remote Invocation of Functions, as it calling of functions from Another Machine
public interface Interface_Remote extends Remote {
	
	// Connect to Database
	void connectDB() throws RemoteException;
	
	// Select Query to Database, returns the Result of Query as a List
	List<String> selectQuery(String sql) throws RemoteException;
	
	// Update Query (CREATE, DROP, INSERT, UPDATE, DELETE) to Database, returns the Row Count or 0 for no return Statements
	int updateQuery(String sql) throws RemoteException;
	
	// Gets a Console Message from Server
	String getMessage() throws RemoteException;
	
	// Send a Console Message to Server
	void sendMessage() throws RemoteException;
}
