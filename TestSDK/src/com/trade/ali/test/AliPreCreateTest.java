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

		//��ɨ
		Map<String,String> mapAliPreCreate = AliPreCreate.getData();
		HttpUtil.httpRequest(PropertyUtil.getProperty(Dict.socket_ip),mapAliPreCreate);
		
	}
	
	

}
