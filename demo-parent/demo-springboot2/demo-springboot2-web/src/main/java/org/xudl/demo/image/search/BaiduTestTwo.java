package org.xudl.demo.image.search;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.imagesearch.AipImageSearch;

public class BaiduTestTwo {

	// 设置APPID/AK/SK
	public static final String APP_ID = "10996719";

	public static final String API_KEY = "145qYCq4fap5YmTGoduvU1N4";

	public static final String SECRET_KEY = "82Uy6j20AGPocrGapsnbPZVcKbeVwRtI";

	public static final AipImageSearch client = new AipImageSearch(APP_ID, API_KEY, SECRET_KEY);
	static {
		client.setConnectionTimeoutInMillis(3000);
		client.setSocketTimeoutInMillis(60000);
	}

	public static void main(String[] args) throws Exception {
		batchUpload(new File("D:\\noah\\拍书\\图片"));
		// delete("D:\\noah\\拍书\\人教版-初中-数学-七年级-下学期-12版\\0扫描图");
		// search("D:\\noah\\拍书\\人教版-初中-数学-七年级-下学期-12版\\校验测试图");

		// uploadSame("D:\\noah\\拍书\\人教版-初中-数学-七年级-下学期-12版\\0扫描图");
		// searchSame("D:\\noah\\拍书\\人教版-初中-数学-七年级-下学期-12版\\校验测试图");
	}

	public static void search(String dirPath) {
		// 传入可选参数调用接口
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("tags", "1,1000");
		options.put("pn", "0");
		options.put("rn", "10");

		File fileDir = new File(dirPath);
		for (File file : fileDir.listFiles()) {
			// 参数为本地图片路径
			JSONObject res = client.similarSearch(file.getPath(), options);
			System.out.println(String.format("%s\t%s", file.getName(), res));
		}
	}

	public static void batchUpload(File file) {
		if (file.isDirectory()) {
			File[] subFiles = file.listFiles();
			for (File subFile : subFiles) {
				batchUpload(subFile);
			}
		} else if (file.isFile()) {
			String type = file.getName().substring(file.getName().lastIndexOf("."));
			if (".jpg".equalsIgnoreCase(type)) {
				String brief = file.getParentFile().getParentFile().getName() + ":"
				        + file.getName().substring(0, file.getName().lastIndexOf("."));
				HashMap<String, String> options = new HashMap<String, String>();
				options.put("brief", brief);
				options.put("tags", "3,3000");
				JSONObject res = client.sameHqAdd(file.getPath(), options);

				System.err.println(brief + ":::::" + res);
			}
		}
	}

	/**
	 * 将指定目录的图片上传
	 * 
	 * @param dirPath
	 */
	public static void upload(String dirPath) {
		File dirFile = new File(dirPath);
		for (File file : dirFile.listFiles()) {
			// 传入可选参数调用接口
			HashMap<String, String> options = new HashMap<String, String>();
			options.put("brief", file.getName() + "-" + dirFile.getName());
			options.put("tags", "1,1000");

			// 参数为本地图片路径
			JSONObject res = client.similarAdd(file.getPath(), options);
			System.out.println(file.getName() + " " + res.toString());
		}
	}

	public static void delete(String dirPath) {
		// 传入可选参数调用接口
		HashMap<String, String> options = new HashMap<String, String>();

		File dirFile = new File(dirPath);
		for (File file : dirFile.listFiles()) {
			// 删除相似图，传入参数为图片签名
			JSONObject res = client.similarDeleteByImage(file.getPath(), options);
			System.out.println(res.toString());
		}
	}

	public static void uploadSame(String dirPath) {
		File dirFile = new File(dirPath);
		for (File file : dirFile.listFiles()) {
			// 传入可选参数调用接口
			HashMap<String, String> options = new HashMap<String, String>();
			options.put("brief", file.getName() + "-" + dirFile.getName());
			options.put("tags", "2,2000");

			// 参数为本地图片路径
			JSONObject res = client.sameHqAdd(file.getPath(), options);
			System.out.println(file.getName() + " " + res.toString());
		}
	}

	public static void searchSame(String dirPath) {
		// 传入可选参数调用接口
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("tags", "2,2000");
		options.put("tag_logic", "0");
		options.put("pn", "0");
		options.put("rn", "10");

		File fileDir = new File(dirPath);
		for (File file : fileDir.listFiles()) {
			// 参数为本地图片路径
			JSONObject res = client.sameHqSearch(file.getPath(), options);
			System.out.println(String.format("%s\t%s", file.getName(), res));
		}
	}

	public static void deleteSame(String sign) {
		// 初始化一个AipImageSearch
		AipImageSearch client = new AipImageSearch(APP_ID, API_KEY, SECRET_KEY);
		// 可选：设置网络连接参数
		client.setConnectionTimeoutInMillis(3000);
		client.setSocketTimeoutInMillis(60000);

		// 传入可选参数调用接口
		HashMap<String, String> options = new HashMap<String, String>();

		// 删除相似图，传入参数为图片签名
		JSONObject res = client.sameHqDeleteBySign(sign, options);
		System.out.println(res.toString());
	}
}
