package com.trade.ali.trading;

import java.util.HashMap;
import java.util.Map;

import com.common.constants.SDKConstant;
import com.common.dicts.Dict;
import com.util.DateUtil;

public class AliCreateMer {

	public static Map<String, String> getData() {
		System.out.println("开始组装支付宝商户新增报文");

		Map<String, String> map = new HashMap<String, String>();
		map.put(Dict.name, "奥克斯有限公司");
		map.put(Dict.aliasName, "奥克斯");
		map.put(Dict.servicePhone, "15959999999");
		map.put(Dict.contactName, "奥巴马");
		map.put(Dict.address, "鄞州区政府");
		map.put(Dict.mcc, "5331");

		return map;
	}

}
