package org.xudl.demo.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HttpDemo {

	public static void main(String[] args) throws Exception {
		httpDemo();
	}

	public static void httpsDemo() throws Exception {
		SSLContext sslContext = SSLContext.getInstance("SSL");
		sslContext.init(null, new TrustManager[] { new X509TrustManager() {
			@Override
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

			}

			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

			}

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		} }, new SecureRandom());

		String url = "https://pmc.youxuepai.com/push/cross/noah/user/machineList.json?jsoncallback=jQuery1710842486036609735_1533190795375&access_token=51933a3d37247da3fdaeadb1cbd2730e&timestamp=1533190795642&client_key=24405F2E560BEAA28D6481DC1E4033E5&from_flag=3&_=1533190795643";
		URL postUrl = new URL(url);
		HttpsURLConnection connection = (HttpsURLConnection) postUrl.openConnection();
		connection.setSSLSocketFactory(sslContext.getSocketFactory());
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setUseCaches(false);
		connection.setRequestMethod("POST");
		connection.connect();

		OutputStream outToServer = connection.getOutputStream();
		outToServer.write("aaa".getBytes("utf-8"));
		outToServer.flush();

		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}
		reader.close();

		int code = connection.getResponseCode();
		String message = connection.getResponseMessage();
		connection.disconnect();
	}

	public static void httpDemo() throws Exception {
		String url = "http://192.168.8.50:8080/asyn_servlet";
		URL postUrl = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setUseCaches(false);
		connection.setRequestMethod("POST");
		connection.setInstanceFollowRedirects(true);
		connection.setRequestProperty("Content-Type", "text/html;charset=utf-8");

		connection.addRequestProperty("param-name", URLEncoder.encode("param-value", "UTF-8"));

		connection.connect();

		// 向连接中输出数据
		final DataOutputStream out = new DataOutputStream(connection.getOutputStream());
		new Thread(new Runnable() {
			@Override
			public void run() {
				int n = 0;
				while (n < 6) {
					n++;
					try {
						String content = n + "-地发动发动发动发动法发\r\n";
						out.write(content.getBytes("UTF-8"));
						out.flush();

						Thread.currentThread().sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			}
		}).start();

		// out.close();

		// 从连接中读取数据
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}
		reader.close();

		int code = connection.getResponseCode();
		String message = connection.getResponseMessage();
		connection.disconnect();
	}

}
