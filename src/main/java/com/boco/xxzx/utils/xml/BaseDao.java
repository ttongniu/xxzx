package com.boco.xxzx.utils.xml;

import java.io.IOException;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class BaseDao {

	// MyBait方法
	private BaseDao() {
	}

	private static String resource = "mybatis-config.xml";
	private static SqlSession session = null;
	private static SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
	private static Reader reader = null;
	private static SqlSessionFactory ssf = null;
	static {
		// 获取读取对象
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ssf = ssfb.build(reader);
	}

	public static SqlSession getSqlSession() {
		session = ssf.openSession();
		return session;
	}

}
