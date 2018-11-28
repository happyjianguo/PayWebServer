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
        String bodyDecond = URLDecoder.decode(body, "UTF-8");
        Map<String, String> map = TransUtil.jsonToMap(bodyDecond.substring(0,bodyDecond.length()-1));
        return aliService.prePay(map);
    }

    @TrackTime(param = "支付宝交易查询交易")
    @RequestMapping(value="/orderQuery")
    public String orderQuery(@RequestBody String body) throws UnsupportedEncodingException {
        String bodyDecond = URLDecoder.decode(body, "UTF-8");
        Map<String, String> map = TransUtil.jsonToMap(bodyDecond.substring(0,bodyDecond.length()-1));
        return aliService.orderQuery(map);
    }

    @TrackTime(param = "支付宝新增商户")
    @RequestMapping(value="/createMer")
    public String createMer(@RequestBody String body) throws UnsupportedEncodingException {
        String bodyDecond = URLDecoder.decode(body, "UTF-8");
        Map<String, String> map = TransUtil.jsonToMap(bodyDecond.substring(0,bodyDecond.length()-1));
        return aliService.createMer(map);
    }

    @TrackTime(param = "支付宝商户查询")
    @RequestMapping(value="/queryMer")
    public String queryMer(@RequestBody String body) throws UnsupportedEncodingException {
        String bodyDecond = URLDecoder.decode(body, "UTF-8");
        Map<String, String> map = TransUtil.jsonToMap(bodyDecond.substring(0,bodyDecond.length()-1));
        return aliService.queryMer(map);
    }

    @TrackTime(param = "支付宝商户删除")
    @RequestMapping(value="/deleteMer")
    public String deleteMer(@RequestBody String body) throws UnsupportedEncodingException {
        String bodyDecond = URLDecoder.decode(body, "UTF-8");
        Map<String, String> map = TransUtil.jsonToMap(bodyDecond.substring(0,bodyDecond.length()-1));
        boolean result = aliService.deleteMer(map);
        return null;
    }

    @TrackTime(param = "支付宝商户修改")
    @RequestMapping(value="/editMer")
    public String editMer(@RequestBody String body) throws UnsupportedEncodingException {
        String bodyDecond = URLDecoder.decode(body, "UTF-8");
        Map<String, String> map = TransUtil.jsonToMap(bodyDecond.substring(0,bodyDecond.length()-1));
        boolean result = aliService.updateMer(map);
        return null;
    }
}
