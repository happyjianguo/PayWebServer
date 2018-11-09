package com.work.payweb.controller;

import com.work.general.dicts.Dict;
import com.work.payweb.service.AliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class PayWebController {

    @Autowired
    AliService aliService;

    @RequestMapping(value="/microPay")
    public String microPay(@RequestParam String params){
        return aliService.microPay(params);
    }

    @RequestMapping(value="/prePay")
    public String prePay(@RequestBody Map<String,String> param){
        System.out.println("aaaaaaa"+param);
        return aliService.prePay(param);
    }

}
