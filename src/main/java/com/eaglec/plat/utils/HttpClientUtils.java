package com.eaglec.plat.utils;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 * 封装了Apache的项目HttpComponents的使用方法
 * @author liuliping
 * @since 2013-12-05
 * */
public class HttpClientUtils {

	public static final void post(String url, List <NameValuePair> nvps) throws Exception{
		 CloseableHttpClient httpclient = HttpClients.createDefault();
	        try {
	            HttpPost httpPost = new HttpPost(url);
	            if(nvps != null && nvps.size() > 0) {
	            	httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
	            }
	            CloseableHttpResponse response2 = httpclient.execute(httpPost);

	            try {
	                System.out.println(response2.getStatusLine());
	                HttpEntity entity2 = response2.getEntity();
	                // do something useful with the response body
	                // and ensure it is fully consumed
	                EntityUtils.consume(entity2);
	            } finally {
	                response2.close();
	            }
	        } finally {
	            httpclient.close();
	        }
	}
	
	
	public static final void get(String url) throws Exception{
		 CloseableHttpClient httpclient = HttpClients.createDefault();
	        try {
	            HttpGet httpGet = new HttpGet(url);
	            CloseableHttpResponse response1 = httpclient.execute(httpGet);
	            // The underlying HTTP connection is still held by the response object
	            // to allow the response content to be streamed directly from the network socket.
	            // In order to ensure correct deallocation of system resources
	            // the user MUST either fully consume the response content  or abort request
	            // execution by calling CloseableHttpResponse#close().
	            try {
	                System.out.println(response1.getStatusLine());
	                HttpEntity entity1 = response1.getEntity();
	                // do something useful with the response body
	                // and ensure it is fully consumed
	                EntityUtils.consume(entity1);
	            } finally {
	                response1.close();
	            }
	        } finally {
	            httpclient.close();
	        }
	}
	
	public static void main(String[] args) {
		try {
			HttpClientUtils.post("http://localhost:80/", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
