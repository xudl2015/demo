package org.xudl.demo.fastjson;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class SchoolVo implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private String address;
	private Date buildTime;
	private Integer allStudentCount;
	private BigDecimal saray;
	private List<StudentVo> studentList;
	private Map<String, StudentVo> studentMap;

	public Map<String, StudentVo> getStudentMap() {
		return studentMap;
	}

	public BigDecimal getSaray() {
		return saray;
	}

	public void setSaray(BigDecimal saray) {
		this.saray = saray;
	}

	public void setStudentMap(Map<String, StudentVo> studentMap) {
		this.studentMap = studentMap;
	}

	public Integer getAllStudentCount() {
		return allStudentCount;
	}

	public void setAllStudentCount(Integer allStudentCount) {
		this.allStudentCount = allStudentCount;
	}

	public String getName() {
		return name;
	}

	public String getFullName() {
		return name;
	}

	public Boolean isImportent() {
		return false;
	}

	public int isTest() {
		return 0;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<StudentVo> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<StudentVo> studentList) {
		this.studentList = studentList;
	}

	public Date getBuildTime() {
		return buildTime;
	}

	public void setBuildTime(Date buildTime) {
		this.buildTime = buildTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
