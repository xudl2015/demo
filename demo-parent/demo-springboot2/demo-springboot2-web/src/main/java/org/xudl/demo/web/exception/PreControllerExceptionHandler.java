package org.xudl.demo.web.exception;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

/**
 * 进入控制器类的方法前抛出的异常处理类。例如路径不存在，参数不匹配等
 * 
 * @author xudl
 *
 */
@RestController
@RequestMapping("${server.error.path:${error.path:/error}}")
public class PreControllerExceptionHandler implements ErrorController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${server.error.path:${error.path:/error}}")
	private String path;

	@Autowired
	private ErrorAttributes errorAttributes;

	@Override
	public String getErrorPath() {
		return path;
	}

	@RequestMapping
	public void dealError(HttpServletRequest request) {
		logger.info("---------PreControllerExceptionHandler------dealError-------");
		WebRequest webRequest = new ServletWebRequest(request);
		Map<String, Object> map = errorAttributes.getErrorAttributes(webRequest, true);
		StringBuilder builder = new StringBuilder(map.size() * 3);
		builder.append("eprror message:\n");
		for (String key : map.keySet()) {
			builder.append(key).append(":").append(map.get(key)).append("\n");
		}
		logger.error(builder.toString());
	}

}
