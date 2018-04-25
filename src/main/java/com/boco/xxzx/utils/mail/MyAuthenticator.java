package com.boco.xxzx.utils.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 用于认证的封装类
 * @author penghui
 *
 */
public class MyAuthenticator extends Authenticator {
	private String userName = null;
	private String password = null;

	public MyAuthenticator() {
	}

	public MyAuthenticator(String userName, String password) {
		this.userName=userName;
		this.password=password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, password);
	}
}
