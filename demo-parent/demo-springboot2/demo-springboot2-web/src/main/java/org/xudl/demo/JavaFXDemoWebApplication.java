package org.xudl.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.xudl.demo.util.IPScanUtil;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * 以javaFX + spring boot启动项目的方式
 * 
 * @author xudl
 *
 */
@SpringBootApplication // 标记为springboot项目
public class JavaFXDemoWebApplication extends Application {
	private static ConfigurableApplicationContext applicationContext = null;

	@Override
	public void init() throws Exception {
		/*CompletableFuture.supplyAsync(() -> {
			ConfigurableApplicationContext ctx = SpringApplication.run(this.getClass());
			return ctx;
		}).thenAccept(this::setSpringApplicationContext);*/
		applicationContext = SpringApplication.run(this.getClass());
	}

	@Override
	public void stop() throws Exception {
		System.out.println("-----stop---------");
		applicationContext.stop();
		System.exit(0);
	}

	@Override
	public void start(Stage stage) throws Exception {
		StackPane root = new StackPane();

		String localIp = IPScanUtil.localIpscan();

		WebView view = new WebView();
		WebEngine engine = view.getEngine();
		// engine.load("http://192.168.8.50:8080/demo/page");
		engine.load("http://news.163.com/");
		root.getChildren().add(view);

		Scene scene = new Scene(root, 1200, 600);
		stage.setScene(scene);
		stage.setTitle("优学派发题端");
		stage.getIcons().add(new Image("/icon32.png"));
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
