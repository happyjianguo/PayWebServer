package com.trade.ali.trading;

import java.util.HashMap;
import java.util.Map;

public class AliQueryMer {

	public static Map<String, String> getData() {
		System.out.println("开始组装支付宝商户查询报文");

		Map<String, String> map = new HashMap<String, String>();
		map.put("merId", "900201811161000023");

		return map;
	}

}
