package com.trade.ali.trading;

import java.util.HashMap;
import java.util.Map;

import com.common.constants.SDKConstant;
import com.util.DateUtil;

public class AliCreateMer {

	public static Map<String, String> getData() {
		System.out.println("开始组装支付宝商户新增报文");

		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "aa81009041035");
		map.put("aliasName", "aa81009041035");
		map.put("servicePhone", "15959999999");
		map.put("contactName", "奥巴马");
		map.put("address", "鄞州区政府");
		map.put("mcc", "5331");
		map.put("orgPid", "2088721382101609");

		return map;
	}

}
