package org.xudl.demo.image.search;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 进行访问参数签名的工具类
 * 
 * @author Administrator
 *
 */
public class SignUtil {

	private static Logger logger = Logger.getLogger(SignUtil.class);

	public static String getSignString(String url, String requestMethod, Map<String, Object> paramMap) {
		List<String> paramsNameList = new ArrayList<String>();
		for (String key : paramMap.keySet()) {
			paramsNameList.add(key);
		}
		Collections.sort(paramsNameList);

		StringBuilder builder = new StringBuilder(requestMethod + url);
		for (int i = 0; i < paramsNameList.size(); i++) {
			String paramName = paramsNameList.get(i);
			try {
				builder.append(paramName).append("=")
				        .append(URLEncoder.encode(paramMap.get(paramName).toString(), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				logger.error(e);
			}
		}

		return builder.toString();
	}

	/**
	 * 获取参数签名
	 * 
	 * @param url
	 * @param requestMethod
	 * @param securityKey
	 * @param paramMap
	 * @return
	 */
	public static String getSign(String url, String requestMethod, String securityKey, Map<String, Object> paramMap) {
		return EncryptUtil.md5Digest(getSignString(url, requestMethod, paramMap) + securityKey);
	}
}
