package test;

import java.rmi.Naming;

public class RMI_Client {
	public static void main(String[] args) throws Exception {
		RMI_ServerIntf server = (RMI_ServerIntf)Naming.lookup("//localhost/RMI_Server");
		System.out.println(server.getMessage());
	}
}
