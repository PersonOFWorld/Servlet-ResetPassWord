package com.hjj.test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import Decoder.BASE64Encoder;

public class Dao {

	// 获取cookie
	public String getCookie(String LOGIN_URL) {
		String cookieValue = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 将每个用户的cookie与登录省份唯一绑定,用系统时间做key，cookie值做value
		try {
			HttpGet httpGet = new HttpGet(LOGIN_URL);
			CloseableHttpResponse response = httpclient.execute(httpGet);
			Header[] headers = response.getAllHeaders();
			for (Header s : headers) {
				if (s.getName().equals("Set-Cookie")) {
					cookieValue = s.getValue();
				}
			}
			if (cookieValue != null) {
				String[] strs = cookieValue.split(";");
				cookieValue = strs[0];
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cookieValue;
	}

	// 获取验证码
	public String getPng(String LOGIN_URL, String CAPTCHA_URL, String USER_AGENT,String path) {
		BufferedReader in = null;
		String cookies = getCookie(LOGIN_URL);
		String img_path_url = CAPTCHA_URL;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpGet = new HttpGet(img_path_url);
			httpGet.setHeader("User-Agent", USER_AGENT);
			httpGet.setHeader("Cookie", cookies);
			httpGet.setHeader("Referer", "****");
			httpGet.setHeader("Origin", "****");
			CloseableHttpResponse response = httpclient.execute(httpGet);
			// 保存图片
			download(response.getEntity().getContent(), path);
			Header[] headers = response.getAllHeaders();
			for (Header s : headers) {
				if (s.getName().equals("Set-Cookie")) {
					cookies = s.getValue();
				}
			}
			if (cookies != null) {
				String[] strs = cookies.split(";");
				cookies = strs[0];
				return cookies;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return null;
	}

	// 模拟登录提交表单
	public boolean postForm(String schoolnum, String password, String validate, String userCookie,
			String USER_AGENT) throws ClientProtocolException, IOException {
		String uriAPI = "****";// Post方式没有参数在这里
		HttpPost httpRequst = new HttpPost(uriAPI);// 创建HttpPost对象
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 提交数据
		// 设置请求头
		httpRequst.setHeader("Host", "****");
		httpRequst.setHeader("User-Agent", USER_AGENT);
		httpRequst.setHeader("Accept", " text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		httpRequst.setHeader("Content-Type", "application/x-www-form-urlencoded");
		httpRequst.setHeader("Referer", "****");
		httpRequst.setHeader("Origin", "****");
		httpRequst.setHeader("Cookie", userCookie);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		String keyWord = URLDecoder.decode("%D1%A7%C9%FA", "gb2312");
		params.add(new BasicNameValuePair("__VIEWSTATE", "dDwyODE2NTM0OTg7Oz5LUCaVfG1Oi+QaOSKH9UZrpjfn1w=="));
		params.add(new BasicNameValuePair("txtUserName", schoolnum));
		params.add(new BasicNameValuePair("TextBox2", password));
		params.add(new BasicNameValuePair("txtSecretCode", validate));
		params.add(new BasicNameValuePair("RadioButtonList1", keyWord));
		params.add(new BasicNameValuePair("Button1", ""));
		params.add(new BasicNameValuePair("lbLanguage", ""));
		params.add(new BasicNameValuePair("hidPdrs", ""));
		params.add(new BasicNameValuePair("hidsc", ""));
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params);
		/* System.out.println("表单1："+params.get(0)); */
		httpRequst.setEntity(entity);
		HttpResponse httpResponse = httpclient.execute(httpRequst);
		String regEx = "xs_main.aspx?x"; // 表示a或f
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(httpResponse.toString());
		boolean info = m.find();
		return info;
	}

	// 保存验证码图片到指定的位置
	public static boolean download(InputStream in, String path) {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(path);
			byte b[] = new byte[1024];
			int j = 0;
			while ((j = in.read(b)) != -1) {
				out.write(b, 0, j);
			}
			out.flush();
			File file = new File(path);
			if (file.exists() && file.length() == 0)
				return false;
			return true;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			if ("FileNotFoundException".equals(e.getClass().getSimpleName()))
				System.err.println("download FileNotFoundException");
			if ("SocketTimeoutException".equals(e.getClass().getSimpleName()))
				System.err.println("download SocketTimeoutException");
			else
				e.printStackTrace();
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return false;
	}

	public static void deletePng(String url) {
		File f = new File(url);
		if (f.exists()) {
			f.delete();
		}
	}
}
