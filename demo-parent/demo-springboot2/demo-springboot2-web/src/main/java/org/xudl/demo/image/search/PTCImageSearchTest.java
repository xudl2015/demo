package org.xudl.demo.image.search;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class PTCImageSearchTest {

	public static void main(String[] args) throws ClientProtocolException, IOException, Exception {
		postFile("D:\\noah\\temp\\1_1.jpg", "jpg", "100");
	}

	public static String postFile(String uploadFile, final String uploadType, String userId)
	        throws ClientProtocolException, IOException, Exception {
		long begin = 0, end = 0, t1 = 0, t2 = 0, t3 = 0, t4 = 0;
		begin = System.currentTimeMillis();

		File file = new File(uploadFile);
		HttpClient httpclient = new DefaultHttpClient();
		// 设置通信协议版本
		httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

		String url = "http://nimage.search.noahedu.com/img-search/searchimg/search";

		Map<String, Object> paramters = new HashMap<>();
		String rand = "ac41f437-e42d-43e9-87b1-43ed4f420c28";// UUID.randomUUID().toString();
		String machine_no = "0270935186203935917";
		paramters.put("rand", rand);
		paramters.put("ocrFlag", 0);
		paramters.put("machine_no", machine_no);
		paramters.put("userId", "15000158");
		paramters.put("uploadType", uploadType);
		paramters.put("timeFlag", 1);
		paramters.put("grade_id", 3);
		paramters.put("location",
		        "Location [addrStr=null, province=null, city=null, cityCode=null, street=null, streetNumber=null, floor=null, altitude=4.9E-324, coorType=bd09ll, derect=-1.0, direction=-1.0, district=null, latitude=22.53742, locType=66, longitude=114.038082, networkLocationType=null, operators=0, poi=null, radius=246.4, satelliteNumber=-1, speed=0.0, time=2018-07-10 10:20:58]");
		paramters.put("phase_id", 3);
		paramters.put("flag", 2);
		paramters.put("client_key", "481c06cfc878e79d9d83987e4568de78");
		paramters.put("searchFlag", "1");
		String rectArrStr = "[{\"w\":547,\"h\":586,\"y\":0,\"x\":16},"
		        + "{\"w\":579,\"h\":547,\"y\":19,\"x\":0},"
		        + "{\"w\":547,\"h\":390,\"y\":0,\"x\":16}"
		        + ",{\"w\":579,\"h\":364,\"y\":19,\"x\":0},"
		        + "{\"w\":547,\"h\":293,\"y\":100,\"x\":16},"
		        + "{\"w\":273,\"h\":390,\"y\":0,\"x\":16}"
		        + ",{\"w\":274,\"h\":390,\"y\":0,\"x\":289}]";

		rectArrStr = "[{\"y\":0,\"x\":125,\"w\":975,\"h\":719},"
		        + "{\"y\":15,\"x\":1,\"w\":1224,\"h\":689}]";
		paramters.put("rectArray", rectArrStr);

		String securityKey = "DA4B52A9AB43B08232BE0C08467E29D0";
		securityKey += file.length();
		String sign = SignUtil.getSign(url, "POST", securityKey, paramters);
		paramters.put("sign", sign);
		int s = 0;
		url += "?";
		for (Entry<String, Object> entry : paramters.entrySet()) {
			url += entry.getKey() + "=" + URLEncoder.encode(String.valueOf(entry.getValue()), "UTF-8");
			if (s + 1 < paramters.size()) {
				url += "&";
			}
			s++;
		}
		HttpPost httppost = new HttpPost(url);

		MultipartEntity mpEntity = new MultipartEntity(); // 文件传输
		ContentBody cbFile = new FileBody(file);
		mpEntity.addPart("file", cbFile);
		// httppost.addHeader("Accept-Encoding", "gzip,deflate,sdch");

		httppost.setEntity(mpEntity);
		t1 = System.currentTimeMillis();

		HttpResponse response = httpclient.execute(httppost);
		t2 = System.currentTimeMillis();
		HttpEntity resEntity = response.getEntity();
		t3 = System.currentTimeMillis();
		System.out.println(response.getStatusLine());// 通信Ok
		String json = "";
		String path = "";
		if (resEntity != null) {
			String contentEncodingType = "";
			Header[] contentEncodHeader = response.getHeaders("Content-Encoding");
			if (null != contentEncodHeader) {
				for (int i = 0; i < contentEncodHeader.length; i++) {
					if ("gzip".equalsIgnoreCase(contentEncodHeader[i].getValue())) {
						contentEncodingType = "gzip";
					}
				}
			}

			if ("gzip".equalsIgnoreCase(contentEncodingType)) {
				InputStream in = resEntity.getContent();
				// json = uncompress(json, in);
			} else {
				json = EntityUtils.toString(resEntity, "utf-8");
			}
			System.out.println(json);
			t4 = System.currentTimeMillis();

		}
		if (resEntity != null) {
			resEntity.consumeContent();
		}
		end = System.currentTimeMillis();
		httpclient.getConnectionManager().shutdown();
		if ((null != json && !json.equals(""))) {
			JSONObject jobj = new JSONObject(json);
			int msgCode = jobj.optInt("msgCode");
			if (309 == msgCode) {
				JSONObject data = jobj.optJSONObject("data");
				String targetId = data.optString("target_id");
				String targetName = data.optString("name");
				String str = targetId + ":" + targetName + "\r\n";
				System.out.println(str);
			}
		}
		return path;
	}
}
