package com.boco.xxzx.utils.mail;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.log4j.Logger;
/**
 * 邮件发送类
 * 
 * @author penghui
 * 
 */
public class SimpleMailSender {
	private static Logger log = Logger.getLogger(SimpleMailSender.class);
	private static String MAILSUFFIX = "@boco.com.cn";

	public final static int SR_SUCCESS = 1; // 发送成功
	public final static int SR_ADDRESSERROR = 2; // 邮箱错误 包括账号和域名
	public final static int SR_MESSAGEERROR = 3; // 其他异常

	public static int sendTextMail(MailSenderInfo mailInfo) {
		// 判断是否需要身份认证
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		if (mailInfo.isValidate()) {
			// 如果需要身份认证，则创建一个密码验证器
			authenticator = new MyAuthenticator(mailInfo.getUserName(),
					mailInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session　　　
		Session sendMailSession = Session
				.getDefaultInstance(pro, authenticator);
		try {
			// 根据session创建一个邮件消息　　　
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址　　　
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// 设置邮件消息的发送者　　　
			mailMessage.setFrom(from);

			// 创建邮件的接收者地址，并设置到邮件消息中　　　
			List<String> toAddress = mailInfo.getToAddress();
			List<Address> toList = new ArrayList<Address>();
			for (String to : toAddress) {
				if (!checkAddress(to))
					return SR_ADDRESSERROR;
				Address toadd = new InternetAddress(to);
				toList.add(toadd);
			}
			Address[] toArray = new Address[toList.size()];
			toArray = toList.toArray(toArray);
			// Message.RecipientType.TO属性表示接收者的类型为TO　　　
			mailMessage.setRecipients(Message.RecipientType.TO, toArray);

			// 创建邮件的抄送人地址，并设置到邮件消息中
			List<String> ccAddress = mailInfo.getCcAddress();
			List<Address> ccList = new ArrayList<Address>();
			for (String cc : ccAddress) {
				if (!checkAddress(cc))
					return SR_ADDRESSERROR;
				Address ccadd = new InternetAddress(cc);
				ccList.add(ccadd);
			}
			Address[] ccArray = new Address[ccList.size()];
			ccArray = ccList.toArray(ccArray);
			// Message.RecipientType.CC属性标示抄送者的类型为CC
			mailMessage.setRecipients(Message.RecipientType.CC, ccArray);

			// 设置邮件消息的主题　　　
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间　　　
			mailMessage.setSentDate(new Date());
			// 设置邮件消息的主要内容　　　
			String mailContent = mailInfo.getContent();
			mailMessage.setText(mailContent);

			// 处理附件
			// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
			Multipart mainPart = new MimeMultipart();
			Map<String,String> files= mailInfo.getFiles();
			List<MimeBodyPart> listfile = getFiles(files);
			if (!listfile.isEmpty()) {
				for (MimeBodyPart fp : listfile) {
					mainPart.addBodyPart(fp);
				}
			}
			mailMessage.setContent(mainPart);

			// 发送邮件　　　
			Transport.send(mailMessage);
			return SR_SUCCESS;
		} catch (AddressException e) {
			log.error(e.getMessage());
			return SR_ADDRESSERROR;
		} catch (MessagingException e) {
			log.error(e.getMessage());
			return SR_MESSAGEERROR;
		}
	}

	/**
	 * 以HTML格式发送邮件
	 * 
	 * @param mailInfo
	 *            待发送的邮件信息　　 　　　　　
	 */
	public static int sendHtmlMail(MailSenderInfo mailInfo) {
		// 判断是否需要身份认证　　　
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		// 如果需要身份认证，则创建一个密码验证器　　　　
		if (mailInfo.isValidate()) {
			authenticator = new MyAuthenticator(mailInfo.getUserName(),
					mailInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session　　　
		Session sendMailSession = Session
				.getDefaultInstance(pro, authenticator);
		try {
			// 根据session创建一个邮件消息　　　
			Message mailMessage = new MimeMessage(sendMailSession);

			// 创建邮件发送者地址　　　
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// 设置邮件消息的发送者　　　
			mailMessage.setFrom(from);

			// 创建邮件的接收者地址，并设置到邮件消息中　　　
			List<String> toAddress = mailInfo.getToAddress();
			List<Address> toList = new ArrayList<Address>();
			for (String to : toAddress) {
				if (!checkAddress(to))
					return SR_ADDRESSERROR;
				Address toadd = new InternetAddress(to);
				toList.add(toadd);
			}
			Address[] toArray = new Address[toList.size()];
			toArray = toList.toArray(toArray);
			// Message.RecipientType.TO属性表示接收者的类型为TO　　　
			mailMessage.setRecipients(Message.RecipientType.TO, toArray);

			// 创建邮件的抄送人地址，并设置到邮件消息中
			List<String> ccAddress = mailInfo.getCcAddress();
			List<Address> ccList = new ArrayList<Address>();
			for (String cc : ccAddress) {
				if (!checkAddress(cc))
					return SR_ADDRESSERROR;
				Address ccadd = new InternetAddress(cc);
				ccList.add(ccadd);
			}
			Address[] ccArray = new Address[ccList.size()];
			ccArray = ccList.toArray(ccArray);
			// Message.RecipientType.CC属性标示抄送者的类型为CC
			mailMessage.setRecipients(Message.RecipientType.CC, ccArray);

			// 设置邮件消息的主题　　　
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间　　　
			mailMessage.setSentDate(new Date());
			// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
			Multipart mainPart = new MimeMultipart();
			// 创建一个包含HTML内容的MimeBodyPart　　　
			BodyPart html = new MimeBodyPart();
			// 设置HTML内容　　　
			html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
			mainPart.addBodyPart(html);

			// 处理附件
			Map<String,String> files = mailInfo.getFiles();
			List<MimeBodyPart> listfile = getFiles(files);
			if (!listfile.isEmpty()) {
				for (MimeBodyPart fp : listfile) {
					mainPart.addBodyPart(fp);
				}
			}

			// 将MiniMultipart对象设置为邮件内容　　　
			mailMessage.setContent(mainPart);
			// 发送邮件　　　
			Transport.send(mailMessage);
			return SR_SUCCESS;
		} catch (AddressException e) {
			log.error(e.getMessage());
			return SR_ADDRESSERROR;
		} catch (MessagingException e) {
			log.error(e.getMessage());
			return SR_MESSAGEERROR;
		}
	}

	private static List<MimeBodyPart> getFiles(Map<String,String> files) {
		List<MimeBodyPart> list = new ArrayList<MimeBodyPart>();
		if (files == null || files.isEmpty())
			return list;
		
		Set<String> filenames = files.keySet();

		for (String filename : filenames) {
			try {
				String fn = MimeUtility.encodeWord(filename);
				String path = files.get(filename);
				
				FileDataSource fds = new FileDataSource(path);
				DataHandler dh = new DataHandler(fds);
				
				MimeBodyPart fp = new MimeBodyPart();
				fp.setFileName(fn);
				fp.setDataHandler(dh);
				list.add(fp);
			} catch (IOException e) {
				log.error(e.getMessage());
			} catch (MessagingException e) {
				log.error(e.getMessage());
			}
		}

		return list;
	}

	private static boolean checkAddress(String mail) {
		if (mail == null)
			return false;

		String mail_regex = "^*" + MAILSUFFIX + "$";
		Pattern mail_pattern = Pattern.compile(mail_regex,
				Pattern.CASE_INSENSITIVE);
		
		Matcher mail_matcher = mail_pattern.matcher(mail);

		if (mail_matcher.find())
			return true;
		else
			return false;
	}
    
	public static void MailSendRejectAssignmentHandler(String dformcode,String sysname,String tomail){
		String subject = MailUtil.getMtSubject("RejectAssignmentHandler");
		String content = MailUtil.getMtContext("RejectAssignmentHandler");
		Map<String, String>	 valueMap = new HashMap<String, String>();
		valueMap.put("${sysname}", sysname);
		valueMap.put("${dformcode}", dformcode);
		subject=  MailUtil.paddingTemplate(subject, valueMap);
		content = MailUtil.paddingTemplate(content, valueMap);
		MailSenderInfo info = new MailSenderInfo(tomail,
			subject, content);
		SimpleMailSender.sendHtmlMail(info);
	}
	
	public static void MailSendPigeonholeAction(String dformcode,String wfname,String sysname,String tomail){
		String subject = MailUtil.getMtSubject("PigeonholeAction");
		String content = MailUtil.getMtContext("PigeonholeAction");
		Map<String, String>	 valueMap = new HashMap<String, String>();
		valueMap.put("${sysname}", sysname);
		valueMap.put("${dformcode}", dformcode);
		valueMap.put("${wfname}", wfname);
		subject=  MailUtil.paddingTemplate(subject, valueMap);
		content = MailUtil.paddingTemplate(content, valueMap);
		MailSenderInfo info = new MailSenderInfo(tomail,
			subject, content);
		SimpleMailSender.sendHtmlMail(info);
	}
	
	public static void MailSendCountersignAction(String dformcode,String sysname,String tomail){
		String subject = MailUtil.getMtSubject("CountersignAction");
		String content = MailUtil.getMtContext("CountersignAction");
		Map<String, String>	 valueMap = new HashMap<String, String>();
		valueMap.put("${sysname}", sysname);
		valueMap.put("${dformcode}", dformcode);
		subject=  MailUtil.paddingTemplate(subject, valueMap);
		content = MailUtil.paddingTemplate(content, valueMap);
		MailSenderInfo info = new MailSenderInfo(tomail,
			subject, content);
		SimpleMailSender.sendHtmlMail(info);
	}
	
	public static void main(String[] args) {
		/*MailSenderInfo info = new MailSenderInfo("langshuai@boco.com.cn", "附件测试", "测试");
		Map<String,String> files = new HashMap<String,String>();
		files.put("关于P2P软件对公司网络危害的相关介绍.doc", 
				"D:\\关于P2P软件对公司网络危害的相关介绍.doc");
		info.setFiles(files);

		int r = SimpleMailSender.sendHtmlMail(info);
		System.out.println(r + "");*/
		String subject = MailUtil.getMtSubject("CountersignAction");
		String content = MailUtil.getMtContext("CountersignAction");
		String  sysname="新工作平台";
		String  dformcode="请假单001";
		Map<String, String>	 valueMap = new HashMap<String, String>();
		valueMap.put("${sysname}", sysname);
		valueMap.put("${dformcode}", dformcode);
		subject=  MailUtil.paddingTemplate(subject, valueMap);
		content = MailUtil.paddingTemplate(content, valueMap);
		
		MailSenderInfo info = new MailSenderInfo("niutongtong@boco.com.cn",
			subject, content);
		//发送附件
		/*Map<String, String> files = new HashMap<String, String>();
		files.put("123.jpg", "E:\\photo\\9506426_48450091.jpg");
		info.setFiles(files);*/
		int r = SimpleMailSender.sendHtmlMail(info);
		System.out.println(r + "");
	}
}
