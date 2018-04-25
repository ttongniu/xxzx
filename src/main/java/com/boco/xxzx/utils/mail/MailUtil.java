package com.boco.xxzx.utils.mail;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.boco.xxzx.utils.xml.Dom4jUtils;

/**
 * 从配置文件中读取配置信息，初始化邮件发送信息
 * 
 * @author penghui,metanoia.lang
 * 
 */
public class MailUtil {
	private static Properties info = new Properties();
	private static Document mtDoc = null;
	static {
		try {
			InputStream is = MailUtil.class
					.getResourceAsStream("/com/boco/xxzx/utils/mail/mail.properties");
			info.load(is);
			is.close();

			InputStream mtis = MailUtil.class
					.getResourceAsStream("/mail.templates.xml");
			SAXReader reader = new SAXReader();
			reader.setEncoding("UTF-8");
			mtDoc = reader.read(mtis);
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	/**
	 * 获得邮件模板 邮件内容模板
	 * 
	 * @param mtName
	 *            String 邮件模板名称
	 * @return String 邮件内容模板
	 */
	public static String getMtContext(String mtName) {
		Element mtContext = Dom4jUtils.findElement(mtDoc,
				"/mail-templates/mail-template[@name='" + mtName + "']/text");

		if (mtContext != null)
			return mtContext.getText();
		else
			return null;
	}

	/**
	 * 获得邮件模板 标题模板
	 * 
	 * @param mtName
	 *            String 邮件模板名称
	 * @return String 标题模板
	 */
	public static String getMtSubject(String mtName) {
		Element mtSubject = Dom4jUtils
				.findElement(mtDoc, "/mail-templates/mail-template[@name='"
						+ mtName + "']/subject");

		if (mtSubject != null)
			return mtSubject.getText();
		else
			return null;
	}

	public static String paddingTemplate(String template, String key,
			String value) {
		if (template == null || key == null)
			return template;

		String ft = template.substring(0);
		return ft.replace(key, value);
	}

	/**
	 * 填充邮件模板
	 * 
	 * @param template
	 *            String 邮件模板串
	 * @param valueMap
	 *            填充参数 key-value对
	 * @return String 填充后邮件内容
	 */
	public static String paddingTemplate(String template,
			Map<String, String> valueMap) {
		if (template == null || valueMap == null || valueMap.isEmpty())
			return template;

		String ft = template.substring(0);

		Set<String> keys = valueMap.keySet();
		for (String key : keys) {
			String value = valueMap.get(key);
			ft = ft.replace(key, value);
		}
		return ft;
	}

	/**
	 * 获得邮件配置文件信息
	 * 
	 * @return Properties
	 */
	public static Properties getMailInfo() {
		return info;
	}

}
