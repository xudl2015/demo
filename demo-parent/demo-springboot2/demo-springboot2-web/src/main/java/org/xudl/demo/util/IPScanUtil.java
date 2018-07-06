package org.xudl.demo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class IPScanUtil {

	public static void main(String[] args) {
		ipscan();
	}

	/**
	 * 扫描局域网内的IP地址，并检查指定的端口是否可以访问
	 */
	public static void ipscan() {
		List<String> list = new ArrayList<String>();
		BufferedReader br = null;
		try {
			Runtime runtime = Runtime.getRuntime();
			Process process = runtime.exec("arp -a");
			br = new BufferedReader(new InputStreamReader(process.getInputStream(), "gbk"));
			String inline;
			String localIp = localIpscan();
			String locatIpPre = localIp.substring(0, localIp.lastIndexOf("."));
			while ((inline = br.readLine()) != null) {
				if (StringUtils.isNotEmpty(inline)
				        && inline.indexOf(".") != -1
				        && inline.startsWith(" ")) {
					String[] str = inline.split(" {4}");
					String ip = str[0].trim();
					if (ip.startsWith(locatIpPre)) {
						list.add(ip);
					}
				}
			}

			System.out.println("===============================");
			for (String ip : list) {
				boolean reachable = connectTest(ip, 8083);
				System.out.println(ip + "-" + reachable);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
				br = null;
			}
		}
	}

	public static String localIpscan() throws SocketException, UnknownHostException {
		String host = InetAddress.getLocalHost().getHostAddress();
		/*Enumeration<NetworkInterface> enu = NetworkInterface.getNetworkInterfaces();
		while (enu.hasMoreElements()) {
			NetworkInterface net = enu.nextElement();
			Enumeration<InetAddress> addresses = net.getInetAddresses();
			while (addresses.hasMoreElements()) {
				InetAddress address = addresses.nextElement();
				if (address != null && address instanceof Inet4Address) {
					host = address.getHostAddress();
		
					System.out.println(host + " " + address.isSiteLocalAddress());
				}
			}
		}*/
		return host;
	}

	public static boolean connectTest(String ip, int port) throws IOException {
		InetSocketAddress address = new InetSocketAddress(ip, port);
		Socket socket = new Socket();
		boolean isConnected = false;
		try {
			socket.connect(address, 10);
			isConnected = socket.isConnected();
			System.out.println(String.format("%s:%s result:%s", ip, port, isConnected));
		} catch (Exception e) {
		} finally {
			socket.close();
		}
		return isConnected;
	}
}
