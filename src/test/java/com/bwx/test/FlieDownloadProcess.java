package com.bwx.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class FlieDownloadProcess {

	private static String preUrl = "https://api.cloud.weatherdt.com";
	private static String bucketByNameUri = "/css-biz/v2/bucket/queryByName";

	public static void main(String[] args) {
		
		// TODO 用户修改
		String aid = "WUTSY2BUFKE5PWFA";
		String akey = "EjvVutDy9Gcktzz9xDryN89xyZzPRqpp";
		String bucketName = "yjs-alarm-obs";
		String downloadPathPre = "D:\\user\\file\\";
		try {
			System.out.println("download file start!");
			// 1、获取bucketId 示例
			String bucketId = getBucketIdByName(aid, akey, bucketName);
			// 2、获取可下载的文件信息示例 返回类型为JSONArray，可能为null，需要进行空值判断，避免空指针
			JSONArray downFileIds = getDownFileIds(aid, akey, bucketId);
			for (int i = 0; i < (downFileIds != null ? downFileIds.size() : 0); i++) {
				JSONObject jo = JSON.parseObject(downFileIds.get(i).toString());
				// 3、申请下载示例
				String fileId = jo.getString("fileId");
				String targetFileName = jo.getString("name");
				JSONObject downApply = downApply(fileId, aid, akey, bucketId);
				// 4、执行下载示例
				String downloadURL = downApply.getString("downloadURL");
				String downloadToken = downApply.getString("downloadToken");
				down(downloadURL, downloadToken, downloadPathPre + targetFileName);
			}
			System.out.println("download file end");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static JSONArray getDownFileIds(String aid, String akey, String bucketId) throws Exception {
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("bucketId", bucketId);
		String timestamp = String.valueOf(System.currentTimeMillis());
		String jsonBody = JSONObject.toJSONString(body);
		String sign = sign("/css-biz/v2/file/getDownFiles", aid, akey, timestamp, jsonBody);
		Map<String, String> headers = getHeader(aid, sign, timestamp);
		String ret = httpPostJson(preUrl + "/css-biz/v2/file/getDownFiles", jsonBody, headers);
		JSONObject o = JSONObject.parseObject(ret);
		checkHttpRet(o);
		return o.getJSONArray("files");
	}

	/**
	 * 获取buckId
	 * 
	 * @param aid
	 * @param akey
	 * @param bucketName
	 * @return
	 * @throws Exception
	 */
	private static String getBucketIdByName(String aid, String akey, String bucketName) throws Exception {
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("name", bucketName);
		String timestamp = String.valueOf(System.currentTimeMillis());
		String jsonBody = JSONObject.toJSONString(body);
		String sign = sign("/css-biz/v2/bucket/queryByName", aid, akey, timestamp, jsonBody);
		Map<String, String> headers = getHeader(aid, sign, timestamp);
		String ret = httpPostJson(preUrl + bucketByNameUri, jsonBody, headers);
		JSONObject o = JSONObject.parseObject(ret);
		checkHttpRet(o);
		return o.getJSONObject("bucketInfo").getString("bucketId");
	}

	/**
	 * 下载申请
	 * 
	 * @param fileId
	 * @param aid
	 * @param akey
	 * @param bucketId
	 * @return
	 * @throws Exception
	 */
	private static JSONObject downApply(String fileId, String aid, String akey, String bucketId) throws Exception {
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("bucketId", bucketId);
		body.put("fileID", fileId);
		String timestamp = String.valueOf(System.currentTimeMillis());
		String jsonBody = JSONObject.toJSONString(body);
		String downApplyUri = "/css-biz/v2/file/applyDownload";
		String sign = sign(downApplyUri, aid, akey, timestamp, jsonBody);
		Map<String, String> headers = getHeader(aid, sign, timestamp);
		String ret = httpPostJson(preUrl + downApplyUri, jsonBody, headers);
		JSONObject o = JSONObject.parseObject(ret);
		checkHttpRet(o);
		return o;
	}

	/**
	 * 下载文件
	 * 
	 * @param downloadURL
	 * @param downloadToken
	 * @param targetFileName
	 * @throws Exception
	 */
	private static void down(String downloadURL, String downloadToken, String targetFileName) throws Exception {
		File file = new File(targetFileName);
		if (file.getParentFile() != null) {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
		}
		Map<String, String> body = new HashMap<String, String>();
		body.put("downloadToken", downloadToken);
		String timestamp = String.valueOf(System.currentTimeMillis());
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("timestamp", timestamp);
		headers.put("language", "zh-cn");
		headers.put("Content-Type", "application/json");
		httpPostDownFile(downloadURL, targetFileName, body, headers);
	}

	private static Map<String, String> getHeader(String aid, String sign, String timestamp) throws Exception {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("AID", aid);
		headers.put("sign", sign);
		headers.put("timestamp", timestamp);
		headers.put("language", "zh-cn");
		headers.put("Content-Encoding", "utf-8");
		headers.put("Content-type", "application/json");
		return headers;
	}

	private static void checkHttpRet(JSONObject o) {
		String code = o.getString("retCode");
		if (!"00000".equals(code)) {
			throw new RuntimeException(o.getString("retInfo"));
		}
	}

	private static CloseableHttpClient createSSLClientDefault() {

        try {
            X509TrustManager x509m = new X509TrustManager() {

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }

                @Override
                public void checkClientTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }
            };
            // 获取一个SSLContext实例
            SSLContext sslContext = SSLContext.getInstance("SSL");
            // 初始化SSLContext实例
            sslContext.init(null, new TrustManager[] { x509m }, new java.security.SecureRandom());

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new HostnameVerifier() {
                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }
            });

            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (Exception e) {
            throw new RuntimeException("get CloseableHttpClient error:", e);
        }

    }

	
	
	/**
	 * 发送请求
	 * 
	 * @param url
	 * @param jsonBody
	 * @param headers
	 * @return
	 */
	private static String httpPostJson(String url, String jsonBody, Map<String, String> headers) {
		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = createSSLClientDefault();
		// 创建httppost
		HttpPost httppost = new HttpPost(url);
		setHeaders(httppost, headers);
		setTimeout(httppost);

		try {
			httppost.setEntity(new StringEntity(jsonBody, Charset.forName("UTF-8")));
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				if (response.getStatusLine().getStatusCode() == 200) {
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						String result = EntityUtils.toString(response.getEntity());
						return result;
					} else {
						return null;
					}
				}
				throw new RuntimeException(
						"response status error:" + response.getStatusLine().getStatusCode() + " url:" + url);
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			throw new RuntimeException("ClientProtocol exception url:" + url, e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("UnsupportedEncodingException exception url:" + url, e);
		} catch (IOException e) {
			throw new RuntimeException("IOException exception url:" + url, e);
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				throw new RuntimeException("IOException when close httpclient, url:" + url, e);
			}
		}
	}

	private static void setHeaders(HttpRequestBase req, Map<String, String> headers) {
		if (headers != null && headers.size() > 0) {
			for (String key : headers.keySet()) {
				req.addHeader(key, headers.get(key));
			}
		}
	}

	private static void setTimeout(HttpRequestBase req) {
		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(30000).setConnectTimeout(30000)
				.setSocketTimeout(30000).build();
		req.setConfig(requestConfig);
	}

	private static void httpPostDownFile(String url, String downPath, Map<String, String> params,
			Map<String, String> headers) {
		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = createSSLClientDefault();
		// 创建httppost
		HttpPost httppost = new HttpPost(url);
		setHeaders(httppost, headers);

		// 创建参数队列
		JSONObject json = new JSONObject();
		for (String key : params.keySet()) {
			json.put(key, params.get(key));
		}
		try {
			httppost.setEntity(new StringEntity(json.toJSONString(), Charset.forName("UTF-8")));
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				if (response.getStatusLine().getStatusCode() == 200) {
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						InputStream is = entity.getContent();
						FileOutputStream fos = new FileOutputStream(new File(downPath));
						int inByte;
						while ((inByte = is.read()) != -1) {
							fos.write(inByte);
						}
						is.close();
						fos.close();
						return;
					} else {
						throw new RuntimeException(
								"response conten is null:" + response.getStatusLine().getStatusCode() + " url:" + url);
					}
				}
				throw new RuntimeException(
						"response status error:" + response.getStatusLine().getStatusCode() + " url:" + url);
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			throw new RuntimeException("ClientProtocol exception url:" + url, e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("UnsupportedEncodingException exception url:" + url, e);
		} catch (IOException e) {
			throw new RuntimeException("IOException exception url:" + url, e);
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				throw new RuntimeException("IOException when close httpclient, url:" + url, e);
			}
		}
	}

	private static String sign(String url, String aid, String akey, String timestamp, String body) {
		aid = aid.trim();
		akey = akey.replaceAll("\"", "");
		if (body != null) {
			body = body.trim();
		}
		if (body != null && !"".equals(body)) {
			body = body.replaceAll(" ", "");
			body = body.replaceAll("\t", "");
			body = body.replaceAll("\r", "");
			body = body.replaceAll("\n", "");
		}

		StringBuffer sb = new StringBuffer();
		if (!url.contains("openapi")) {
			sb.append(url);
		}
		if (body != null && !"".equals(body)) {
			sb.append(body);
		}
		sb.append(aid).append(akey).append(timestamp);
		MessageDigest md = null;
		byte[] bytes = null;
		try {
			md = MessageDigest.getInstance("sha-256");
			bytes = md.digest(sb.toString().getBytes("utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return BinaryToHexString(bytes);
	}

	private static String BinaryToHexString(byte[] bytes) {
		StringBuilder hex = new StringBuilder();
		String hexStr = "0123456789abcdef";
		for (int i = 0; i < bytes.length; i++) {
			hex.append(String.valueOf(hexStr.charAt((bytes[i] & 0xF0) >> 4)));
			hex.append(String.valueOf(hexStr.charAt(bytes[i] & 0x0F)));
		}
		return hex.toString();
	}

}
