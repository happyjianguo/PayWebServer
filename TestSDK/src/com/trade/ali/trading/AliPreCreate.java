package com.trade.ali.trading;

import com.common.dicts.Dict;
import com.util.DateUtil;

import java.util.HashMap;
import java.util.Map;

public class AliPreCreate {

	public static Map<String, String> getData() {

		System.out.println("开始组装支付宝主扫报文");

		Map<String, String> map = new HashMap<String, String>();
		map.put(Dict.outTradeNo, "3000"+DateUtil.getDateStr("yyyyMMddHHmmss"));
		map.put(Dict.orderAmount,"0.01");
		map.put(Dict.subject,"扫码测试商品");
		map.put(Dict.subMchId,"2088000419225331");
		map.put(Dict.body,"扫码测试商品");

		return map;
	}

}
