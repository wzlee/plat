package com.eaglec.plat.utils;

import java.net.MalformedURLException;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class MailUtil {
	private final String hostName = "smtp.exmail.qq.com";
//	private final int smtpPort = 25;
	private final String userName = "admin@pservice.cn";
	private final String password = "密码";
	private final String formEmail = "bmsoft2013";
	private final String nickName = "中小企业公共服务平台";
	private final String textMsg = "对不起,您的邮箱不支持HTML格式的邮件!";
	private final String chartSet = "utf-8";
	
//	public static void TextMail() throws EmailException {
//		Email email = new SimpleEmail();
////		email.setDebug(true);
//		email.setHostName("smtp.qq.com");
//		email.setSmtpPort(25);
//		email.setAuthenticator(new DefaultAuthenticator("账号", "密码"));
//		email.setSSLOnConnect(true);
//		email.setFrom("账号@qq.com");
//		email.setSubject("TestMail");
//		email.setMsg("This is a test mail ... :-)");
//		email.addTo("账号@qq.com");
//		email.send();
//	}
//
//	public void AttachmentMail() throws EmailException {
//		// Create the attachment
//		EmailAttachment attachment = new EmailAttachment();
//		attachment.setPath("src/main/webapp/resources/images/hgm123_logo_s.png");
//		attachment.setDisposition(EmailAttachment.ATTACHMENT);
//		attachment.setDescription("Picture of John");
//		attachment.setName("Hgm123.png");
//
//		// Create the email message
//		
//		MultiPartEmail email = new MultiPartEmail();
//		email.setDebug(true);
//		email.setHostName("smtp.qq.com");
//		email.setAuthenticator(new DefaultAuthenticator("账号", "密码"));
//		email.setSSLOnConnect(true);
//		email.addTo("账号@qq.com", "Player");
//		email.setFrom("账号@qq.com","Hgm123");
//		email.setSubject("The picture");
//		email.setMsg("Here is the picture you wanted");
//
//		// add the attachment
//		email.attach(attachment);
//
//		// send the email
//		email.send();
//	}

	public void HTMLMail(String recever,String subject,String content) throws EmailException, MalformedURLException {
		HtmlEmail htmlMail = new HtmlEmail();
//		email.setDebug(true);
		htmlMail.setCharset(chartSet);
		htmlMail.setHostName(hostName);
		htmlMail.setSSLOnConnect(true);
		htmlMail.setAuthenticator(new DefaultAuthenticator(userName, password));
		htmlMail.addTo(recever);
		htmlMail.setFrom(formEmail,nickName);
		htmlMail.setSubject(subject);
		htmlMail.setHtmlMsg(content);
		htmlMail.setTextMsg(textMsg);
		htmlMail.send();

	}
}
