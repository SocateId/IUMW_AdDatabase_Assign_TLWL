package test;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMI_ServerIntf extends Remote {
	String getMessage() throws RemoteException;
}
