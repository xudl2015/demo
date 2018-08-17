package org.xudl.demo.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/asyn_servlet", asyncSupported = true)
public class AsynServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println("进入Servlet的时间：" + new Date() + ".");
		out.flush();

		// 在子线程中执行业务调用，并由其负责输出响应，主线程退出
		final AsyncContext ctx = req.startAsync();
		ctx.setTimeout(20000);
		// 添加事件回调处理
		ctx.addListener(new AsyncListener() {

			@Override
			public void onTimeout(AsyncEvent event) throws IOException {
				PrintWriter wirter = event.getAsyncContext().getResponse().getWriter();
				wirter.write("---server onTimeout---");
				wirter.flush();
				System.out.println("--onTimeout--");
			}

			@Override
			public void onStartAsync(AsyncEvent event) throws IOException {
				PrintWriter wirter = event.getAsyncContext().getResponse().getWriter();
				wirter.write("---server onStartAsync---");
				wirter.flush();
				System.out.println("--onStartAsync--");
			}

			@Override
			public void onError(AsyncEvent event) throws IOException {
				PrintWriter wirter = event.getAsyncContext().getResponse().getWriter();
				wirter.write("---server onError---");
				wirter.flush();
				System.out.println("--onError--");
			}

			@Override
			public void onComplete(AsyncEvent event) throws IOException {

				// 如果是超时，则客户端不会在收到此处写出的消息
				PrintWriter wirter = event.getAsyncContext().getResponse().getWriter();
				wirter.write("---server onComplete---");
				wirter.flush();

				System.out.println("--onComplete--");
			}
		});
		new Work(ctx).start();
		out.println("结束Servlet的时间：" + new Date() + ".");
		out.flush();
	}
}

class Work extends Thread {
	private AsyncContext context;

	public Work(AsyncContext context) {
		this.context = context;
	}

	@Override
	public void run() {
		try {
			long sleepTime = 0L;
			while (sleepTime <= 210000) {
				Thread.sleep(2000);// 让线程休眠2s钟模拟超时操作
				BufferedReader reader = new BufferedReader(
				        new InputStreamReader(context.getRequest().getInputStream()));
				String line = null;
				while ((line = reader.readLine()) != null) {
					System.out.println("---read data:" + line);
				}

				System.out.println("---" + context.hasOriginalRequestAndResponse());
				PrintWriter wirter = context.getResponse().getWriter();
				wirter.write("延迟输出----\r\n");
				wirter.flush();

				sleepTime += 2000;
			}
			// 调用了complete方法后，可以激活监听器中的onComplete方法，在该方法还可以在返回内容。
			context.complete();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
