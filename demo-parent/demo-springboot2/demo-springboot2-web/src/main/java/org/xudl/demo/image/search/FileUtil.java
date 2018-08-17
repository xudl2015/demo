package org.xudl.demo.image.search;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.alibaba.fastjson.util.IOUtils;

public class FileUtil {
	private static ExecutorService executorService = Executors.newFixedThreadPool(60);

	public static void main(String[] args) throws Exception {
		List<String> idsList = readFileData("抽测图片-人教版数学二年级上册-baidusame-log.txt", false);
		for (String line : idsList) {
			if (line != null) {
				System.out.println(URLDecoder.decode(line, "utf-8"));
			}
		}
	}

	private static List<String> readFileData(String filename, boolean isSkipFirt) {
		InputStream in = null;
		BufferedReader reader = null;
		String data = null;
		List<String> dataList = new ArrayList<String>(2500000);
		try {
			in = FileUtil.class.getResourceAsStream(filename);
			reader = new BufferedReader(new InputStreamReader(in));
			if (isSkipFirt) {
				reader.readLine();
			}
			do {
				data = reader.readLine();
				dataList.add(data);
			} while (data != null);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.close(reader);
			IOUtils.close(in);
		}
		return dataList;
	}

}
