package com.work.payweb.service.combination.ali;

import java.util.Map;

public interface AliService {

    String microPay(String params);

    String prePay(Map<String,String> map);

    String createMer(Map<String, String> map);

    String queryMer(Map<String, String> map);

    boolean deleteMer(Map<String, String> map);

    boolean updateMer(Map<String, String> map);

    String orderQuery(Map<String, String> map);
}
