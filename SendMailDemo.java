package cn.asiainfo.javamail;



import java.io.File;


public class SendMailDemo {
	public static void main(String[] args)  {
	   // �����ʼ���������Ϣ
	  MailSenderInfo mailInfo = new MailSenderInfo();
	  mailInfo.setMailServerHost("***@163.com");
	  //mailInfo.setMailServerPort("25");
	  mailInfo.setValidate(true);
	  //�����Ƿ���HTML��ʽ����,false�����ǣ�true������HTML��ʽ����
	  mailInfo.setHtml(true);
	  
	  mailInfo.setCharset("utf-8");
	  // �����û���
	  mailInfo.setUserName("�Զ���");
	  // ��������
	  mailInfo.setPassword("�Զ���");
	  // ����������
	  mailInfo.setFromAddress("�Զ���");
	  // �ռ�������,����Ϊ���
	  mailInfo.setToAddress(new String[]{"***@163.com","***@163.com"});
	  // ���������䣬����Ϊ���
	  mailInfo.setToAddresscc(new String[]{"***@163.com"});	  	  	  	  
	  // �ʼ�����
	  mailInfo.setSubject("Test");
	  // �ʼ�����	  
	  mailInfo.setFiles(new File[]{new File("F:/***.txt"),new File("F:/***.html")});

	  // �ʼ�����
	  //�˴����ó�����,html��ʽ�ķ���
	  StringBuilder html = new StringBuilder();
	  html.append("<p>������ַ<a href='http://dacp.asiainfo.com/dacp/login'>dacp.asiainfo.com/dacp/login</a></p>");
	  //����ǩ��
	  html.append("<td>"
	  		+ "<div style='line-height:40px;height:40px'></div>"
	  		+ "<p style='line-height:24px;font-size:12px;color:#979797;font-family:'����''>���ʼ�Ϊ�����ʼ�������ظ���</p>"
	  		+ "</td>");

	  mailInfo.setContent("�����ʼ�"+html.toString());
	  // �����ʼ�
	  MailSender msg = new MailSender();
	  // ���������ʽ
	  msg.sendMixMail(mailInfo);
	 }
	
	} 