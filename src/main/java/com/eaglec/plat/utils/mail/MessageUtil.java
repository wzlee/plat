package com.eaglec.plat.utils.mail;



/**
 * 系统告警通知
 * @author cs
 *
 */
public class MessageUtil {

	/**
	 * 发送邮件
	 *
	 * @param title
	 * @param content
	 * @param to
	 */
	public static boolean sendMail(String title, String content, String to) {
		MailSenderInfo mailInfo = new MailSenderInfo(); 
		  mailInfo.setMailServerHost("smtp.exmail.qq.com"); 
		  mailInfo.setMailServerPort("25"); 
		  mailInfo.setValidate(true); 
		  mailInfo.setUserName("admin@pservice.cn"); 
		  mailInfo.setPassword("bmsoft2013");//您的邮箱密码 
		  mailInfo.setFromAddress("admin@pservice.cn"); 
		  mailInfo.setToAddress(to); 
		  mailInfo.setSubject(title); 
		  mailInfo.setContent(content); 
	        //这个类主要来发送邮件
		  SimpleMailSender sms = new SimpleMailSender();
	        return sms.sendTextMail(mailInfo);//发送文体格式 
	 //        sms.sendHtmlMail(mailInfo);//发送html格式
		
	}
	public static void main(String[] args) {
		sendMail("cs", "cs", "263621221@qq.com");
	}
}
