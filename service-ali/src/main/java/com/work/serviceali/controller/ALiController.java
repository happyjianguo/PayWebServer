package com.work.serviceali.controller;

import com.work.general.dicts.Dict;
import com.work.general.parameters.InputParam;
import com.work.general.parameters.OutputParam;
import com.work.general.pub.PubClz;
import com.work.serviceali.service.AliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ALiController extends PubClz {

    @Autowired
    AliService aliService;

    @RequestMapping(value = "/microPay")
    public String microPay(@RequestParam String params) {
        InputParam inputParam = new InputParam();
        inputParam.putParam(Dict.outTradeNo, "");
        inputParam.putParam(Dict.authCode, "");
        inputParam.putParam(Dict.orderAmount, "");
        inputParam.putParam(Dict.subject, "");
        inputParam.putParam(Dict.body, "");
        inputParam.putParam(Dict.subMerId, "");
        OutputParam outputParam = aliService.microPay(inputParam);
        return "";
    }

    @RequestMapping(value = "/prePay")
    public String prePay(Map<String,String> map) {
        String outTradeNo = map.get(Dict.outTradeNo);
        String orderAmount = map.get(Dict.orderAmount);
        String subject = map.get(Dict.subject);
        String subMchId = map.get(Dict.subMchId);
        String body = map.get(Dict.body);
        System.out.println("ali"+map.get("orderAmount"));
        System.out.println("aliv"+map.get("subject"));
        InputParam inputParam = new InputParam();
        inputParam.putParam(Dict.outTradeNo, outTradeNo);
        inputParam.putParam(Dict.orderAmount, orderAmount);
        inputParam.putParam(Dict.subject, subject);
        inputParam.putParam(Dict.subMchId, subMchId);
        inputParam.putParam(Dict.body, body);
        OutputParam outputParam = aliService.prePay(inputParam);
        return "";
    }


}
