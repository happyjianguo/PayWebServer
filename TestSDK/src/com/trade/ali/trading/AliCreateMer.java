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
		map.put("servicePhone", "15661219999");
		map.put("contactName", "testaa");
		map.put("idCardNo", "6228580107000000861");
		map.put("categoryId", "2016042200000148");
		map.put("address", "sssssssssssssssssssssssssssss");
		map.put("mcc", "5331");
		map.put("orgPid", "2088721382101609");

		return map;
	}

}
