package org.xudl.demo.web.config;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 定义web转换的一些常用设置。例如设置日期的转换格式等
 * 
 * @author xudl
 *
 */
@Configuration
public class WebCustomerConfig {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 设置jackson的转换时采用的配置
	 * 
	 * @return
	 */
	@Bean
	public ObjectMapper getObjectMapper() {
		logger.info("---getObjectMapper---");
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		return mapper;
	}
}
