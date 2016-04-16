package cn.asiainfo.javamail;

import java.io.File;
import java.util.Properties;



public class MailSenderInfo {
 // �����ʼ��ķ�������IP(��������ַ)
 private String mailServerHost;

 // �����ʼ��ķ������Ķ˿�
 private String mailServerPort = "25";

 // �����������ַ
 private String fromAddress;

 // �ռ��������ַ
 private String[] toAddress;
 
 // �����������ַ
 private String[] toAddresscc;
 

// ��½�ʼ����ͷ��������û���
 private String userName;

 // ��½�ʼ����ͷ�����������
 private String password;

 // �Ƿ���Ҫ�����֤
 private boolean validate = false;

 // �Ƿ�HTML��ʽ����
 private boolean isHtml = false;
 
 // �ʼ�����
 private String subject;

// �ʼ����ı�����
 private String content;

 // �ʼ��������ļ���
 private String[] attachFileNames;

 //�ַ���
 private String charset ;
 
 // �ļ�
 private File[] files;
 


public Properties getProperties() {
  Properties p = new Properties();
  p.put("mail.smtp.host", this.mailServerHost);
  p.put("mail.smtp.port", this.mailServerPort);
  p.put("mail.smtp.auth", validate ? "true" : "false");
  return p;
 }

 public String getMailServerHost() {
  return mailServerHost;
 }

 public void setMailServerHost(String mailServerHost) {
  this.mailServerHost = mailServerHost;
 }

 public String getMailServerPort() {
  return mailServerPort;
 }

 public void setMailServerPort(String mailServerPort) {
  this.mailServerPort = mailServerPort;
 }

 public boolean isValidate() {
  return validate;
 }

 public void setValidate(boolean validate) {
  this.validate = validate;
 }

 public String[] getAttachFileNames() {
  return attachFileNames;
 }

 public void setAttachFileNames(String[] fileNames) {
  this.attachFileNames = fileNames;
 }

 public String getFromAddress() {
  return fromAddress;
 }

 public void setFromAddress(String fromAddress) {
  this.fromAddress = fromAddress;
 }

 public String getPassword() {
  return password;
 }

 public void setPassword(String password) {
  this.password = password;
 }


 public String[] getToAddress() {
	return toAddress;
}

public void setToAddress(String[] toAddress) {
	this.toAddress = toAddress;
}

public String[] getToAddresscc() {
	return toAddresscc;
}

public void setToAddresscc(String[] toAddresscc) {
	this.toAddresscc = toAddresscc;
}


public String getUserName() {
  return userName;
 }

 public void setUserName(String userName) {
  this.userName = userName;
 }

 public String getSubject() {
  return subject;
 }

 public void setSubject(String subject) {
  this.subject = subject;
 }

 public String getContent() {
  return content;
 }

 public void setContent(String textContent) {
  this.content = textContent;
 }

 public String getCharset() {
	return charset;
}

public void setCharset(String charset) {
	this.charset = charset;
}

public boolean isHtml() {
	return isHtml;
}

public void setHtml(boolean isHtml) {
	this.isHtml = isHtml;
}


public File[] getFiles() {
	return files;
}

public void setFiles(File[] files) {
	this.files = files;
}



} 