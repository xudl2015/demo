package org.xudl.demo.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MyService extends Remote {
	String getHello(String name) throws RemoteException;
}
