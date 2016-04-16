package cn.asiainfo.javamail;



import java.io.File;


public class SendMailDemo {
	public static void main(String[] args)  {
	   // 设置邮件服务器信息
	  MailSenderInfo mailInfo = new MailSenderInfo();
	  mailInfo.setMailServerHost("***@163.com");
	  //mailInfo.setMailServerPort("25");
	  mailInfo.setValidate(true);
	  //设置是否是HTML格式发送,false代表不是，true代表以HTML格式发送
	  mailInfo.setHtml(true);
	  
	  mailInfo.setCharset("utf-8");
	  // 邮箱用户名
	  mailInfo.setUserName("自定义");
	  // 邮箱密码
	  mailInfo.setPassword("自定义");
	  // 发件人邮箱
	  mailInfo.setFromAddress("自定义");
	  // 收件人邮箱,可以为多个
	  mailInfo.setToAddress(new String[]{"***@163.com","***@163.com"});
	  // 抄送人邮箱，可以为多个
	  mailInfo.setToAddresscc(new String[]{"***@163.com"});	  	  	  	  
	  // 邮件标题
	  mailInfo.setSubject("Test");
	  // 邮件附件	  
	  mailInfo.setFiles(new File[]{new File("F:/***.txt"),new File("F:/***.html")});

	  // 邮件内容
	  //此处设置超链接,html格式的发送
	  StringBuilder html = new StringBuilder();
	  html.append("<p>体验网址<a href='http://dacp.asiainfo.com/dacp/login'>dacp.asiainfo.com/dacp/login</a></p>");
	  //设置签名
	  html.append("<td>"
	  		+ "<div style='line-height:40px;height:40px'></div>"
	  		+ "<p style='line-height:24px;font-size:12px;color:#979797;font-family:'宋体''>此邮件为测试邮件，请勿回复！</p>"
	  		+ "</td>");

	  mailInfo.setContent("测试邮件"+html.toString());
	  // 发送邮件
	  MailSender msg = new MailSender();
	  // 发送文体格式
	  msg.sendMixMail(mailInfo);
	 }
	
	} 