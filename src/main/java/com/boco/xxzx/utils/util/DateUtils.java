package com.boco.xxzx.utils.util;


import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类，系统相关日期处理都由该类提供
 * 
 * @author Metanoia.Lang
 * 
 */
public class DateUtils {

	/**
	 * 时间范围：年
	 */
	public static final int YEAR = 1;

	/**
	 * 时间范围：季度
	 */
	public static final int QUARTER = 2;

	/**
	 * 时间范围：月
	 */
	public static final int MONTH = 3;

	/**
	 * 时间范围：周
	 */
	public static final int WEEK = 5;

	/**
	 * 时间范围：日
	 */
	public static final int DAY = 6;

	/**
	 * 时间比较:当前时间在比较时间段之前
	 */
	public static final int BEFORE_SDATE = 1;

	/**
	 * 时间比较:当前时间在比较时间段内
	 */
	public static final int IN_PERIOD = 2;

	/**
	 * 时间比较:当前时间在比较时间段之后
	 */
	public static final int AFTER_EDATE = 3;

	public DateUtils() {
	}
	 /**
	  * 得到现在时间
	  * 
	  * @return
	  */
	 public static Date getNow() {
	  Date currentTime = new Date();
	  return currentTime;
	 }
	
	/**
	 * 获取系统当前时间字符串 格式:"yyyy-MM-dd HH:mm:ss"
	 * 
	 * @return String "yyyy-MM-dd HH:mm:ss"
	 */
	public static String getCurSysDateStrBySecond() {
		return DateFormatHelper.getFormatStr(new Date(),
				DateFormatHelper.TIME_FORMAT_YMDHMS_A);
	}
	/**
	 * 
	 * <p>Title: getCurSysDateStr</p>   
	 * <p>Description: (时间字符串)</p>  
	 * @param: @return      
	 * @return: String     "yyyyMMddHHmmss" 
	 *
	 */
	public static String  getCurSysDateStr(){
		return getCurSysDateStrBySecond().replaceAll("\\D", "") ;
	}

	/**
	 * 获取系统当前时间字符串 格式："yyyy-MM-dd"
	 * 
	 * @return String "yyyy-MM-dd"
	 */
	public static String getCurSysDateStrByDate() {
		return DateFormatHelper.getFormatStr(new Date(),
				DateFormatHelper.DATE_FORMAT);
	}

	/**
	 * 根据指定时间格式，获取系统当前时间字符串
	 * 
	 * @param dateFormatHelperFiled
	 *            String DateFormatHelper静态变量
	 * @return String 格式化错误 return null
	 */
	public static String getCurSysDateStr(String dateFormatHelperFiled) {
		return DateFormatHelper.getFormatStr(new Date(), dateFormatHelperFiled);
	}

	/**
	 * 返回当前所在周是当前年的第几周，按周第一天是MONDAY计算
	 * 
	 * @return int WeekOfYear
	 */
	public static int getCurWeekOfYear() {
		return getWeekOfYear(new Date(), 0, Calendar.MONDAY);
	}

	/**
	 * 返回当前所在周是当前年的第几周，按指定周第一天是星期几计算
	 * 
	 * @param fiducialDate
	 *            Date 如果null，取当前时间为基准
	 * @param offset
	 *            int 天的偏移量
	 * @param firstDayOfWeek
	 *            int 指定周的第一天是星期几，使用Calendar类的静态属性，例如:Calendar.MONDAY
	 * @return int WeekOfYear fiducialDate==null renturn -1
	 */
	public static int getWeekOfYear(Date fiducialDate, int offset,
			int firstDayOfWeek) {
		if (fiducialDate == null)
			return -1;

		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(firstDayOfWeek);
		cal.setTime(fiducialDate);
		cal.add(Calendar.DAY_OF_MONTH, offset);
		return cal.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 获取年的第一天
	 * 
	 * @param fiducialDate
	 *            Date 如果null，取当前时间为基准
	 * @param offset
	 *            int 年的偏移量
	 * @return Date
	 */
	public static Date getFirstDayOfYear(Date fiducialDate, int offset) {
		if (fiducialDate == null)
			return null;

		Calendar cal = Calendar.getInstance();
		cal.setTime(fiducialDate);
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + offset);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	/**
	 * 获取年的最后一天
	 * 
	 * @param fiducialDate
	 *            Date 如果null，取当前时间为基准
	 * @param offset
	 *            int 年的偏移量
	 * @return Date
	 */
	public static Date getLastDayOfYear(Date fiducialDate, int offset) {
		if (fiducialDate == null)
			return null;

		Calendar cal = Calendar.getInstance();
		cal.setTime(fiducialDate);
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + offset);
		cal.set(Calendar.MONTH, Calendar.DECEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 31);
		return cal.getTime();
	}

	/**
	 * 获取季度的第一天
	 * 
	 * @param fiducialDate
	 *            Date 如果null，取当前时间为基准
	 * @param offset
	 *            int 季度的偏移量
	 * @return Date
	 */
	public static Date getFirstDayOfQuarter(Date fiducialDate, int offset) {
		if (fiducialDate == null)
			return null;

		Calendar cal = Calendar.getInstance();
		cal.setTime(fiducialDate);
		cal.add(Calendar.MONTH, offset * 3);
		int mon = cal.get(Calendar.MONTH);
		if (mon >= Calendar.JANUARY && mon <= Calendar.MARCH) {
			cal.set(Calendar.MONTH, Calendar.JANUARY);
			cal.set(Calendar.DAY_OF_MONTH, 1);
		}
		if (mon >= Calendar.APRIL && mon <= Calendar.JUNE) {
			cal.set(Calendar.MONTH, Calendar.APRIL);
			cal.set(Calendar.DAY_OF_MONTH, 1);
		}
		if (mon >= Calendar.JULY && mon <= Calendar.SEPTEMBER) {
			cal.set(Calendar.MONTH, Calendar.JULY);
			cal.set(Calendar.DAY_OF_MONTH, 1);
		}
		if (mon >= Calendar.OCTOBER && mon <= Calendar.DECEMBER) {
			cal.set(Calendar.MONTH, Calendar.OCTOBER);
			cal.set(Calendar.DAY_OF_MONTH, 1);
		}
		return cal.getTime();
	}

	/**
	 * 获取季度的最后一天
	 * 
	 * @param fiducialDate
	 *            Date 如果null，取当前时间为基准
	 * @param offset
	 *            int 季度的偏移量
	 * @return Date
	 */
	public static Date getLastDayOfQuarter(Date fiducialDate, int offset) {
		if (fiducialDate == null)
			return null;

		Calendar cal = Calendar.getInstance();
		cal.setTime(fiducialDate);
		cal.add(Calendar.MONTH, offset * 3);

		int mon = cal.get(Calendar.MONTH);
		if (mon >= Calendar.JANUARY && mon <= Calendar.MARCH) {
			cal.set(Calendar.MONTH, Calendar.MARCH);
			cal.set(Calendar.DAY_OF_MONTH, 31);
		}
		if (mon >= Calendar.APRIL && mon <= Calendar.JUNE) {
			cal.set(Calendar.MONTH, Calendar.JUNE);
			cal.set(Calendar.DAY_OF_MONTH, 30);
		}
		if (mon >= Calendar.JULY && mon <= Calendar.SEPTEMBER) {
			cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
			cal.set(Calendar.DAY_OF_MONTH, 30);
		}
		if (mon >= Calendar.OCTOBER && mon <= Calendar.DECEMBER) {
			cal.set(Calendar.MONTH, Calendar.DECEMBER);
			cal.set(Calendar.DAY_OF_MONTH, 31);
		}
		return cal.getTime();
	}

	/**
	 * 获取月的第一天
	 * 
	 * @param fiducialDate
	 *            Date 如果null，取当前时间为基准
	 * @param offset
	 *            int 月的偏移量
	 * @return Date
	 */
	public static Date getFirstDayOfMonth(Date fiducialDate, int offset) {
		if (fiducialDate == null)
			return null;

		Calendar cal = Calendar.getInstance();
		cal.setTime(fiducialDate);
		cal.add(Calendar.MONTH, offset);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	/**
	 * 获取月的最后一天
	 * 
	 * @param fiducialDate
	 *            Date 如果null，取当前时间为基准
	 * @param offset
	 *            int 月的偏移量
	 * @return Date
	 */
	public static Date getLastDayOfMonth(Date fiducialDate, int offset) {
		if (fiducialDate == null)
			return null;

		Calendar cal = Calendar.getInstance();
		cal.setTime(fiducialDate);
		cal.add(Calendar.MONTH, offset + 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}

	/**
	 * 获取周的第一天(MONDAY)
	 * 
	 * @param fiducialDate
	 *            Date 如果null，取当前时间为基准
	 * @param offset
	 *            int 周 的偏移量
	 * @return Date
	 */
	public static Date getFirstDayOfWeek(Date fiducialDate, int offset) {
		return getFirstDayOfWeek(fiducialDate, offset, Calendar.MONDAY);
	}

	/**
	 * 获取周的第一天，可以指定周的第一天是星期几，根据指定返回周第一天对象
	 * 
	 * @param fiducialDate
	 *            Date 如果null，取当前时间为基准
	 * @param offset
	 *            int 周 的偏移量
	 * @param firstDayOfWeek
	 *            int 指定周的第一天是星期几，使用Calendar类的静态属性，例如:Calendar.MONDAY
	 * @return Date
	 */
	public static Date getFirstDayOfWeek(Date fiducialDate, int offset,
			int firstDayOfWeek) {
		if (fiducialDate == null)
			return null;

		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(firstDayOfWeek);
		cal.setTime(fiducialDate);
		cal.add(Calendar.DAY_OF_MONTH, offset * 7);
		cal.set(Calendar.DAY_OF_WEEK, firstDayOfWeek);
		return cal.getTime();
	}

	/**
	 * 获取周的最后一天(SUNDAY)
	 * 
	 * @param fiducialDate
	 *            Date 如果null，取当前时间为基准
	 * @param offset
	 *            int 周 的偏移量
	 * @return Date
	 */
	public static Date getLastDayOfWeek(Date fiducialDate, int offset) {
		return getLastDayOfWeek(fiducialDate, offset, Calendar.MONDAY);
	}

	/**
	 * 获取周的最后一天，可以指定周的第一天是星期几，根据指定返回周最后一天对象
	 * 
	 * @param fiducialDate
	 *            Date 如果null，取当前时间为基准
	 * @param offset
	 *            int 周 的偏移量
	 * @param firstDayOfWeek
	 *            int 指定周的第一天是星期几，使用Calendar类的静态属性，例如:Calendar.MONDAY
	 * @return Date
	 */
	public static Date getLastDayOfWeek(Date fiducialDate, int offset,
			int firstDayOfWeek) {
		if (fiducialDate == null)
			return null;

		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(firstDayOfWeek);
		cal.setTime(fiducialDate);
		cal.add(Calendar.DAY_OF_MONTH, offset * 7);
		cal.set(Calendar.DAY_OF_WEEK, firstDayOfWeek);
		cal.add(Calendar.DAY_OF_MONTH, 6);
		return cal.getTime();
	}

	/**
	 * 获取指定时间范围的第一天，如果是周范围，按周第一天是MONDAY计算
	 * 
	 * @param fiducialDate
	 *            Date
	 * @param field
	 *            int 时间范围类型 DateUtil类时间范围常量
	 * @param offset
	 *            int field的偏移量
	 * @return Date
	 */
	public static Date getFirstDate(Date fiducialDate, int field, int offset) {
		if (fiducialDate == null)
			return null;

		switch (field) {
		case YEAR:
			return getFirstDayOfYear(fiducialDate, offset);
		case QUARTER:
			return getFirstDayOfQuarter(fiducialDate, offset);
		case MONTH:
			return getFirstDayOfMonth(fiducialDate, offset);
		case WEEK:
			return getFirstDayOfWeek(fiducialDate, offset);
		case DAY:
			return add(fiducialDate, Calendar.DAY_OF_YEAR, offset);
		}
		return fiducialDate;
	}

	/**
	 * 获取指定时间范围的第一天，如果是周范围，按周第一天是MONDAY计算。格式:"yyyy-MM-dd"
	 * 
	 * @param fiducialDate
	 *            Date
	 * @param field
	 *            int 时间范围类型 DateUtil类时间范围常量
	 * @param offset
	 *            int field的偏移量
	 * @return String "yyyy-MM-dd"
	 */
	public static String getFirstDateStr(Date fiducialDate, int field,
			int offset) {
		return DateFormatHelper.getFormatStr(getFirstDate(fiducialDate, field,
				offset), DateFormatHelper.DATE_FORMAT);
	}

	/**
	 * 获取指定时间范围的最后一天，如果是周范围，按周第一天是MONDAY计算
	 * 
	 * @param fiducialDate
	 *            Date
	 * @param field
	 *            int 时间范围类型 DateUtil类时间范围常量
	 * @param offset
	 *            int field的偏移量
	 * @return Date
	 */
	public static Date getLastDate(Date fiducialDate, int field, int offset) {
		if (fiducialDate == null)
			return null;

		switch (field) {
		case YEAR:
			return getLastDayOfYear(fiducialDate, offset);
		case QUARTER:
			return getLastDayOfQuarter(fiducialDate, offset);
		case MONTH:
			return getLastDayOfMonth(fiducialDate, offset);
		case WEEK:
			return getLastDayOfWeek(fiducialDate, offset);
		case DAY:
			return add(fiducialDate, Calendar.DAY_OF_YEAR, offset);
		}
		return fiducialDate;
	}

	/**
	 * 获取指定时间范围的最后一天，如果是周范围，按周第一天是MONDAY计算。格式:"yyyy-MM-dd"
	 * 
	 * @param fiducialDate
	 *            Date
	 * @param field
	 *            int 时间范围类型 DateUtil类时间范围常量
	 * @param offset
	 *            int 偏移量
	 * @return String "yyyy-MM-dd"
	 */
	public static String getLastDateStr(Date fiducialDate, int field, int offset) {
		return DateFormatHelper.getFormatStr(getLastDate(fiducialDate, field,
				offset), DateFormatHelper.DATE_FORMAT);
	}

	/**
	 * 指定时间段内有几个dayOfWork
	 * 
	 * @param sDate
	 *            Date 时间段启点
	 * @param eDate
	 *            Date 时间段止点
	 * @param dayOfWeek
	 *            int 星期几 使用Calendar类的静态属性，例如:Calendar.MONDAY
	 * @return int dayOfWork个数 sDate==null eDate==null return -1
	 */
	public static int countSpecifyDayOfWeek(Date sDate, Date eDate,
			int dayOfWeek) {
		int count = 0;

		if (null == sDate || null == eDate)
			return -1;

		int sWeekNum = 0;
		int eWeekNum = 0;

		sWeekNum = DateUtils.getWeekOfYear(sDate, 0, dayOfWeek);
		eWeekNum = DateUtils.getWeekOfYear(eDate, 0, dayOfWeek);

		count = eWeekNum - sWeekNum;

		// 修正
		Date amendDate = DateUtils.getFirstDayOfWeek(sDate, 0, dayOfWeek);
		if (DateUtils.compareToDay(amendDate, sDate) == 0)
			count++;

		return count;
	}

	/**
	 * 指定时间段内有几个dayOfWork
	 * 
	 * @param sDate
	 *            String 时间段启点
	 * @param eDate
	 *            String 时间段止点
	 * @param dayOfWeek
	 *            int 星期几 使用Calendar类的静态属性，例如:Calendar.MONDAY
	 * @param dateFormatHelperFiled
	 *            String DateFormatHelper静态变量
	 * @return int dayOfWork个数 sDate==null/"" eDate==null/""
	 *         dateFormatHelperFiled=null/"" return -1
	 */
	public static int countSpecifyDayOfWeek(String sDate, String eDate,
			int dayOfWeek, String dateFormatHelperFiled) {
		int count = 0;

		if (null == sDate || null == eDate || null == dateFormatHelperFiled
				|| sDate.equals("") || eDate.equals("")
				|| dateFormatHelperFiled.equals(""))
			return -1;

		count = DateUtils.countSpecifyDayOfWeek(DateFormatHelper.getFormatDate(
				sDate, dateFormatHelperFiled), DateFormatHelper.getFormatDate(
				eDate, dateFormatHelperFiled), dayOfWeek);

		return count;
	}

	/**
	 * 根据日历的规则，为基准时间添加指定日历字段的时间量
	 * 
	 * @param fiducialDate
	 *            Date
	 * @param calendarField
	 *            int 日历字段, 使用Calendar类定义的日历字段常量
	 * @param offset
	 *            int calendarField偏移量
	 * @return Date
	 */
	public static Date add(Date fiducialDate, int calendarField, int offset) {
		if (fiducialDate == null)
			return null;

		Calendar cal = Calendar.getInstance();
		cal.setTime(fiducialDate);
		cal.add(calendarField, offset);
		return cal.getTime();
	}

	/**
	 * 根据日历的规则，为基准时间添加指定日历字段的时间量
	 * 
	 * @param fiducialDate
	 *            String
	 * @param format
	 *            String DateFormatHelper静态变量
	 * @param calendarField
	 *            日历字段, 使用Calendar类定义的日历字段常量
	 * @param offset
	 *            int calendarField偏移量
	 * @return String
	 */
	public static String add(String fiducialDate, String format,
			int calendarField, int offset) {
		Date date = DateFormatHelper.getFormatDate(fiducialDate, format);
		if (null == date)
			return null;

		date = DateUtils.add(date, calendarField, offset);
		return DateFormatHelper.getFormatStr(date, format);
	}

	/**
	 * 根据日历的规则，为基准时间添加指定日历字段的单个时间单元 例:如果 DAY_OF_MONTH 字段为 31，则在 February
	 * 的范围内滚动会将它设置为 28。
	 * 
	 * @param fiducialDate
	 *            Date
	 * @param calendarField
	 *            int 日历字段, 使用Calendar类定义的日历字段常量
	 * @param offset
	 *            int 偏移量
	 * @return Date
	 */
	public static Date roll(Date fiducialDate, int calendarField, int offset) {
		if (fiducialDate == null)
			return null;

		Calendar cal = Calendar.getInstance();
		cal.setTime(fiducialDate);
		cal.roll(calendarField, offset);
		return cal.getTime();
	}

	/**
	 * 计算相差天数 fDate-sDate
	 * 
	 * @param fDate
	 *            Date
	 * @param sDate
	 *            Date
	 * @return Integer 相差天数，参数为null，返回null
	 */
	public static Integer compareToDay(Date fDate, Date sDate) {
		if (null == fDate || null == sDate)
			return null;

		Long comp = (fDate.getTime() - sDate.getTime()) / (1000 * 60 * 60 * 24);
		return comp.intValue();
	}

	/**
	 * 获取两个时间直接的差，返回一个字符串表示,可以精确到分
	 * 
	 * @param fDate
	 * @param sDate
	 * @return
	 */
	public static String compareToDate(Date fDate, Date sDate) {
		if (null == fDate || null == sDate)
			return null;
		Long day = (fDate.getTime() - sDate.getTime()) / (1000 * 60 * 60 * 24);
		Long hour = ((fDate.getTime() - sDate.getTime()) % (1000 * 60 * 60 * 24))
				/ (1000 * 60 * 60);
		Long min = (((fDate.getTime() - sDate.getTime()) % (1000 * 60 * 60 * 24)) % (1000 * 60 * 60))
				/ (1000 * 60);

		return day + "天" + hour + "小时" + min + "分";
	}

	/**
	 * 计算相差天数 fDate-sDate
	 * 
	 * @param fDate
	 *            String "yyyy-MM-dd"
	 * @param sDate
	 *            String "yyyy-MM-dd"
	 * @return Integer 相差天数，参数为null，返回null
	 */
	public static Integer compareToDay(String fDate, String sDate) {
		if (null == fDate || null == sDate || fDate.equals("")
				|| sDate.equals(""))
			return null;

		return DateUtils.compareToDay(DateFormatHelper.getFormatDate(fDate,
				DateFormatHelper.DATE_FORMAT), DateFormatHelper.getFormatDate(
				sDate, DateFormatHelper.DATE_FORMAT));
	}

	/**
	 * 计算相差天数 fDate-sDate
	 * 
	 * @param fDate
	 *            String
	 * @param sDate
	 *            String
	 * @param dateFormat
	 *            String 日期格式 参考DateFormatHelper类静态属性。
	 *            例：DateFormatHelper.DATE_FORMAT
	 * @return Integer 相差天数，参数为null，返回null
	 */
	public static Integer compareToDay(String fDate, String sDate,
			String dateFormat) {
		if (null == fDate || null == sDate || null == dateFormat
				|| fDate.equals("") || sDate.equals("")
				|| dateFormat.equals(""))
			return null;

		return DateUtils.compareToDay(DateFormatHelper.getFormatDate(fDate,
				dateFormat), DateFormatHelper.getFormatDate(sDate, dateFormat));
	}

	/**
	 * 当前时间与时间段比较,判断当前时间与时间段的关系
	 * 
	 * @param curDate
	 *            Date 当前时间
	 * @param startDate
	 *            Date 时间段开始时间
	 * @param endDate
	 *            Date 时间段结束时间
	 * @return Integer 返回比较结果 常量:BEFORE_SDATE;IN_PERIOD;AFTER_EDATE
	 */
	public static Integer inDayPeriod(Date curDate, Date startDate, Date endDate) {
		if (curDate == null && startDate == null && endDate == null)
			return null;

		if (DateUtils.compareToDay(curDate, startDate) < 0)
			return DateUtils.BEFORE_SDATE;
		else if (DateUtils.compareToDay(curDate, startDate) >= 0
				&& DateUtils.compareToDay(curDate, endDate) <= 0)
			return DateUtils.IN_PERIOD;
		else
			return DateUtils.AFTER_EDATE;
	}

	// public static void main(String[] args) {
	// System.out.println(DateUtil
	// .getCurSysDateStr(DateFormatHelper.TIME_FORMAT_YMDHMSSS));
	// System.out.println(DateUtil.getCurSysDateStrByDate());
	// System.out.println(DateUtil.getCurSysDateStrBySecond());
	// System.out.println(DateUtil.getFirstDateStr(new Date(),
	// DateUtil.QUARTER, 0));
	// System.out.println(DateUtil.getLastDateStr(new Date(),
	// DateUtil.QUARTER, 0));

	// System.out.println(DateUtil.getWeekOfYear(DateFormatHelper
	// .getFormatDate("2008-01-18", DateFormatHelper.DATE_FORMAT), 0,
	// Calendar.SATURDAY));

	// System.out.println(DateUtil.countSpecifyDayOfWeek("2008-1-22",
	// "2008-1-1", Calendar.SUNDAY));

	// System.out.println(DateUtil.compareToDay("2007-12-31", "2007-07-01")
	// + "");
	// }
	
   public static void main(String[] args) {
	//System.out.println(DateUtils.getNow());
	System.out.println(getCurSysDateStrBySecond());   
    System.out.println(getCurSysDateStr());
   }
}
