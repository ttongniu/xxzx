package com.boco.xxzx.utils.util;

import java.text.DecimalFormat;

import org.apache.log4j.Logger;

import com.boco.xxzx.utils.log.Loger;

/**
 * 十进制数字格式化工具类
 * 
 * @author Metanoia Nov 26, 2008 10:21:32 AM
 */
public class NumericFormatHelper {
	private static Logger logger = Loger.log(NumericFormatHelper.class);

	/**
	 * 格式化小数，四舍五入保留两位
	 * 
	 * @param number
	 *            Double
	 * @return String 格式化后的string；如果number==null return null
	 */
	public static String reserve2bits(Double number) {
		if (null == number)
			return null;

		DecimalFormat df = new DecimalFormat("#0.00");
		return df.format(number);
	}

	/**
	 * 将Double转换成保留2为小数的Double
	 * 
	 * @param number
	 *            Double
	 * @return Double 如果number==null return null
	 */
	public static Double reserve2bitsForDouble(Double number) {
		return NumericFormatHelper.parseDouble(NumericFormatHelper
				.reserve2bits(number));
	}

	/**
	 * 将String转换成保留2为小数的Double
	 * 
	 * @param doubleStr
	 *            String
	 * @return Double 如果doubleStr==null或者转换时出现异常 返回null
	 */
	public static Double reserve2bits(String doubleStr) {
		return NumericFormatHelper.reserve2bitsForDouble(NumericFormatHelper
				.parseDouble(doubleStr));
	}

	/**
	 * 将String转换成Double
	 * 
	 * @param doubleStr
	 *            String
	 * @return Double 如果doubleStr==null或者转换时出现异常 返回null
	 */
	@SuppressWarnings("finally")
	public static Double parseDouble(String doubleStr) {
		if (null == doubleStr)
			return null;

		Double number = null;

		try {
			number = Double.parseDouble(doubleStr);
		} catch (NumberFormatException e) {
			logger.error(e.getMessage());
		} finally {
			return number;
		}
	}

	/**
	 * 将String转换成Integer
	 * 
	 * @param intStr
	 *            String
	 * @return Integer 如果intStr==null或者转换时出现异常 返回null
	 */
	@SuppressWarnings("finally")
	public static Integer parseInt(String intStr) {
		if (null == intStr)
			return null;

		Integer number = null;

		try {
			number = Integer.parseInt(intStr);
		} catch (NumberFormatException e) {
			logger.error(e.getMessage());
		} finally {
			return number;
		}
	}

	/**
	 * 将String转换成Long
	 * 
	 * @param longStr
	 *            String
	 * @return Long 如果longStr==null或者转换时出现异常 返回null
	 */
	@SuppressWarnings("finally")
	public static Long parseLong(String longStr) {
		if (null == longStr)
			return null;

		Long number = null;

		try {
			number = Long.parseLong(longStr);
		} catch (NumberFormatException e) {
			logger.error(e.getMessage());
		} finally {
			return number;
		}
	}

	/**
	 * 返回一个6位数的随机数
	 * 
	 * @return
	 */
	public static String random() {
		int num = (int) (Math.random() * 1000000);

		return num + "";
	}
}
