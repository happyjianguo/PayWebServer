package com.work.payweb.controller;

import com.work.general.annotations.TrackTime;
import com.work.general.pub.PubClz;
import com.work.general.service.redisservice.RedisUtil;
import com.work.general.util.RequestUtil;
import com.work.general.util.TransUtil;
import com.work.payweb.service.combination.ali.AliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PayWebController extends PubClz{

    @Autowired
    AliService aliService;
    @Autowired
    RedisUtil redisUtil;

    @RequestMapping(value="/microPay")
    public String microPay(@RequestParam String params){
        return aliService.microPay(params);
    }

    @TrackTime(param = "支付宝扫码交易")
    @RequestMapping(value="/prePay")
    public String prePay(HttpServletRequest request) throws UnsupportedEncodingException {
        Map<String,String> map = RequestUtil.getMapFromRequest(request);
        return aliService.prePay(map);
    }

    @TrackTime(param = "支付宝交易查询交易")
    @RequestMapping(value="/orderQuery")
    public String orderQuery(HttpServletRequest request) throws UnsupportedEncodingException {
        Map<String,String> map = RequestUtil.getMapFromRequest(request);
        return aliService.orderQuery(map);
    }

    @TrackTime(param = "支付宝新增商户")
    @RequestMapping(value="/createMer")
    public String createMer(HttpServletRequest request) throws UnsupportedEncodingException {
        Map<String,String> map = RequestUtil.getMapFromRequest(request);
        return aliService.createMer(map);
    }

    @TrackTime(param = "支付宝商户查询")
    @RequestMapping(value="/queryMer")
    public String queryMer(HttpServletRequest request) throws UnsupportedEncodingException {
        Map<String,String> map = RequestUtil.getMapFromRequest(request);
        return aliService.queryMer(map);
    }

    @TrackTime(param = "支付宝商户删除")
    @RequestMapping(value="/deleteMer")
    public String deleteMer(HttpServletRequest request) throws UnsupportedEncodingException {
        Map<String,String> map = RequestUtil.getMapFromRequest(request);
        boolean result = aliService.deleteMer(map);
        return null;
    }

    @TrackTime(param = "支付宝商户修改")
    @RequestMapping(value="/editMer")
    public String editMer(HttpServletRequest request) throws UnsupportedEncodingException {
        Map<String,String> map = RequestUtil.getMapFromRequest(request);
        boolean result = aliService.updateMer(map);
        return null;
    }


}
