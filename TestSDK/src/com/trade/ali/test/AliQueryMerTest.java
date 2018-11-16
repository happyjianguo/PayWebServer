package com.trade.ali.test;

import java.util.Map;

import com.common.dicts.Dict;
import com.trade.ali.trading.AliCreateMer;
import com.util.*;
import org.junit.Test;

import com.trade.ali.trading.AliQueryMer;
import com.validate.AliValidation;
import com.validate.common.Validation;

public class AliQueryMerTest {

	@Test
	public void SocketReq() throws Exception {

		Map<String,String> mapAliQueryMer = AliQueryMer.getData();
		HttpUtil.httpRequest(PropertyUtil.getProperty(Dict.queryMer),mapAliQueryMer);
	}
	
}
