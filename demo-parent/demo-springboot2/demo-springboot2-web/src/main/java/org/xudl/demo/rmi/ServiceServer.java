package org.xudl.demo.rmi;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMISocketFactory;
import java.rmi.server.UnicastRemoteObject;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ServiceServer {
	public static void main(String[] args) throws NamingException, IOException, AlreadyBoundException {

		// RMISocketFactory.setSocketFactory(new MyRMISocketFactory());

		MyService service = new MyServiceImpl();

		Registry registry = LocateRegistry.createRegistry(8083);
		// 此处绑定时，只能指定名称。不能指定ip地址
		registry.rebind("myservice3", service);

		Context context = new InitialContext();
		context.rebind("rmi://192.168.8.50:8083/myservice", service);

		Naming.bind("rmi://192.168.8.50:8083/myservice2/", service);

		// 或使用下面这种方式来发布服务
		UnicastRemoteObject.unexportObject(service, true);
		UnicastRemoteObject.exportObject(service, 2000);
		System.out.println("---------server start---------------");
	}

}

class ServerRMISocketFactory extends RMISocketFactory {

	@Override
	public Socket createSocket(String host, int port) throws IOException {
		Socket socket = new Socket();
		socket.setSoTimeout(10000);
		socket.setSoLinger(Boolean.FALSE, 0);
		socket.connect(new InetSocketAddress(host, port), 10000);
		return socket;
	}

	@Override
	public ServerSocket createServerSocket(int port) throws IOException {
		return new ServerSocket(port);
	}

}
