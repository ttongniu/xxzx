package com.boco.xxzx.utils.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.web.util.HtmlUtils;

/**
 * 字符串工具类 系统对字符串相关处理都由该类提供
 * 
 * @see org.springframework.web.util.HtmlUtils;
 * 
 * @author Metanoia.Lang
 */
public class StringUtils {

	/**
	 * 将元素为String类型的Iterator集合，按separate分隔符，转换成String。
	 * 如果Iterator集合中元素为null，转换时写入"null"。
	 * 
	 * 例：{"a","b","c"} separate="," return * "a,b,c"
	 * 
	 * @param iterator
	 *            Iterator<String>
	 * @param separate
	 *            String 转换成String后的分隔符
	 * @return String "null" if item is null
	 */
	public static String convertToString(Iterator<String> iterator,
			String separate) {
		if (iterator == null || separate == null)
			return null;

		StringBuffer str = new StringBuffer();
		while (iterator.hasNext()) {
			String item = iterator.next();
			str.append(item == null ? "null" : item);
			str.append(separate);
		}
		return str.length() == 0 ? "" : str.substring(0, str
				.lastIndexOf(separate));
	}

	/**
	 * 将元素为String类型的Iterator集合，按separate分隔符，转换成String。
	 * 如果Iterator数组中元素为null，转换时写入"null"。
	 * 
	 * 例：{"a","b","c"} separate="," return * "a,b,c"
	 * 
	 * @param iterator
	 *            String[]
	 * @param separate
	 *            String 转换成String后的分隔符
	 * @return String "null" if item is null
	 */
	public static String convertToString(String[] iterator, String separate) {
		if (iterator == null || separate == null)
			return null;

		StringBuffer str = new StringBuffer();
		for (String item : iterator) {
			str.append(item == null ? "null" : item);
			str.append(separate);
		}
		return str.length() == 0 ? "" : str.substring(0, str
				.lastIndexOf(separate));
	}

	/**
	 * 根据分隔符分离字符串，比较两个字符串是否有相同子串。 两个比较字符串分隔符必须相同。
	 * 
	 * @param strA
	 *            String
	 * @param strB
	 *            String
	 * @param separator
	 *            String
	 * @return Boolean 只要有相同子串则返回true，否则返回false。
	 */
	public static boolean compareString(String strA, String strB,
			String separator) {
		if (strA == null || strB == null || separator == null)
			return false;

		String[] strArrayA = strA.split(separator);
		strB = separator + strB + separator;

		for (int i = 0; i < strArrayA.length; i++) {
			if (strB.indexOf(separator + strArrayA[i] + separator) > -1)
				return true;
		}

		return false;
	}

	/**
	 * 截取指定位字符串，将指定位后面的字符用指定字符串替换。 例："abcdefg" 从第三位截取，用"..."替换，结果为 "abc..."
	 * 
	 * @param str
	 *            String 要截取的字符串
	 * @param n
	 *            int 指定位数 0-base
	 * @param placeholder
	 *            String 替换被被截取部分的字符串
	 * @return String 截取后的字符串
	 */
	public static String getTokenString(String str, int n, String placeholder) {
		byte[] newbyte = str.getBytes();
		if (newbyte.length > n) {
			String strA = new String(newbyte, 0, n);
			char c = strA.charAt(strA.length() - 1);
			if (Character.isLetterOrDigit(c)) {
				return new String(newbyte, 0, n) + placeholder;
			} else {
				return new String(newbyte, 0, n - 1) + placeholder;
			}
		} else {
			return str;
		}
	}

	/**
	 * 将html转换成字符 e.g. <br>
	 * -> &lt;br&gt;
	 * 
	 * @param html
	 *            String html标签
	 * @return String 若参数null return "null"
	 */
	public static String htmlConvertToString(String html) {
		return HtmlUtils.htmlEscape(html);
	}

	/**
	 * 将html字符转换成html标签 e.g. &lt;br&gt; -> <br>
	 * 
	 * @param string
	 *            String html字符
	 * @return String 若参数null return "null"
	 */
	public static String stringConvertToHtml(String string) {
		return HtmlUtils.htmlUnescape(string);
	}

	/**
	 * 将categoryList里的元素，按size进行分组用","分割成字符串，形成新的list<String>
	 * 例:categoryList={"a","b","c","d","e"} size=2 return {"a,b","c,d","e"}
	 * 
	 * @param categoryList
	 *            List<String> 分类集合
	 * @return List<String> 返回截取之后的集合
	 */
	public static List<String> reduceArray(List<String> categoryList, int size) {
		List<String> reduceArray = new ArrayList<String>();
		int listSize = categoryList.size();
		int model = listSize % size == 0 ? listSize / size : listSize / size
				+ 1;
		int residue = listSize % size == 0 ? size : listSize % size;
		for (int i = 0; i < model; i++) {
			StringBuffer array = new StringBuffer();
			int k = 0;
			if (i == model - 1) {
				k = residue;
			} else
				k = size;
			for (int j = 0; j < k; j++) {
				array.append(categoryList.get(i * size + j));
				array.append(",");
			}
			reduceArray.add(array.toString());
		}
		return reduceArray;
	}


}
