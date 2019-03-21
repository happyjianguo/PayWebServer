package com.trade.ali.test;

import com.common.dicts.Dict;
import com.trade.ali.trading.AliPreCreate;
import com.util.HttpUtil;
import com.util.PropertyUtil;
import org.junit.Test;

import java.util.Map;

public class AliPreCreateTest {
	
	@Test
	public void SocketReq() throws Exception {

		Map<String,String> mapAliPreCreate = AliPreCreate.getData();
		mapAliPreCreate.put(Dict.merId,"900201903211000094");
		HttpUtil.httpRequest(PropertyUtil.getProperty(Dict.prePay),mapAliPreCreate);


	}
	
	

}
