package assignment;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class UptownBooks_Server extends UnicastRemoteObject implements Interface_Remote {
	private String url = "jdbc:mysql://localhost:3306/uptown_books";
	private String username = "root";
	private String password = "";
	
	protected UptownBooks_Server() throws Exception {
		super(0);
	}

	@Override
	public String getMessage() throws RemoteException {
		
		return "Remote Invocation of getMessage()";
	}

	@Override
	public void sendMessage() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
