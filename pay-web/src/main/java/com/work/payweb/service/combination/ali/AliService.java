package com.work.payweb.service.combination.ali;

import java.util.Map;

public interface AliService {

    String microPay(String params);

    String prePay(Map<String,String> map);

    String createMer(Map<String, String> map);
}
