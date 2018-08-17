package org.xudl.demo.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MyServiceImpl extends UnicastRemoteObject implements MyService {

	protected MyServiceImpl() throws RemoteException {
		super();
	}

	private static final long serialVersionUID = 1L;

	@Override
	public String getHello(String name) throws RemoteException {
		return "Hello " + name;
	}
}
