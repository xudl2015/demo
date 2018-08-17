package org.xudl.demo.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "demo")
public class DemoProperties {
	private String name;
	private String age;
	private InnerDemo inner = new InnerDemo();

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("name:").append(name)
		        .append(" age:").append(age)
		        .append(" inner.name:").append(inner.getName());
		return builder.toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public InnerDemo getInner() {
		return inner;
	}

	public void setInner(InnerDemo inner) {
		this.inner = inner;
	}

	class InnerDemo {
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}
}
