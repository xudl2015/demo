package org.xudl.demo.fastjson;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class FastJsonDemo {

	public static void main(String[] args) {
		SchoolVo school = new SchoolVo();
		school.setName("xinandaxue?=/&:");
		school.setAddress("");
		school.setAllStudentCount(50);
		school.setSaray(new BigDecimal("5.325441"));
		school.setBuildTime(new Date());

		String json = JSON.toJSONString(school);
		System.out.println(json);

		JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
		json = JSON.toJSONString(school,
		        SerializerFeature.WriteMapNullValue);
		System.out.println(json);

		List<SchoolVo> list = new ArrayList<>();
		list.add(school);

		json = JSON.toJSONString(list);
		System.out.println(json);
		list = JSON.parseArray(json, SchoolVo.class);
		System.out.println(list.get(0).getName());

	}

}
