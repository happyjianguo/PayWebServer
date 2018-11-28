package com.trade.ali.trading;

import com.common.dicts.Dict;

import java.util.HashMap;
import java.util.Map;

public class AliQuery {

	public static Map<String, String> getData() {

		System.out.println("开始组装支付宝订单查询报文");

		Map<String, String> map = new HashMap<String, String>();
		map.put(Dict.txnSeqId, "100000022");
		return map;

	}

}
