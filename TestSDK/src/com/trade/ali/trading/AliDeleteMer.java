package com.trade.ali.trading;

import com.common.dicts.Dict;

import java.util.HashMap;
import java.util.Map;

public class AliDeleteMer {

	public static Map<String, String> getData() {
		System.out.println("开始组装支付宝商户删除报文");

		Map<String, String> map = new HashMap<String, String>();
		map.put(Dict.merId, "11111111111");

		return map;
	}

}
