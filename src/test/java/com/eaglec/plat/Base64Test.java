package com.eaglec.plat;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

public class Base64Test {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	public static void main(String[] args) throws UnsupportedEncodingException{
		System.out.println(Base64.encodeBase64URLSafeString("lizhiwei123123123Base64加密解密测试".getBytes("utf-8"))+"\n");
		System.out.println(new String(Base64.decodeBase64("SGksSSBhbSBoZXJl")));
	}
	
}
