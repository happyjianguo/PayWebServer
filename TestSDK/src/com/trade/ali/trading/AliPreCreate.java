package com.trade.ali.trading;

import com.common.dicts.Dict;
import com.util.DateUtil;

import java.util.HashMap;
import java.util.Map;

public class AliPreCreate {

	public static Map<String, String> getData() {

		System.out.println("��ʼ��װ֧������ɨ����");

		Map<String, String> map = new HashMap<String, String>();
		map.put(Dict.outTradeNo, "3000"+DateUtil.getDateStr("yyyyMMddHHmmss"));
		map.put(Dict.orderAmount,"0.01");
		map.put(Dict.subject,"ɨ�������Ʒ");
		map.put(Dict.subMchId,"2088000419225331");
		map.put(Dict.body,"ɨ�������Ʒ");

		return map;
	}

}
