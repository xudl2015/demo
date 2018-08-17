package org.xudl.demo.resource;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceDemo {

	public static void main(String[] args) {

	}

	public static void getResourceDemo() {
		Locale locale = new Locale("zh", "CN");
		ResourceBundle bundle = ResourceBundle.getBundle("myres", locale);
		System.out.println(bundle.getString("reskey"));

		bundle = ResourceBundle.getBundle("myres", Locale.getDefault());
		System.out.println(bundle.getString("reskey"));
	}
}
