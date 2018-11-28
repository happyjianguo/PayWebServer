package com.work.serviceali.controller;

import com.work.general.annotations.TrackTime;
import com.work.general.dicts.Dict;
import com.work.general.parameters.InputParam;
import com.work.general.parameters.OutputParam;
import com.work.general.pub.PubClz;
import com.work.serviceali.service.AliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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

    @TrackTime(param = "支付宝服务扫码交易")
    @RequestMapping(value = "/prePay")
    public OutputParam prePay(@RequestBody InputParam inputParam) {
        logger.info("支付宝扫码交易请求报文:"+inputParam);
        OutputParam outputParam = aliService.prePay(inputParam);
        return outputParam;
    }

    @TrackTime(param = "支付宝服务商户新增")
    @RequestMapping(value = "/createMer")
    public OutputParam createMer(@RequestBody InputParam inputParam) {
        logger.info("支付宝新增商户请求报文:"+inputParam);
        OutputParam outputParam = aliService.createMer(inputParam);
        return outputParam;
    }

    @TrackTime(param = "支付宝服务订单查询")
    @RequestMapping(value = "/orderQuery")
    public OutputParam orderQuery(@RequestBody InputParam inputParam) {
        OutputParam outputParam = aliService.orderQuery(inputParam);
        return outputParam;
    }


}
