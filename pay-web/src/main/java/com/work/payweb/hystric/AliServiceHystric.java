package com.work.payweb.hystric;

import com.work.payweb.service.AliService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AliServiceHystric implements AliService{
    @Override
    public String microPay(String params) {
        return "ali microPay service is not available !";
    }

    @Override
    public String prePay(Map<String,String> map) {
        return "ali prePay service is not available !";
    }

}
