package com.boco.xxzx.utils.util;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 日期格式化类
 * 
 * @author Metanoia.Lang
 * 
 */
public class DateFormatHelper {
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static final String TIME_FORMAT_YMDHMS_A = "yyyy-MM-dd HH:mm:ss";

	/**
	 * yyyy-MM-dd HH:mm
	 */
	public static final String TIME_FORMAT_YMDHM = "yyyy-MM-dd HH:mm";

	/**
	 * yyyyMMddHHmmss
	 */
	public static final String TIME_FORMAT_YMDHMS_B = "yyyyMMddHHmmss";
	/**
	 * yyyy-MM-dd HH:mm:ss:SSS
	 */
	public static final String TIME_FORMAT_YMDHMSSS = "yyyy-MM-dd HH:mm:ss:SSS";
	/**
	 * MM-dd-yyyy
	 */
	public static final String DATE_FORMAT_MDY = "MM-dd-yyyy";
	/**
	 * yyyy-MM-dd
	 */
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	/**
	 * yyyyMMdd
	 */
	public static final String DATE_FORMATB = "yyyyMMdd";
	/**
	 * yyyy-MM
	 */
	public static final String DATE_FORMAT_YM = "yyyy-MM";
	/**
	 * yyyyMM
	 */
	public static final String DATE_FORMAT_YMB = "yyyyMM";
	/**
	 * yyyy
	 */
	public static final String YEAR_FORMAT = "yyyy";

	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	private static final String FORMAT_T_YMDHMS_A = "^[0-9]{4}-[0-9]{1,2}-[0-9]{1,2} [0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}$";
	/**
	 * yyyy-MM-dd HH:mm
	 */
	private static final String FORMAT_T_YMDHM = "^[0-9]{4}-[0-9]{1,2}-[0-9]{1,2} [0-9]{1,2}:[0-9]{1,2}$";
	/**
	 * yyyyMMddHHmmss
	 */
	private static final String FORMAT_T_YMDHMS_B = "^[0-9]{4}[0-9]{1,2}[0-9]{1,2}[0-9]{1,2}[0-9]{1,2}[0-9]{1,2}$";
	/**
	 * yyyy-MM-dd HH:mm:ss:SSS
	 */
	private static final String FORMAT_T_YMDHMSSS = "^[0-9]{4}-[0-9]{1,2}-[0-9]{1,2} [0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}:[0-9]{3}$";
	/**
	 * MM-dd-yyyy
	 */
	private static final String FORMAT_D_MDY = "^[0-9]{1,2}-[0-9]{1,2}-[0-9]{4}$";
	/**
	 * yyyy-MM-dd
	 */
	private static final String FORMAT_D = "^[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}$";
	/**
	 * yyyyMMdd
	 */
	private static final String FORMAT_D_B = "^[0-9]{4}[0-9]{1,2}[0-9]{1,2}$";
	/**
	 * yyyy-MM
	 */
	private static final String FORMAT_YM = "^[0-9]{4}-[0-9]{1,2}$";
	/**
	 * yyyyMM
	 */
	private static final String FORMAT_YMB = "^[0-9]{4}[0-9]{1,2}$";
	/**
	 * yyyy
	 */
	private static final String FORMAT_Y = "^[0-9]{4}$";

	private static final String[][] FORMATS = {
			{ FORMAT_T_YMDHMS_A, TIME_FORMAT_YMDHMS_A },
			{ FORMAT_T_YMDHM, TIME_FORMAT_YMDHM },
			{ FORMAT_T_YMDHMS_B, TIME_FORMAT_YMDHMS_B },
			{ FORMAT_T_YMDHMSSS, TIME_FORMAT_YMDHMSSS },
			{ FORMAT_D_MDY, DATE_FORMAT_MDY }, { FORMAT_D, DATE_FORMAT },
			{ FORMAT_D_B, DATE_FORMATB }, { FORMAT_YM, DATE_FORMAT_YM },
			{ FORMAT_YMB, DATE_FORMAT_YMB }, { FORMAT_Y, YEAR_FORMAT } };

	private DateFormatHelper() {
	}

	/**
	 * 根据秒获取日期
	 * 
	 * @param seconds
	 *            Long 秒
	 * @param format
	 *            String 日期格式
	 * @return String ""-seconds==null
	 * 
	 */
	public static String getFormatStr(Long seconds, String format) {
		if (null == seconds){
			return "";}

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(seconds);
	}

	/**
	 * 根据Date获取日期
	 * 
	 * @param date
	 *            Date
	 * @param format
	 *            String
	 * @return String ""-date==null
	 */
	public static String getFormatStr(Date date, String format) {
		if (null == date)
			{return "";}

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(date);
	}

	/**
	 * 根据字符串获取Date
	 * 
	 * @param dateStr
	 *            String
	 * @param format
	 *            String
	 * @return Date null-dateStr==null or dateStr.equals("")
	 */
	public static Date getFormatDate(String dateStr, String format) {
		Date date = null;
		if (null == dateStr || dateStr.equals(""))
			{return date;}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		try {
			date = simpleDateFormat.parse(dateStr);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 根据字符串获取Date，方法内做自动格式匹配，如果匹配不上抛异常
	 * 
	 * @param dateStr
	 *            日期或时间格式的字符串
	 * @return Date Date null-dateStr==null or dateStr.equals("")
	 */
	public static Date getFormatDate(String dateStr) {
		Date date = null;
		if (null == dateStr || dateStr.equals(""))
			{return date;}

		for (String[] format : FORMATS) {
			Pattern pattern = Pattern.compile(format[0],
					Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(dateStr);
			if (matcher.find()) {
				date = getFormatDate(dateStr, format[1]);
				break;
			}
		}

		return date;
	}
}
