package org.xudl.demo.charset;

import java.io.UnsupportedEncodingException;

public class CharsetUtil {

	public static void main(String[] args) {

	}

	public static void getChartCodeDemo() throws UnsupportedEncodingException {
		String content = "123-*汉字";
		StringBuilder builder = new StringBuilder(content.length() * 5);
		for (int i = 0; i < content.length(); i++) {
			char c = content.charAt(i);
			builder.append("字符:").append(c)
			        .append(" Unicode编码：").append(Integer.toHexString(content.charAt(i) & 0xffff))
			        .append(" 16进制数：").append(Integer.toHexString(c));
			byte[] charBytes = String.valueOf(c).getBytes("GBK");
			int code = charBytes.length >= 2 ? ((charBytes[0] & 255) << 8 + (charBytes[1] & 255))
			        : ((charBytes[0] & 255) << 8);
			builder.append(" GBK编码：").append(Integer.toHexString(code)).append("\n");
		}
		System.out.println(builder.toString());
	}

}
