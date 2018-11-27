package com.trade.ali.trading;

import com.common.dicts.Dict;

import java.util.HashMap;
import java.util.Map;

public class AliEditMer {

	public static Map<String, String> getData() {
		System.out.println("开始组装支付宝商户修改报文");

		Map<String, String> map = new HashMap<String, String>();
//		map.put(Dict.merId, "1122222121212");
//		map.put(Dict.name, "奥克斯有限公司");
		map.put(Dict.aliasName, "奥克斯12345");
//		map.put(Dict.servicePhone, "15959999999");
//		map.put(Dict.contactName, "奥巴马");
//		map.put(Dict.address, "鄞州区政府");
//		map.put(Dict.mcc, "5331");

		return map;
	}

}
