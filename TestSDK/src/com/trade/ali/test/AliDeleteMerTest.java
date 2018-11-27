package com.trade.ali.test;

import com.common.dicts.Dict;
import com.trade.ali.trading.AliDeleteMer;
import com.trade.ali.trading.AliEditMer;
import com.util.HttpUtil;
import com.util.PropertyUtil;
import org.junit.Test;

import java.util.Map;

public class AliDeleteMerTest {
	@Test
	public void SocketReq()  throws Exception {
		Map<String,String> mapAliDeleteMer = AliDeleteMer.getData();
		mapAliDeleteMer.put(Dict.merId, "900201811271000045");
		HttpUtil.httpRequest(PropertyUtil.getProperty(Dict.deleteMer),mapAliDeleteMer);
		
	}
}
