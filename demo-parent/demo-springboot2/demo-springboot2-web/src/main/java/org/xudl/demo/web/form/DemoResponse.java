package org.xudl.demo.web.form;

import java.io.Serializable;
import java.util.Date;

/**
 * 响应码信息
 * 
 * @author xudl
 *
 */
public class DemoResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 响应码
	 */
	private int code;
	/**
	 * 响应码说明
	 */
	private String desc;
	/**
	 * 服务器当前时间
	 */
	private Date time;

	/**
	 * 响应数据
	 */
	private Object data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
