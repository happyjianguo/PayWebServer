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

	/**
	 * map����תxml�ַ���
	 * 
	 * @param map
	 * @return
	 */
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

	/**
	 * ����xmlתmap
	 * 
	 * @param xmlString
	 * @return
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	public static Map<String, String> xmlToMap(String xmlString)
			throws Exception {

		// ������Dom�ķ�ʽ�����ذ�������ҪĿ���Ƿ�ֹAPI�����ذ��ֶ�
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputStream is = getStringStream(xmlString);
		Document document = builder.parse(is);

		// ��ȡ��document�����ȫ�����
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
			// ������ֵ��������ڴ��С��ÿ�εݹ��ջ��С������һ����2000������
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

	/**
	 * json string ת��Ϊ map ����
	 * @param jsonObj
	 * @return
	 */
	public static Map<String, String> jsonToMap(String jsonObj) {
		JSONObject jsonObject = JSONObject.fromObject(jsonObj);
		return (Map<String, String>)jsonObject;
	}

	public static void main(String[] args) {
		String ss = "<xml><remark>&amp;��55357;&amp;��56351;</remark></xml>";
//		String ss = "<xml><remark>&��55357;&��56351;</remark></xml>";
//		String ss = "<xml><remark>sdfsdfs</remark></xml>";
		try {
//			Map<String, String> map = TransUtil.stringToMap("{orderAmount=0.01, subject=ɨ�������Ʒ, outTradeNo=20181112153510, subMchId=2088000203474835, body=ɨ�������Ʒ}");
//			System.out.println(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
