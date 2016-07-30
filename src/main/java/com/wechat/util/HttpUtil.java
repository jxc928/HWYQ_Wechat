package com.wechat.util;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

public class HttpUtil {

	/**
	 * http get 请求方法
	 * 
	 * @param reqUrl
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String sendGet(String reqUrl, Map<String, String> params) throws Exception {
		InputStream inputStream = null;
		HttpGet request = new HttpGet();
		try {
			String url = buildUrl(reqUrl, params);
			HttpClient client = new DefaultHttpClient();

			// 设置内容压缩形式
			request.setHeader("Accept-Encoding", "gzip");
			request.setURI(new URI(url));

			HttpResponse response = client.execute(request);

			inputStream = response.getEntity().getContent();
			String result = getJsonStringFromGZIP(inputStream);

			return result;
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			request.releaseConnection();
		}
	}

	/**
	 * http post 请求方法
	 * 
	 * @param reqUrl
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String sendPost(String reqUrl, Map<String, String> params) throws Exception {
		try {
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			for (String key : params.keySet()) {
				list.add(new BasicNameValuePair(key, params.get(key)));
			}
			if (list.size() > 0) {
				try {
					HttpClient client = new DefaultHttpClient();
					HttpPost request = new HttpPost(reqUrl);

					request.setHeader("Accept-Encoding", "gzip");
					request.setEntity(new UrlEncodedFormEntity(list, HTTP.UTF_8));

					HttpResponse response = client.execute(request);

					InputStream inputStream = response.getEntity().getContent();
					try {
						String result = getJsonStringFromGZIP(inputStream);

						return result;
					} finally {
						inputStream.close();
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					throw new Exception("网络连接失败,请连接网络后重试!");
				}
			} else {
				throw new Exception("参数不全，请稍后重试!");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("发送: 未知异常!");
		}
	}

	/**
	 * 将得到的结果输入流转换为json字符串
	 * 
	 * @param is
	 * @return
	 */
	public static String getJsonStringFromGZIP(InputStream is) {
		String jsonString = null;
		try {
			BufferedInputStream bis = new BufferedInputStream(is);
			/*
			 * 设置输入流读取限制做到只读取前两个字节，以判断是否为Gzip格式
			 */
			bis.mark(2);
			byte[] header = new byte[2];
			int result = bis.read(header);
			// reset输入流到开始位置
			bis.reset();

			int headerData = getShort(header);
			if (result != -1 && headerData == 0x1f8b) {// Gzip流的前两个字节是 0x1f8b
				is = new GZIPInputStream(bis);
			} else {
				is = bis;
			}

			InputStreamReader reader = new InputStreamReader(is, "utf-8");
			char[] data = new char[100];
			int readSize;
			StringBuffer sb = new StringBuffer();
			while ((readSize = reader.read(data)) > 0) {
				sb.append(data, 0, readSize);
			}
			jsonString = sb.toString();

			bis.close();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonString;
	}

	private static int getShort(byte[] data) {
		return (data[0] << 8) | data[1] & 0xFF;
	}

	/**
	 * 构建get方式的url
	 * 
	 * @param reqUrl
	 * @param params
	 * @return
	 */
	public static String buildUrl(String reqUrl, Map<String, String> params) {
		StringBuilder query = new StringBuilder();
		int paramsSize = params.size();
		for (String key : params.keySet()) {
			if(paramsSize <= 1){
				query.append(String.format("%s=%s", key, params.get(key)));
			}else{
				query.append(String.format("%s=%s&", key, params.get(key)));				
			}
			paramsSize--;
		}
		return reqUrl + "?" + query.toString();
	}
}
