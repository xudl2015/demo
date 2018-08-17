package org.xudl.demo.rmi;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.RMISocketFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ServiceClient {

	public static void main(String[] args)
	        throws NamingException, MalformedURLException, RemoteException, NotBoundException {
		/*Context context = new InitialContext();
		MyService service = (MyService) context.lookup("rmi://192.168.8.50:2001/myservice");
		String res = service.getHello("xudl");
		System.out.println(res);*/
		// 或使用
		MyService service = (MyService) Naming.lookup("rmi://192.168.8.50:8083/myservice3");
		String res = service.getHello("xudl2");
		System.out.println(res);
	}

}
