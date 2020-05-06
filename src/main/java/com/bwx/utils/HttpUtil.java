package com.bwx.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

public final class HttpUtil {
	private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

	public static final int connectionTimeout = 3000;
	public static final int soTimeout = 5000;
	public static final int soLinger = 60;

	/**
	 * 抓取地址下所有内容
	 *
	 * @param url
	 * @return
	 */
	public static String getUrlContent(String url) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(url);
		HttpResponse response;
		StringBuffer sbsbuffer = new StringBuffer();
		try {
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				InputStream is = entity.getContent();
				int len = 0;
				try {
					while ((len = is.read()) != -1) {
						sbsbuffer.append((char) len);
					}
				} catch (Exception e) {
					System.out.println("获取评论出错");
				}
			}
			httpget.abort();

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return sbsbuffer.toString();
	}

	public static String getUrlContent(String url, String cookies, String code) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter("http.socket.timeout",5000);
		HttpGet httpget = new HttpGet(url);
		httpget.setHeader("Cookie", cookies);
		HttpResponse response;
		StringBuffer sbsbuffer = new StringBuffer();
		try {
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				InputStream is = entity.getContent();
				int len = 0;
				try {
					while ((len = is.read()) != -1) {
						sbsbuffer.append((char) len);
					}
				} catch (Exception e) {
					System.out.println("HTTP抓取出错");
				}
			}
			httpget.abort();

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			httpclient.getConnectionManager().shutdown();
		}
		try {
			return new String(sbsbuffer.toString().getBytes("ISO-8859-1"), code).trim();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}return "";
	}

	/**
	 * 将节点转换为字符串
	 *
	 * @param node
	 * @return
	 */
	public static String toHTML(Node node) {
		StringBuilder sb = new StringBuilder();
		toHTML(sb, node, null);
		return sb.toString();
	}

	public static String toHTML(Node node, String channel) {
		StringBuilder sb = new StringBuilder();
		toHTML(sb, node, channel);
		return sb.toString();
	}

	/**
	 * get方法获取远程网页信息
	 *
	 * @param url
	 * @param contentEncode
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getMethod(String url, String contentEncode, boolean ishttps) {
		HttpParams httpParameters = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters, connectionTimeout);
		HttpConnectionParams.setSoTimeout(httpParameters, soTimeout);
		HttpConnectionParams.setLinger(httpParameters, soLinger);

		HttpClient httpClient = null;
		if(ishttps){
			httpClient = wrapClient(httpParameters);
		}else{
			httpClient = new DefaultHttpClient(httpParameters);
		}
		String html = "异常访问";
		try {
			HttpGet httpget = new HttpGet(url);
			httpget.addHeader("User-Agent", "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; WOW64; Trident/6.0; MAMIJS)");
			HttpResponse response = httpClient.execute(httpget);
			HttpEntity httpEntity = response.getEntity();
			if (httpEntity != null) {
				html = EntityUtils.toString(httpEntity, contentEncode);
				httpEntity.consumeContent();
			}
			httpget.abort();
		} catch (ClientProtocolException e) {
			System.out.println("远程调用链接:" + e.getMessage());
		} catch (IOException e) {
			System.out.println("远程调用链接:" + e.getMessage());
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return html;
	}

	public static String getMethod(String url, Header... headers) throws ClientProtocolException, IOException {
		HttpParams httpParameters = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters, 10000);
		HttpConnectionParams.setSoTimeout(httpParameters, 45000);
		HttpConnectionParams.setLinger(httpParameters, 60);
		DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
		String html = "";
		try {
			HttpGet httpGet = new HttpGet(url);
			httpGet.setHeaders(headers);
			BasicResponseHandler responseHandler = new BasicResponseHandler();
			html = httpClient.execute(httpGet, responseHandler);
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return html;
	}


	public static HttpClient wrapClient(HttpParams hp) {
		try {
			SSLContext ctx = SSLContext.getInstance("TLS");
			X509TrustManager tm = new X509TrustManager() {
				public X509Certificate[] getAcceptedIssuers() {return null;}
				public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}
				public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}
			};
			ctx.init(null, new TrustManager[] { tm }, null);
			SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("https", 443, ssf));
			ThreadSafeClientConnManager mgr = new ThreadSafeClientConnManager(registry);
			return new DefaultHttpClient(mgr, hp);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	private static void toHTML(StringBuilder sb, Node node, String channel) {
		if (node == null) {
			return;
		}
		int type = node.getNodeType();
		switch (type) {

			case Node.DOCUMENT_NODE: {
				NodeList children = node.getChildNodes();
				if (children != null) {
					int len = children.getLength();
					for (int i = 0; i < len; i++)
						toHTML(sb, children.item(i), channel);
				}
				break;
			}

			// print element with attributes
			case Node.ELEMENT_NODE: {
				sb.append("<");
				sb.append(node.getNodeName());
				NamedNodeMap attrs = node.getAttributes();
				for (int i = 0; i < attrs.getLength(); i++) {
					Node attr = attrs.item(i);
					if (channel != null && node.getNodeName().equalsIgnoreCase("a")
							&& attr.getNodeName().equalsIgnoreCase("href")
							&& attr.getNodeValue().indexOf("search.jhtml") == -1) {
						try {
							sb.append(" " + attr.getNodeName() + "=\"/other.do?go=gozf&channel=" + channel + "&url="
									+ java.net.URLEncoder.encode(attr.getNodeValue(), "GBK") + "\"");
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						} catch (DOMException e) {
							e.printStackTrace();
						}
					} else {
						sb.append(" " + attr.getNodeName() + "=\"" + attr.getNodeValue() + "\"");
					}

				}

				if (!node.hasChildNodes()) {
					sb.append("/>");
					return;
				}
				sb.append(">");

				NodeList children = node.getChildNodes();
				if (children != null) {
					int len = children.getLength();
					for (int i = 0; i < len; i++)
						toHTML(sb, children.item(i), channel);
				}
				break;
			}

			// handle entity reference nodes
			case Node.ENTITY_REFERENCE_NODE: {
				sb.append("&").append(node.getNodeName()).append(";");
				break;
			}

			// print cdata sections
			case Node.CDATA_SECTION_NODE: {
				sb.append("<![CDATA[").append(node.getNodeValue()).append("]]>");
				break;
			}

			// print text
			case Node.TEXT_NODE: {
				sb.append(node.getNodeValue());
				break;
			}
		}

		if (type == Node.ELEMENT_NODE) {
			sb.append("</");
			sb.append(node.getNodeName());
			sb.append(">");
		}
	}
	public static String postMethod(String url, HttpEntity httpParameterEntity) {
		return postMethod( url,  httpParameterEntity,  connectionTimeout , soTimeout, soLinger, "");
	}

	public static String postParams(String url, Map<String, Object> params) {
		return postMethod( url,  getPostValue2(params),  connectionTimeout , soTimeout, soLinger, "");
	}

	public static String postMethod(String url, HttpEntity httpParameterEntity, String cookie) {
		return postMethod( url,  httpParameterEntity,  connectionTimeout , soTimeout, soLinger, cookie);
	}

	/**
	 * 使用post方法获取远程网页信息,支持301，302等跳转<br />
	 *
	 * List<BasicNameValuePair> list=new ArrayList<BasicNameValuePair>(); <br />
	 * list.add(new BasicNameValuePair("UserName","sxdxdx")); <br />
	 * list.add(new BasicNameValuePair("PassWord","sxdxdx"));<br />
	 * UrlEncodedFormEntity httpParameterEntity = new UrlEncodedFormEntity(list,"UTF-8");<br />
	 *
	 * 如果网页信息有编码信息按编码信息将返回的内容编码，如果没有则使用ISO-8859-1来进行编码
	 *
	 * @param url
	 * @param httpParameterEntity StringEntity,UrlEncodedFormEntity等类
	 * @return
	 */
	public static String postMethod(String url, HttpEntity httpParameterEntity, int connectionTimeout ,int soTimeout,int soLinger, String cookie) {
		HttpParams httpParameters = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters, connectionTimeout);
		HttpConnectionParams.setSoTimeout(httpParameters, soTimeout);
		HttpConnectionParams.setLinger(httpParameters, soLinger);
		HttpProtocolParams.setUserAgent(httpParameters, "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; WOW64; Trident/6.0; MAMIJS)");
		DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
		String html = "";
		try {
			HttpPost httpPost = new HttpPost(url);
			if (httpParameterEntity != null) {
				httpPost.setEntity(httpParameterEntity);
			}
			if(StringUtils.isNotBlank(cookie))
				httpPost.setHeader("Cookie", cookie);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				HttpEntity httpEntity = httpResponse.getEntity();
				if (httpEntity != null) {
					html = EntityUtils.toString(httpEntity);
					EntityUtils.consume(httpEntity);
				}
				return html;
			}
			httpPost.abort();

			if (statusCode == HttpStatus.SC_MOVED_TEMPORARILY || statusCode == HttpStatus.SC_MOVED_PERMANENTLY
					|| statusCode == HttpStatus.SC_SEE_OTHER || statusCode == HttpStatus.SC_TEMPORARY_REDIRECT) {
				Header header = httpResponse.getFirstHeader("location");
				if (header == null) {
					return html;
				}
				String newuri = header.getValue();
				if ((newuri == null) || (newuri.equals(""))) {
					newuri = "/";
				}
				HttpGet httpGet = new HttpGet(newuri);
				httpResponse = httpClient.execute(httpGet);
				HttpEntity httpEntity = httpResponse.getEntity();
				if (httpEntity != null) {
					html = EntityUtils.toString(httpEntity);
					EntityUtils.consume(httpEntity);
				}
				return html;
			}
		} catch (Exception e) {
			logger.info("httpclient post error: {} {} ", url, e.getMessage());
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return html;
	}
	public static MultipartEntity getPostValue2(Map<String, Object> map){
		MultipartEntity multipartEntity = null;
		try {
			Charset charset = Charset.forName("utf-8");
			multipartEntity = new MultipartEntity(HttpMultipartMode.STRICT, null, charset);
			for(Map.Entry<String, Object> kv : map.entrySet()){
				multipartEntity.addPart(kv.getKey(), new StringBody(String.valueOf(kv.getValue()), charset));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return multipartEntity;
	}

	/**
	 * 通用请求格式转换
	 * @param httpServletRequest
	 * @return
	 */
	public static Map<String, String> commonHttpRequestParamConvert(HttpServletRequest httpServletRequest) {
		Map<String, String> params = new HashMap<>();
		try {
			Map<String, String[]> requestParams = httpServletRequest.getParameterMap();
			if (requestParams != null && !requestParams.isEmpty()) {
				requestParams.forEach((key, value) -> params.put(key, value[0]));
			} else {
				StringBuilder paramSb = new StringBuilder();
				try {
					String str = "";
					BufferedReader br = httpServletRequest.getReader();
					while((str = br.readLine()) != null){
						paramSb.append(str);
					}
				} catch (Exception e) {
					System.out.println("httpServletRequest get requestbody error, cause : " + e);
				}
				if (paramSb.length() > 0) {
					JSONObject paramJsonObject = JSON.parseObject(paramSb.toString());
					if (paramJsonObject != null && !paramJsonObject.isEmpty()) {
						paramJsonObject.forEach((key, value) -> params.put(key, String.valueOf(value)));
					}
				}
			}
		} catch (Exception e) {
			System.out.println("commonHttpRequestParamConvert error, cause : " + e);
		}
		return params;
	}

}
