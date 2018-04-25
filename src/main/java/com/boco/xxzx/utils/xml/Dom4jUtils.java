package com.boco.xxzx.utils.xml;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.DOMReader;
import org.dom4j.io.DOMWriter;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.boco.xxzx.utils.log.Loger;

/**
 * 利用dom4j处理xml的工具类，包含xml操作的常用方法
 */
public class Dom4jUtils {
	private static Logger logger = Loger.log(Dom4jUtils.class);

	// ----------- get xml ------------
	/**
	 * 通过Xpath 获取指定节点指定属性
	 * 
	 * 注：只支持获取单节点属性
	 * 
	 * @param doc
	 *            Document xml对象
	 * @param nodeXpath
	 *            String 定位节点Xpath e.g //foo/bar/author
	 * @param attributeName
	 *            String 节点属性Xpath e.g name
	 * @return String 属性值 若无或异常返回null
	 */
	public static String findAttribute(Document doc, String nodeXpath,
			String attributeName) {
		if (null == doc || null == nodeXpath || null == attributeName)
			return null;

		Node node = doc.selectSingleNode(nodeXpath);
		if (null == node)
			return null;
		return node.valueOf("@" + attributeName);
	}

	/**
	 * 通过Xpath 获取指定节点所有属性
	 * 
	 * @param doc
	 *            Document xml对象
	 * @param nodeXpath
	 *            String 定位节点Xpath e.g //a
	 * @return Map<String,String> 若无返回空Map
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, String> findAttributes(Document doc,
			String nodeXpath) {
		Map<String, String> map = new HashMap<String, String>();
		if (null == doc || null == nodeXpath)
			return map;

		List list = doc.selectNodes(nodeXpath + "/@*");
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Attribute attribute = (Attribute) iter.next();
			map.put(attribute.getName(), attribute.getValue());
		}
		return map;
	}

	/**
	 * 通过Xpath 获取指定节点对象
	 * 
	 * @param doc
	 *            Document xml对象
	 * @param nodeXpath
	 *            String 定位节点Xpath e.g //a[@id='b1']
	 * @return Element 若无返回null
	 */
	public static Element findElement(Document doc, String nodeXpath) {
		if (null == doc || null == nodeXpath)
			return null;

		Node node = doc.selectSingleNode(nodeXpath);
		if (null == node)
			return null;
		return (Element) node;
	}

	/**
	 * 通过Xpath 获取指定节点集合
	 * 
	 * @param doc
	 *            Document xml对象
	 * @param nodeXpath
	 *            String 定位节点Xpath e.g //a[@name='name1']
	 * @return Element[] 若无返回空数组
	 */
	@SuppressWarnings({ "rawtypes" })
	public static Element[] findElements(Document doc, String nodeXpath) {
		List<Element> list = new ArrayList<Element>();
		if (null == doc || null == nodeXpath)
			return list.toArray(new Element[0]);

		List nodelist = doc.selectNodes(nodeXpath);
		for (Iterator iter = nodelist.iterator(); iter.hasNext();) {
			list.add((Element) iter.next());
		}

		Element[] elements = new Element[list.size()];
		return list.toArray(elements);
	}

	/**
	 * 获取xml标签内容 如果xml里有重复标签 只返回第一个
	 * 
	 * @param xmlStr
	 *            String xml字符串
	 * @param elementName
	 *            标签名
	 * @return String 标签内容 若无返回null
	 */
	@Deprecated
	public static String StringXmlValue(String xmlStr, String elementName) {
		if (null == xmlStr || null == elementName)
			return null;

		Document document = buildFromXMLString(xmlStr, null);
		if (null == document)
			return null;

		Element element = document.getRootElement();
		Element findElement = findElement(element, elementName);
		return findElement == null ? "" : findElement.getTextTrim();
	}

	/**
	 * 在xml tree中递归查找指定标签名的对象
	 * 
	 * 如果xml里有重复标签 只返回第一个
	 * 
	 * @param node
	 *            Element 待便利标签tree的根节点
	 * @param elementName
	 *            查找标签名
	 * @return Element 返回找到的标签对象 若无返回null
	 */
	@SuppressWarnings("rawtypes")
	@Deprecated
	public static Element findElement(Element node, String elementName) {
		if (null == node || null == elementName)
			return null;

		Element element = null;
		if (node.getName().equals(elementName)) {
			element = node;
		} else {
			List child = node.elements();
			if (!child.isEmpty()) {
				Element selectedNode = null;
				for (int i = 0; i < child.size(); i++) {
					selectedNode = (Element) child.get(i);
					if (selectedNode.getName().equals(elementName)) {
						element = selectedNode;
						break;
					} else {
						selectedNode = findElement(selectedNode, elementName);
						if (selectedNode != null) {
							element = selectedNode;
							break;
						}
					}
				}
			}
		}
		return element;
	}

	// ----------- u&d xml --------------
	/**
	 * 在指定节点下添加节点，xpath必须是单节点
	 * 
	 * @param doc
	 *            Document
	 * @param parentNodeXpath
	 *            String 父节点xpath
	 * @param name
	 *            String 添加节点name
	 * @param text
	 *            String 添加节点内容
	 * @param attributes
	 *            Map<String,String> 添加节点属性
	 * @return Boolean true-成功；false-失败
	 */
	public static Boolean addElement(Document doc, String parentNodeXpath,
			String name, String text, Map<String, String> attributes) {
		Boolean flag = false;

		if (doc == null || parentNodeXpath == null
				|| parentNodeXpath.equals("") || name == null
				|| name.equals("")) {
			logger
					.warn("document is null or parentNodeXpath is null or name is null");
			return flag;
		}

		Element parentElement = findElement(doc, parentNodeXpath);
		// parent is null
		if (parentElement == null) {
			logger.warn("parentElement is null");
		}
		// add
		else {
			Element element = parentElement.addElement(name);

			if (text != null)
				element.setText(text);

			Set<String> keySet = attributes.keySet();
			for (String key : keySet) {
				if (attributes.get(key) != null)
					element.addAttribute(key, attributes.get(key));
			}

			flag = true;
		}

		return flag;
	}

	/**
	 * 更新节点内容和属性，xpath必须是单节点
	 * 
	 * @param doc
	 *            Document
	 * @param nodeXpath
	 *            String 目标节点xpath
	 * @param attributes
	 *            Map<String,String> 待更新属性map
	 * @param text
	 *            节点内容
	 * @return Boolean true-成功；false-失败
	 */
	public static Boolean updateElement(Document doc, String nodeXpath,
			Map<String, String> attributes, String text) {
		Boolean flag = false;

		if (doc == null || nodeXpath == null || nodeXpath.equals("")) {
			logger.warn("document is null or nodeXpath is null");
			return flag;
		}

		Element element = findElement(doc, nodeXpath);
		// node is null
		if (element == null) {
			logger.warn("无目标node：" + nodeXpath);
		}
		// update
		else {
			// text
			element.setText(text);
			// attribute
			Set<String> keySet = attributes.keySet();
			for (String key : keySet) {
				Attribute att = element.attribute(key);
				// 新增属性
				if (att == null)
					element.addAttribute(key, attributes.get(key));
				// 更新属性
				else
					att.setValue(attributes.get(key));
			}
			flag = true;
		}

		return flag;
	}

	/**
	 * 删除节点集合,符合nodeXpath的节点都会删除
	 * 
	 * @param doc
	 *            Document
	 * @param nodeXpath
	 *            String 待删节点xpath
	 * @return Boolean true-成功；false-失败
	 */
	public static Boolean removeElement(Document doc, String nodeXpath) {
		Boolean flag = false;

		if (doc == null || nodeXpath == null || nodeXpath.equals("")) {
			logger.warn("document is null or nodeXpath is null");
			return flag;
		}

		Element[] elements = findElements(doc, nodeXpath);
		// node is null
		if (elements == null || elements.length == 0) {
			logger.warn("无目标node：" + nodeXpath);
		}
		// remove
		else {
			for (Element element : elements) {
				Element parentElement = element.getParent();
				parentElement.remove(element);
			}
		}

		return flag;
	}

	// ----------- output xml ------------
	/**
	 * 将Document输出到 System.out 按utf-8编码输出
	 * 
	 * @param doc
	 *            Document xml对象
	 */
	public static void outputToStdout(Document doc) {
		outputToStdout(doc, "UTF-8");
	}

	/**
	 * 将Document输出到 System.out 按指定编码输出
	 * 
	 * @param doc
	 *            Document xml对象
	 * @param encoding
	 *            String 编码
	 */
	public static void outputToStdout(Document doc, String encoding) {
		if (null == doc || null == encoding)
			return;

		OutputFormat format = new OutputFormat("\t", true, encoding);
		try {
			XMLWriter xmlwriter = new XMLWriter(System.out, format);
			xmlwriter.write(doc);
			xmlwriter.close();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * 将Document转换为xml字符串输出，按UTF-8编码输出
	 * 
	 * @param doc
	 *            Document xml对象
	 * @return String xml字符串 若无或异常返回空字符串
	 */
	public static String outputToString(Document doc) {
		return outputToString(doc, "UTF-8");
	}

	/**
	 * 将Document转换为xml字符串输出，按指定编码输出
	 * 
	 * @param doc
	 *            Document xml对象
	 * @param encoding
	 *            String 编码
	 * @return String xml字符串 若无或异常返回空字符串
	 */
	public static String outputToString(Document doc, String encoding) {
		ByteArrayOutputStream byteRep = new ByteArrayOutputStream();

		if (null == doc || null == encoding)
			return byteRep.toString();

		OutputFormat format = new OutputFormat("\t", true, encoding);
		try {
			XMLWriter xmlwriter = new XMLWriter(byteRep, format);
			xmlwriter.write(doc);
			xmlwriter.close();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}

		return byteRep.toString();
	}

	/**
	 * 将Document转换为文件，按UTF-8编码输出
	 * 
	 * @param doc
	 *            Document xml对象
	 * @param filePath
	 *            String 保存文件绝对路径
	 */
	public static void outputToFile(Document doc, String filePath) {
		outputToFile(doc, filePath, "UTF-8");
	}

	/**
	 * 将Document转换为文件，按指定编码输出
	 * 
	 * @param doc
	 *            Document xml对象
	 * @param filePath
	 *            String 保存文件绝对路径
	 * @param encoding
	 *            String 编码
	 */
	public static void outputToFile(Document doc, String filePath,
			String encoding) {
		if (null == doc || null == filePath || null == encoding)
			return;

		OutputFormat format = new OutputFormat("\t", true, encoding);
		try {
			FileWriter writer = new FileWriter(filePath);
			XMLWriter xmlwriter = new XMLWriter(writer, format);
			xmlwriter.write(doc);
			xmlwriter.close();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * 将dom4j Document转换为 w3c Document对象
	 * 
	 * @param document
	 *            Document dom4j对象
	 * @return org.w3c.dom.Document 若无或异常返回null
	 */
	public static org.w3c.dom.Document outputToDom(Document document) {
		if (null == document)
			return null;

		DOMWriter domWriter = new DOMWriter();
		org.w3c.dom.Document doc = null;
		try {
			doc = domWriter.write(document);
		} catch (DocumentException e) {
			logger.error(e.getMessage());
		}

		return doc;
	}

	// ----------- build xml ------------
	/**
	 * 创建空Document
	 * 
	 * @param encoding
	 *            String 编码
	 * @return Document
	 */
	public static Document buildEmpty(String encoding) {
		if (encoding == null)
			encoding = "UTF-8";

		Document doc = DocumentHelper.createDocument();
		doc.setXMLEncoding(encoding);

		return doc;
	}

	/**
	 * 通过xml文件构建xml Document对象
	 * 
	 * @param filePath
	 *            String xml文件路径
	 * @param encoding
	 *            String 编码
	 * @return Document dom4j xml 对象 若无或异常返回null
	 */
	public static Document buildFromFile(String filePath, String encoding) {
		if (null == filePath)
			return null;

		encoding = encoding == null ? "UTF-8" : encoding;

		Document doc = null;
		SAXReader reader = new SAXReader();
		reader.setEncoding(encoding);
		try {
			doc = reader.read(new File(filePath));
		} catch (DocumentException e) {
			logger.error(e.getMessage());
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
		}
		return doc;
	}

	/**
	 * 通过xml字符串构建xml Document对象
	 * 
	 * @param xmlString
	 *            String Xml的字符串
	 * @param encoding
	 *            String 编码
	 * @return Document dom4j xml 对象 若无或异常返回null
	 */
	public static Document buildFromXMLString(String xmlString, String encoding) {
		if (null == xmlString)
			return null;

		encoding = encoding == null ? "UTF-8" : encoding;

		Document doc = null;
		SAXReader reader = new SAXReader();
		reader.setEncoding(encoding);

		try {
			doc = reader.read(new StringReader(xmlString));
		} catch (DocumentException e) {
			logger.error(e.getMessage());
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
		}
		return doc;
	}

	/**
	 * 通过xml文件读取流构建xml Document对象
	 * 
	 * @param stream
	 *            InputStream xml文件读取流
	 * @param encoding
	 *            String 编码
	 * @return Document dom4j xml 对象 若无或异常返回null
	 */
	public static Document buildFromXMLStream(InputStream stream,
			String encoding) {
		if (null == stream)
			return null;

		encoding = encoding == null ? "UTF-8" : encoding;

		Document doc = null;
		SAXReader reader = new SAXReader();
		reader.setEncoding(encoding);
		try {
			doc = reader.read(stream);
		} catch (DocumentException e) {
			logger.error(e.getMessage());
		}
		return doc;
	}

	/**
	 * 通过org.w3c.dom.Document构建xml Document对象
	 * 
	 * @param document
	 *            org.w3c.dom.Document w3c xml对象
	 * @return Document dom4j xml 对象 若无返回null
	 */
	public static Document buildFromDom(org.w3c.dom.Document document) {
		if (null == document)
			return null;

		DOMReader reader = new DOMReader();
		return reader.read(document);
	}
}
