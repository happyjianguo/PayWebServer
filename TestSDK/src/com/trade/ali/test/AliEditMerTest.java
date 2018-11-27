package com.trade.ali.test;

import java.util.Map;

import com.common.dicts.Dict;
import com.trade.ali.trading.AliCreateMer;
import com.util.*;
import org.junit.Test;

import com.trade.ali.trading.AliEditMer;

public class AliEditMerTest {
	@Test
	public void SocketReq()  throws Exception {
		Map<String,String> mapAliEditMer = AliEditMer.getData();
		mapAliEditMer.put(Dict.merId, "900201811271000045");
		HttpUtil.httpRequest(PropertyUtil.getProperty(Dict.editMer),mapAliEditMer);
		
	}
}
