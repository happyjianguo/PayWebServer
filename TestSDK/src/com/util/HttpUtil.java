package com.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

public class HttpUtil {


	/**
	 * 
	 * ����http����
	 * 
	 * @param requestUrl
	 *            �����url
	 * @param paramMap
	 *            ����������Ҫ���Ĳ���
	 * @return
	 * @throws FrameException
	 */
	public static String httpRequest(String requestUrl, Map<String, String> paramMap)
			throws Exception {
		System.out.println("http�����ַ:"+requestUrl);

		StringBuffer buffer = new StringBuffer();

		try {

			URL url = new URL(requestUrl);

			Proxy proxy = null;

			HttpURLConnection httpURLConnection = null;
			if (proxy == null) {
				httpURLConnection = (HttpURLConnection) url.openConnection();
			} else {
				httpURLConnection = (HttpURLConnection) url.openConnection(proxy);
			}

			// Post ������ʹ�û���
			httpURLConnection.setUseCaches(false);
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setConnectTimeout(30000);
			httpURLConnection.setReadTimeout(300000);
			httpURLConnection.connect();
			// ���ӣ���������2����url.openConnection()���˵����ñ���Ҫ��connect֮ǰ���
			httpURLConnection.connect();
			// �˴�getOutputStream�������Ľ���connect(������ͬ���������connect()������
			// �����ڿ����в�����������connect()Ҳ����)��
			OutputStream outStrm = httpURLConnection.getOutputStream();
			StringBuffer params = new StringBuffer();
			if (paramMap != null) {
				int i = 0;
				for (Entry<String, String> entry : paramMap.entrySet()) {
					if (i > 0) {
						params.append("&");
					}
					;
					params.append(entry.getKey() + "=" + entry.getValue());
					i++;
				}
			}
			System.out.println("http������:"+params.toString());
			outStrm.write(params.toString().getBytes("utf-8"));
			outStrm.flush();
			outStrm.close();

			// �����ص�������ת�����ַ���
			InputStream inputStream = httpURLConnection.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// �ͷ���Դ
			inputStream.close();
			inputStream = null;
			httpURLConnection.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("http���ر���:"+buffer.toString());
		return buffer.toString();
	}
	
	public static String httpRequest(String requestUrl, String msg) throws Exception {
		
		StringBuffer buffer = new StringBuffer();
		
		try {
			
			URL url = new URL(requestUrl);
			
			Proxy proxy = null;
			
			HttpURLConnection httpURLConnection = null;
			if (proxy == null) {
				httpURLConnection = (HttpURLConnection) url.openConnection();
			} else {
				httpURLConnection = (HttpURLConnection) url.openConnection(proxy);
			}
			
			// Post ������ʹ�û���
			httpURLConnection.setUseCaches(false);
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setConnectTimeout(30000);
			httpURLConnection.setReadTimeout(300000);
			httpURLConnection.connect();
			// ���ӣ���������2����url.openConnection()���˵����ñ���Ҫ��connect֮ǰ���
			httpURLConnection.connect();
			// �˴�getOutputStream�������Ľ���connect(������ͬ���������connect()������
			// �����ڿ����в�����������connect()Ҳ����)��
			OutputStream outStrm = httpURLConnection.getOutputStream();
			
			outStrm.write(msg.toString().getBytes("utf-8"));
			outStrm.flush();
			outStrm.close();
			
			// �����ص�������ת�����ַ���
			InputStream inputStream = httpURLConnection.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// �ͷ���Դ
			inputStream.close();
			inputStream = null;
			httpURLConnection.disconnect();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return buffer.toString();
	}


	public static void postJson(String url,String content) {

		try {
			//��������
			URL u = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) u
					.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");

			connection.connect();

			//POST����
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());


			out.writeBytes(content);
			out.flush();
			out.close();

			//��ȡ��Ӧ
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String lines;
			StringBuffer sb = new StringBuffer("");
			while ((lines = reader.readLine()) != null) {
				lines = new String(lines.getBytes(), "utf-8");
				sb.append(lines);
			}
			System.out.println(sb);
			reader.close();
			// �Ͽ�����
			connection.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
