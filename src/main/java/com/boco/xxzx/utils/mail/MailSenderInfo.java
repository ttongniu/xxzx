package com.boco.xxzx.utils.mail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 邮件发送的一个bean，记录邮件发送的基本信息
 * 
 * @author penghui
 * 
 */
public class MailSenderInfo {
	// 发送邮件的服务器的IP和端口　　　
	private String mailServerHost;
	// 邮件服务端口,如果没有设置，默认为25
	private String mailServerPort = "25";
	// 邮件发送者的地址
	private String fromAddress;
	// 邮件接收者的地址
	private List<String> toAddress = new ArrayList<String>();
	// 邮件抄送者的地址
	private List<String> ccAddress = new ArrayList<String>();
	// 登陆邮件发送服务器的用户名和密码
	private String userName;
	private String password;
	// 是否需要身份验证　　　
	private boolean validate = false;
	// 邮件主题　　　
	private String subject;
	// 邮件的文本内容　　　
	private String content;
	// 邮件附件的文件名　filename-fileAbsolutePath
	private Map<String, String> files;

	/**
	 * 从配置文件加载配置信息
	 */
	public MailSenderInfo() {
		Properties info = MailUtil.getMailInfo();
		this.mailServerHost = info.getProperty("mailServerHost");
		this.fromAddress = info.getProperty("fromAddress");
		this.userName = info.getProperty("userName");
		this.password = info.getProperty("password");
		if (info.getProperty("mailServerPort") != null) {
			this.mailServerPort = info.getProperty("mailServerPort");
		}
		if (info.getProperty("validate") != null) {
			this.validate = Boolean.parseBoolean(info.getProperty("validate"));
		}
	}

	public MailSenderInfo(String toAddress, String subject, String content) {
		this();
		this.toAddress.add(toAddress);
		this.subject = subject;
		this.content = content;
	}
	
	public MailSenderInfo(String toAddress, String subject, String content, 
			Map<String,String> files) {
		this();
		this.toAddress.add(toAddress);
		this.subject = subject;
		this.content = content;
		this.files = files;
	}

	/**
	 * 获取邮件会话
	 * 
	 * @return Properties
	 */
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

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public List<String> getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress.clear();
		this.toAddress.add(toAddress);
	}

	public void setToAddress(List<String> toAddress) {
		this.toAddress = toAddress;
	}

	public List<String> getCcAddress() {
		return ccAddress;
	}

	public void setCcAddress(String ccAddress) {
		this.ccAddress.clear();
		this.ccAddress.add(ccAddress);
	}

	public void setCcAddress(List<String> ccAddress) {
		this.ccAddress = ccAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
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

	public void setContent(String content) {
		this.content = content;
	}

	public Map<String, String> getFiles() {
		return files;
	}

	public void setFiles(Map<String, String> files) {
		this.files = files;
	}
}
