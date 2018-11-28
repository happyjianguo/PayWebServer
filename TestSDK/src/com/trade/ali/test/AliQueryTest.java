package com.trade.ali.test;

import java.util.Map;

import com.common.dicts.Dict;
import com.trade.ali.trading.AliQueryMer;
import com.util.*;
import org.junit.Test;

import com.trade.ali.trading.AliQuery;
import com.validate.AliValidation;
import com.validate.common.Validation;

public class AliQueryTest {
	@Test
	public void SocketReq() throws Exception {

		Map<String,String> mapAliQuery = AliQuery.getData();
		mapAliQuery.put(Dict.txnSeqId, "100000027");
		HttpUtil.httpRequest(PropertyUtil.getProperty(Dict.orderQuery),mapAliQuery);

	}
}
