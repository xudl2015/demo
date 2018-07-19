<<<<<<< HEAD
package org.xudl.demo.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "xudl.demo")
public class DemoProperties {
	private String name;
	private String welcome;

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

}
=======
package org.xudl.demo.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "xudl.demo")
public class DemoProperties {
	private String name;
	private String welcome;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" name:").append(name);
		builder.append(" welcome:").append(welcome);
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

}
>>>>>>> 44017638b059bcfd7c56e405a181190849308275
