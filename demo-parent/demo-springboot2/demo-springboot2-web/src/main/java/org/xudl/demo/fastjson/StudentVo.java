package org.xudl.demo.fastjson;

import java.util.Date;

public class StudentVo {

	private String name;
	private Integer age;
	private Date birthDay;
	private String company;
	private Double saray;
	private String[] friends;
	private String localUserName;
	private String address;
	private String phone;
	


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("--demovo--");
		builder.append(" name:").append(name);
		builder.append(" phone:").append(phone);
		return builder.toString();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String[] getFriends() {
		return friends;
	}

	public void setFriends(String[] friends) {
		this.friends = friends;
	}

	private boolean isBoy;

	public StudentVo() {

	}

	public boolean isBoy() {
		return isBoy;
	}

	public void setBoy(boolean isBoy) {
		this.isBoy = isBoy;
	}

	public Double getSaray() {
		return saray;
	}

	public void setSaray(Double saray) {
		this.saray = saray;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getLocalUserName() {
		return localUserName;
	}

	public void setLocalUserName(String localUserName) {
		this.localUserName = localUserName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
}
