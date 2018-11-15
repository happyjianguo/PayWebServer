package com.work.payweb.controller;

import com.work.general.annotations.TrackTime;
import com.work.general.pub.PubClz;
import com.work.general.util.RedisStringUtil;
import com.work.general.util.RedisUtil;
import com.work.general.util.TransUtil;
import com.work.payweb.service.combination.ali.AliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

@RestController
public class PayWebController extends PubClz{

    @Autowired
    AliService aliService;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    RedisStringUtil redisStringUtil;

    @RequestMapping(value="/microPay")
    public String microPay(@RequestParam String params){
        return aliService.microPay(params);
    }

    @TrackTime(param = "支付宝扫码交易")
    @RequestMapping(value="/prePay")
    public String prePay(@RequestBody String body) throws UnsupportedEncodingException {
        logger.info("进入PayWebController.......");

        redisStringUtil.set("ssss1","asdf1");
//        Object obj = redisStringUtil.get("ssss1");
//        logger.info("obj:"+obj);
//
//        redisUtil.set("sd", "sdf");
//        Object obj1 = redisUtil.get("sd");
//        logger.info("obj1:"+obj1);

        String bodyDecond = URLDecoder.decode(body, "UTF-8");
        Map<String, String> map = TransUtil.jsonToMap(bodyDecond.substring(0,bodyDecond.length()-1));
        logger.info(map.toString());
        return aliService.prePay(map);
    }

    @TrackTime(param = "支付宝新增商")
    @RequestMapping(value="/createMer")
    public String createMer(@RequestBody String body) throws UnsupportedEncodingException {
        String bodyDecond = URLDecoder.decode(body, "UTF-8");
        Map<String, String> map = TransUtil.jsonToMap(bodyDecond.substring(0,bodyDecond.length()-1));
        return aliService.createMer(map);
    }


}
