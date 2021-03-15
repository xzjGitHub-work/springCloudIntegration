package com.tech.eclouds.xzj.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;

/**
 * HttpClientUtil
 * @author zhangliang
 * @version 1.0.0
 */
public class HttpClientUtil {

    private static String CHARSET_UTF8 = "UTF-8";

    private HttpClientUtil() {}

    public static String get(String url) throws IOException, URISyntaxException {
        return get(url, null, null, null);
    }

    public static String get(String url, Map<String, Object> params) throws URISyntaxException, IOException {
        return get(url, null, params, null);
    }

    public static String get(String url, Map<String, String> headers, Map<String, Object> params, HttpClient httpClient)
            throws URISyntaxException, IOException {
        URIBuilder ub = new URIBuilder(url);

        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        if (pairs != null) {
            ub.setParameters(pairs);
        }

        HttpGet httpGet = new HttpGet(ub.build());
        if (headers != null) {
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				httpGet.addHeader(entry.getKey(), String.valueOf(entry.getValue()));
			}
        }
        return getResult(httpGet, httpClient);
    }

    public static String post(String url, Map<String, Object> params) throws IOException {
        return post(url, null, params, null);
    }

	public static String post(String url, JSONObject params) throws IOException {
		return post(url, null, params, null);
	}

    public static String post(String url, Map<String, String> headers, Map<String, Object> params, HttpClient httpClient)
            throws IOException {
        HttpPost httpPost = new HttpPost(url);

        if (headers != null) {
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				httpPost.addHeader(entry.getKey(), String.valueOf(entry.getValue()));
			}
        }

        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        if (pairs != null) {
            httpPost.setEntity(new UrlEncodedFormEntity(pairs, CHARSET_UTF8));
        }
        return getResult(httpPost, httpClient);
    }

	public static String post(String url, Map<String, String> headers, JSONObject params, HttpClient httpClient)
		throws IOException {
		HttpPost httpPost = new HttpPost(url);

		if (headers != null) {
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				httpPost.addHeader(entry.getKey(), String.valueOf(entry.getValue()));
			}
		}

		if(params != null){
			String inputParam = params.toJSONString();
			httpPost.setEntity(new StringEntity(inputParam, CHARSET_UTF8));
		}

		return getResult(httpPost, httpClient);
	}

    public static ArrayList<NameValuePair> covertParams2NVPS(Map<String, Object> params) {
        if (params != null) {
            ArrayList<NameValuePair> pairs = new ArrayList<>();
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				pairs.add(new BasicNameValuePair(entry.getKey(),entry.getValue() == null ? null : String.valueOf(entry.getValue())));
			}
            return pairs;
        }
        return null;
    }

    /**
     * 处理Http请求
     */
    public static String getResult(HttpRequestBase request, HttpClient httpClient) throws IOException {
        if (httpClient == null) {
            httpClient = HttpClientHelper.getInstance().getHttpClient();
        }
        CloseableHttpResponse response = (CloseableHttpResponse)httpClient.execute(request);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            // long len = entity.getContentLength();// -1 表示长度未知
			String result = EntityUtils.toString(entity,"UTF-8");
            response.close();
            return result;
        }
        return null;
    }

    /**
     * 带参数的post请求
     *
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
    public static HttpResult doPost(String url, Map<String, Object> map) throws Exception {
        String result = post(url, map);
        return new HttpResult(200, JSONObject.parseObject(result));
    }

    /**
     * 不带参数post请求
     *
     * @param url
     * @return
     * @throws Exception
     */
    public HttpResult doPost(String url) throws Exception {
        return doPost(url, null);
    }

}