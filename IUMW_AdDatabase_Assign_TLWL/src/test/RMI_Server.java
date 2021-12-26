package test;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

// RMI SERVER
public class RMI_Server extends UnicastRemoteObject implements RMI_ServerIntf {
	public static final String MESSAGE = "Hello World";
	
	public RMI_Server() throws Exception {
		// Do this to avoid "rmic" step
		super(0);
	}
	
	public String getMessage() {
		return MESSAGE;
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println("RMI server started");
		
		try {
			LocateRegistry.createRegistry(1099);
			System.out.println("java RMI registry created.");
		} catch(RemoteException e) {
			System.out.println("java RMI registry already exist.");
		}
		
		// Starts the Server
		RMI_Server server = new RMI_Server();
		
		// Binds object instance to RMI_Server
		Naming.rebind("//localhost/RMI_Server", server);
		System.out.println("PeerServer bound in registry");
	}
}
