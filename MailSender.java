import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class MailSender {
 
 public boolean sendMixMail(MailSenderInfo mailInfo)  {
	  // 判断是否需要身份认证
	  MyAuthenticator authenticator = null;
	  Properties pro = mailInfo.getProperties();
	  if (mailInfo.isValidate()) {
	      // 如果需要身份认证，则创建一个密码验证器
		  authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
	  }else{
		  System.out.println("验证失败");
	  }
	  // 根据邮件会话属性和密码验证器构造一个发送邮件的session
	  Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
	  try {
			  // 根据session创建一个邮件消息
			  Message mailMessage = new MimeMessage(sendMailSession);
			  // 创建邮件发送者地址
			  Address from = new InternetAddress(mailInfo.getFromAddress());
			  // 设置邮件消息的发送者
			  mailMessage.setFrom(from);
			  // 创建邮件的接收者地址，并设置到邮件消息中
			 // MailSenderInfo mailSenderInfo = new MailSenderInfo();
			  String[] to=mailInfo.getToAddress();
			  InternetAddress[] address = new InternetAddress[to.length];
			  for (int i = 0; i < address.length; i++) {
				  address[i] = new InternetAddress(to[i]);
				  isNameAdressFormat(address[i].getAddress());
			  }
			  
			  mailMessage.setRecipients(Message.RecipientType.TO, address);
	   
			  // 创建邮件的抄送着地址，并设置到邮件消息中
			  String[] tocc=mailInfo.getToAddresscc();
			  InternetAddress[] addresscc = new InternetAddress[tocc.length];
			  for (int i = 0; i < addresscc.length; i++) {
				  addresscc[i] = new InternetAddress(tocc[i]);
				  isNameAdressFormat(address[i].getAddress());
			  }
			  mailMessage.setRecipients(Message.RecipientType.CC, addresscc);			//此处为非密送，若要密送将Message.RecipientType.CC改为Message.RecipientType.BCC
	    
			  // 设置邮件消息的主题
			  mailMessage.setSubject(mailInfo.getSubject());
			  // 设置邮件消息发送的时间
			  mailMessage.setSentDate(new Date());		  
			  //部件
			  Multipart mp = new MimeMultipart();
	         
			  //body部件
			  MimeBodyPart mbp = new MimeBodyPart();
	         
			  //判断发送的是否是html格式
			  if (mailInfo.isHtml()) {													// 如果是html格式
				  mbp.setContent(mailInfo.getContent(), "text/html;charset= "+ mailInfo.getCharset());
			  } else{
				  mbp.setText(mailInfo.getContent());
			  }
			  //将该正文部件加入到整体部件
			  mp.addBodyPart(mbp);
	         
			  if (mailInfo.getFiles() != null && mailInfo.getFiles().length > 0) {		// 判断是佛有附件
	            //存在附件就将附件全部加入到BodyPart
				  for (File file : mailInfo.getFiles()) {
					  mbp = new MimeBodyPart();
					  FileDataSource fds = new FileDataSource(file); 					// 得到数据源
					  mbp.setDataHandler(new DataHandler(fds)); 						// 得到附件本身并至入BodyPart
					  mbp.setFileName(MimeUtility.encodeText(fds.getName())); 			// 得到文件名转码后同样至入BodyPart
					  mp.addBodyPart(mbp);
	              }
			  }
			  // Multipart加入到信件
			  mailMessage.setContent(mp); 
	  
			  // 发送邮件
			  Transport.send(mailMessage,mailMessage.getAllRecipients());
			 
			  System.out.println("邮件发送成功");
			  
			  return true;
		  	} catch (MessagingException ex) {
		  		ex.printStackTrace();
		  		System.out.println("发件人邮箱地址或密码有误，请重新检查！");
		  		} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("字符编码不支持！");
			}
	  			System.out.println("邮件发送失败，请仔细检查相关设置是否有误！");
		  		return false;
		 	}

 public boolean isNameAdressFormat(String email){  
	    boolean isExist = false;  
	   
	    Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}");  
	    Matcher m = p.matcher(email);  
	    boolean b = m.matches();  
	    if(b) {  
	        //System.out.println("有效邮件地址");  
	        isExist=true;
	        
	    } else {  
	        System.out.println("无效邮件地址");  
	        System.exit(1);
	    }  
	    return isExist;  
	}  

 
}
  
 
