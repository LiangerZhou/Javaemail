package cn.asiainfo.javamail;

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
	  // �ж��Ƿ���Ҫ�����֤
	  MyAuthenticator authenticator = null;
	  Properties pro = mailInfo.getProperties();
	  if (mailInfo.isValidate()) {
	      // �����Ҫ�����֤���򴴽�һ��������֤��
		  authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
	  }else{
		  System.out.println("��֤ʧ��");
	  }
	  // �����ʼ��Ự���Ժ�������֤������һ�������ʼ���session
	  Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
	  try {
			  // ����session����һ���ʼ���Ϣ
			  Message mailMessage = new MimeMessage(sendMailSession);
			  // �����ʼ������ߵ�ַ
			  Address from = new InternetAddress(mailInfo.getFromAddress());
			  // �����ʼ���Ϣ�ķ�����
			  mailMessage.setFrom(from);
			  // �����ʼ��Ľ����ߵ�ַ�������õ��ʼ���Ϣ��
			 // MailSenderInfo mailSenderInfo = new MailSenderInfo();
			  String[] to=mailInfo.getToAddress();
			  InternetAddress[] address = new InternetAddress[to.length];
			  for (int i = 0; i < address.length; i++) {
				  address[i] = new InternetAddress(to[i]);
				  isNameAdressFormat(address[i].getAddress());
			  }
			  
			  mailMessage.setRecipients(Message.RecipientType.TO, address);
	   
			  // �����ʼ��ĳ����ŵ�ַ�������õ��ʼ���Ϣ��
			  String[] tocc=mailInfo.getToAddresscc();
			  InternetAddress[] addresscc = new InternetAddress[tocc.length];
			  for (int i = 0; i < addresscc.length; i++) {
				  addresscc[i] = new InternetAddress(tocc[i]);
				  isNameAdressFormat(address[i].getAddress());
			  }
			  mailMessage.setRecipients(Message.RecipientType.CC, addresscc);			//�˴�Ϊ�����ͣ���Ҫ���ͽ�Message.RecipientType.CC��ΪMessage.RecipientType.BCC
	    
			  // �����ʼ���Ϣ������
			  mailMessage.setSubject(mailInfo.getSubject());
			  // �����ʼ���Ϣ���͵�ʱ��
			  mailMessage.setSentDate(new Date());		  
			  //����
			  Multipart mp = new MimeMultipart();
	         
			  //body����
			  MimeBodyPart mbp = new MimeBodyPart();
	         
			  //�жϷ��͵��Ƿ���html��ʽ
			  if (mailInfo.isHtml()) {													// �����html��ʽ
				  mbp.setContent(mailInfo.getContent(), "text/html;charset= "+ mailInfo.getCharset());
			  } else{
				  mbp.setText(mailInfo.getContent());
			  }
			  //�������Ĳ������뵽���岿��
			  mp.addBodyPart(mbp);
	         
			  if (mailInfo.getFiles() != null && mailInfo.getFiles().length > 0) {		// �ж��Ƿ��и���
	            //���ڸ����ͽ�����ȫ�����뵽BodyPart
				  for (File file : mailInfo.getFiles()) {
					  mbp = new MimeBodyPart();
					  FileDataSource fds = new FileDataSource(file); 					// �õ�����Դ
					  mbp.setDataHandler(new DataHandler(fds)); 						// �õ�������������BodyPart
					  mbp.setFileName(MimeUtility.encodeText(fds.getName())); 			// �õ��ļ���ת���ͬ������BodyPart
					  mp.addBodyPart(mbp);
	              }
			  }
			  // Multipart���뵽�ż�
			  mailMessage.setContent(mp); 
	  
			  // �����ʼ�
			  Transport.send(mailMessage,mailMessage.getAllRecipients());
			 
			  System.out.println("�ʼ����ͳɹ�");
			  
			  return true;
		  	} catch (MessagingException ex) {
		  		ex.printStackTrace();
		  		System.out.println("�����������ַ���������������¼�飡");
		  		} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("�ַ����벻֧�֣�");
			}
	  			System.out.println("�ʼ�����ʧ�ܣ�����ϸ�����������Ƿ�����");
		  		return false;
		 	}

 public boolean isNameAdressFormat(String email){  
	    boolean isExist = false;  
	   
	    Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}");  
	    Matcher m = p.matcher(email);  
	    boolean b = m.matches();  
	    if(b) {  
	        //System.out.println("��Ч�ʼ���ַ");  
	        isExist=true;
	        
	    } else {  
	        System.out.println("��Ч�ʼ���ַ");  
	        System.exit(1);
	    }  
	    return isExist;  
	}  

 
}
  
 