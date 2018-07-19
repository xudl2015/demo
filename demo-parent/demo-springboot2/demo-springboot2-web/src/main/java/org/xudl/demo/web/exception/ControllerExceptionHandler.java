package org.xudl.demo.web.exception;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xudl.demo.web.form.DemoResponse;

/**
 * 控制器中抛出的异常的处理类。
 * 
 * @author xudl
 *
 */
@ControllerAdvice
public class ControllerExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public DemoResponse testHandlerNoahException(RuntimeException e, HttpServletRequest request) {
		logger.info("----------ControllerExceptionHandler-------------");
		loggerMessage(e, request);
		return new DemoResponse();
	}

	private void loggerMessage(Exception e, HttpServletRequest request) {
		StringBuilder builder = new StringBuilder(request.getRequestURI());
		builder.append(" paramters[");
		Map<String, String[]> paraMap = request.getParameterMap();
		for (String name : paraMap.keySet()) {
			builder.append(name).append(StringUtils.arrayToCommaDelimitedString(paraMap.get(name)));
		}
		builder.append("]");
		logger.error(builder.toString(), e);
	}
}
