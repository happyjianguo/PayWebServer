package com.trade.ali.test;

import java.util.Map;

import com.common.dicts.Dict;
import com.trade.ali.trading.AliPreCreate;
import com.util.*;
import org.junit.Test;

import com.trade.ali.trading.AliCreateMer;

public class AliCreateMerTest {

	@Test
	public void SocketReq() throws Exception {

		Map<String,String> mapAliCreateMer = AliCreateMer.getData();
		HttpUtil.httpRequest(PropertyUtil.getProperty(Dict.createMer),mapAliCreateMer);


	}
	
}
