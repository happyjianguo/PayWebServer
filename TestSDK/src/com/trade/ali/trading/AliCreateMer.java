package com.trade.ali.trading;

import java.util.HashMap;
import java.util.Map;

import com.common.constants.SDKConstant;
import com.util.DateUtil;

public class AliCreateMer {

	public static Map<String, String> getData() {
		System.out.println("��ʼ��װ֧�����̻���������");

		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "aa81009041035");
		map.put("aliasName", "aa81009041035");
		map.put("servicePhone", "15959999999");
		map.put("contactName", "�°���");
		map.put("address", "۴��������");
		map.put("mcc", "5331");
		map.put("orgPid", "2088721382101609");

		return map;
	}

}
