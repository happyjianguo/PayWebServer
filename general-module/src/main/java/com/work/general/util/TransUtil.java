package com.work.general.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.work.general.constants.StringConstans;
import net.sf.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TransUtil {

	public static String mapToXml(Map<String, String> map) {
		StringBuffer sb = new StringBuffer();
		try {
			sb.append("<xml>");

			Set<String> set = map.keySet();
			for (Iterator<String> it = set.iterator(); it.hasNext();) {
				String key = it.next();
				Object value = map.get(key);
				if (null == value) {
					value = "";
				}
				sb.append("<" + key + ">" + value + "</" + key + ">");
			}
			sb.append("</xml>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static Map<String, String> xmlToMap(String xmlString)
			throws Exception {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputStream is = getStringStream(xmlString);
		Document document = builder.parse(is);

		NodeList allNodes = document.getFirstChild().getChildNodes();
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < allNodes.getLength(); i++) {
			Node node = allNodes.item(i);
			nodeIterator(node, map, 2000);
		}

		return map;
	}

	public static InputStream getStringStream(String sInputString)
			throws UnsupportedEncodingException {
		ByteArrayInputStream tInputStringStream = null;
		if (sInputString != null && !sInputString.trim().equals("")) {
			tInputStringStream = new ByteArrayInputStream(sInputString
					.getBytes(StringConstans.CHARSET.CHARSET_UTF_8));
		}
		return tInputStringStream;
	}

	public static void nodeIterator(Node node, Map<String, String> map, int deep) {
		if (deep > 2000) {
			deep = 2000;
		}

		if (--deep < 0) {
			return;
		}

		if (node instanceof Element && node.hasChildNodes()) {
			for (int i = 0; i < node.getChildNodes().getLength(); i++) {
				Node subNode = node.getChildNodes().item(i);
				if (subNode.getNodeType() == Node.ELEMENT_NODE) {
					nodeIterator(subNode, map, 2000);
				} else {
					map.put(node.getNodeName(), node.getTextContent());
				}
			}
		}
	}

	public static Map<String, String> jsonToMap(String jsonObj) {
		JSONObject jsonObject = JSONObject.fromObject(jsonObj);
		return (Map<String, String>)jsonObject;
	}

	public static void main(String[] args) {
	}

}
