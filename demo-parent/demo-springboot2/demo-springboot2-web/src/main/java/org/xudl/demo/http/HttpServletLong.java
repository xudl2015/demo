package org.xudl.demo.http;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 一个长连接的http服务器server端
 * 
 * @author xudl
 *
 */
public class HttpServletLong extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		while (true) {
			try {

				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write(
				        "<script type=\"text/javascript\">parent.jsFun(\"" + System.currentTimeMillis()
				                + "\")</script>");
				response.flushBuffer();
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}

			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
