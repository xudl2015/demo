package org.xudl.demo.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "xudl.demo2")
public class PublicProperties {
	private String name;
	private String welcome;
	private int age;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" name:").append(name);
		builder.append(" welcome:").append(welcome);
		builder.append(" age:").append(age);
		return builder.toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWelcome() {
		return welcome;
	}

	public void setWelcome(String welcome) {
		this.welcome = welcome;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
