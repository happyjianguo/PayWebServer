package com.trade.ali.trading;

import java.util.HashMap;
import java.util.Map;

import com.common.constants.SDKConstant;
import com.common.dicts.Dict;
import com.util.DateUtil;

public class AliCreateMer {

	public static Map<String, String> getData() {
		System.out.println("��ʼ��װ֧�����̻���������");

		Map<String, String> map = new HashMap<String, String>();
		map.put(Dict.name, "�¿�˹���޹�˾");
		map.put(Dict.aliasName, "�¿�˹");
		map.put(Dict.servicePhone, "15959999999");
		map.put(Dict.contactName, "�°���");
		map.put(Dict.address, "۴��������");
		map.put(Dict.mcc, "5331");

		return map;
	}

}
