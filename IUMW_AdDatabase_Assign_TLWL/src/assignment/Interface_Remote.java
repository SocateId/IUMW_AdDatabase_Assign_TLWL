package assignment;

import java.rmi.Remote;
import java.rmi.RemoteException;

// Allow For Remote Invocation of Functions, as it calling of functions from Another Machine
public interface Interface_Remote extends Remote {
	String getMessage() throws RemoteException;
	
	void sendMessage() throws RemoteException;
}
