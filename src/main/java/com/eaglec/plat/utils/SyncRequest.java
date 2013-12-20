package com.eaglec.plat.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;


public class SyncRequest extends Thread{
	
	private static final Logger logger = LoggerFactory.getLogger(SyncRequest.class);
	
	private String uri;
	private String ip;
	private String queryString;

	public SyncRequest(String uri, String ip, String queryString) {
		super();
		this.uri = uri;
		this.ip = ip;
		this.queryString = queryString;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	@Override
	public void run() {
//		URL $matches;
//		String $host="";
//		String $path="";
//		int $port = 80;
//		try {
//			$matches = new URL(uri);
//			$host = $matches.getHost();
//			$path = $matches.getPath()!=null? $matches.getPath()+($matches.getQuery()!=null?"?"+$matches.getQuery():""):"/";
//			if( $matches.getPort()>0 ) $port = $matches.getPort();
//		} catch (MalformedURLException e1) {
//			logger.info(e1.getLocalizedMessage());
//		}
//
//		StringBuffer $out = new StringBuffer();
//		if(queryString != null && queryString.length()>0) {
//			logger.info("请求应用接口["+uri+"]参数为:"+queryString);
//			$out.append("POST ").append($path).append(" HTTP/1.0\r\n");
//			$out.append("Accept: */*\r\n");
//			$out.append("Accept-Language: zh-cn\r\n");
//			$out.append("Content-Type: application/x-www-form-urlencoded\r\n");
//			$out.append("User-Agent: \r\n");
//			$out.append("Host: ").append($host).append("\r\n");
//			$out.append("Content-Length: ").append(queryString.length()).append("\r\n");
//			$out.append("Connection: Close\r\n");
//			$out.append("Cache-Control: no-cache\r\n");
//			$out.append("Cookie: \r\n\r\n");
//			$out.append(queryString);
//		} else {
//			$out.append("GET $path HTTP/1.0\r\n");
//			$out.append( "Accept: */*\r\n");
//			//$out .= "Referer: $boardurl\r\n";
//			$out.append("Accept-Language: zh-cn\r\n");
//			$out.append("User-Agent: Java/1.5.0_01\r\n");
//			$out.append("Host: $host\r\n");
//			$out.append("Connection: Close\r\n");
//			$out.append("Cookie: $cookie\r\n\r\n");
//		}
//	
//		try{
//			Socket $fp = new Socket(ip!=null && ip.length()>10? ip : $host, $port );
//			if(!$fp.isConnected()) {
//				logger.info("请求应用接口["+uri+"]发送数据包:"+$fp.getSendBufferSize());
//			} else {
//
//				OutputStream os = $fp.getOutputStream();
//				os.write($out.toString().getBytes());
//				
//				InputStream ins = $fp.getInputStream();				
//				String responseString = InputStreamUtil.InputStreamTOString(ins, "UTF-8");
//				logger.info("请求应用接口["+uri+"]响应信息:"+responseString);
//				$fp.close();
//			}
//		}catch (IOException e) {
//			logger.info("请求应用接口["+uri+"]异常:"+e.getLocalizedMessage());
//		} catch (Exception e) {
//			logger.info("请求应用接口["+uri+"]异常:"+e.getLocalizedMessage());
//		}
		
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod(uri);
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		getMethod.getParams().setContentCharset("UTF-8");
		getMethod.setQueryString(queryString);
		try {
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				logger.info("同步登陆请求["+uri+"],参数["+queryString+"]失败:"+getMethod.getStatusLine());
			}else{
				InputStream responseBody = getMethod.getResponseBodyAsStream();
				String responseString = InputStreamUtil.InputStreamTOString(responseBody, "UTF-8");
				logger.info("同步登陆请求["+uri+"],参数["+queryString+"]成功,返回信息:"+JSON.toJSONString(responseString, true));
			}
		} catch (HttpException e) {
			logger.info("同步登陆请求["+uri+"],参数["+queryString+"]发生异常:"+e.getLocalizedMessage());
		} catch (IOException e) {
			logger.info("同步登陆请求["+uri+"],参数["+queryString+"]发生异常:"+e.getLocalizedMessage());
		} catch (Exception e) {
			logger.info("同步登陆请求["+uri+"],参数["+queryString+"]发生异常:"+e.getLocalizedMessage());
		} finally {
			getMethod.releaseConnection();
		}
	}
}
