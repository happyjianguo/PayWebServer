package com.work.payweb.controller;

import com.work.general.util.StringUtil;
import com.work.general.util.TransUtil;
import com.work.payweb.service.AliService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.HashMap;
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
    public String prePay(@RequestBody String body) throws UnsupportedEncodingException {
        String bodyDecond = URLDecoder.decode(body, "UTF-8");
        Map<String, String> map = TransUtil.jsonToMap(bodyDecond.substring(0,bodyDecond.length()-1));
        System.out.println(map);
        return aliService.prePay(map);
    }

//    @RequestMapping(value="/prePay")
//    public String prePay(@RequestBody Map<String,String> param){
//        System.out.println("aaaaaaa"+param);
//        return aliService.prePay(param);
//    }
//    @RequestMapping(value="/prePay")
//    public String prePay(HttpServletRequest request,@RequestBody String body11) throws UnsupportedEncodingException {
////    public String prePay(HttpServletRequest request){
//        System.out.println("ssssssss:"+request.getParameter("orderAmount"));
////               System.out.println("123123");
////        System.out.println("body:"+body11);
////        String body1 = null;
////        try {
//            System.out.println("body:" + URLDecoder.decode(body11, "UTF-8"));
////            InputStream in = request.getInputStream();
////            body1 = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
////            System.out.println("sdf"+body1);
////            System.out.println("sdf1"+request.getMethod());
////            System.out.println("sdf2"+request.getQueryString());
////            System.out.println("sdf3"+request.getParameter("userId"));
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//
////        if(StringUtils.isNotBlank(body)){
////            JSONObject jsonObject = JSON.parseObject(body);
////            Object userId = jsonObject.get("userId");
////        }
//        Map<String, String> map = new HashMap<>();
//        return aliService.prePay(map);
//    }

}
