package com.work.payweb.service;

import com.work.payweb.hystric.AliServiceHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@FeignClient(value = "service-ali", fallback = AliServiceHystric.class)
public interface AliService {

    @RequestMapping(value = "/microPay")
    String microPay(String params);

    @RequestMapping(value = "/prePay")
    String prePay(Map<String,String> map);

}
