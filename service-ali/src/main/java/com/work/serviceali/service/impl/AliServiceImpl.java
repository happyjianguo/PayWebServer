package com.work.serviceali.service.impl;

import com.work.general.dicts.Dict;
import com.work.general.parameters.InputParam;
import com.work.general.parameters.OutputParam;
import com.work.general.pub.PubClz;
import com.work.general.util.StringUtil;
import com.work.serviceali.service.AliService;
import com.work.serviceali.service.YLAliPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AliServiceImpl extends PubClz implements AliService {

    @Autowired
    YLAliPayService ylAliPayService;

    @Value("${asdk.tradePay}")
    String asdk_tradePay;
    @Value("${asdk.tradePrecreate}")
    String asdk_tradePrecreate;

    @Override
    public OutputParam microPay(InputParam input) {
        OutputParam outputParam = new OutputParam();
//        try {
//            String outTradeNo = input.getParamsStr(Dict.outTradeNo);
//            String authCode = input.getParamsStr(Dict.authCode);
//            String orderAmount = input.getParamsStr(Dict.orderAmount);
//            String subject = input.getParamsStr(Dict.subject);
//            String body = input.getParamsStr(Dict.body);
//            String subMerId = input.getParamsStr(Dict.subMerId);
//
//            HashMap<String, Object> data = new HashMap<String, Object>();
//            // 商户订单号,64个字符以内、只能包含字母、数字、下划线；需保证在商户端不重复
//            data.put("out_trade_no", outTradeNo);
//            data.put("scene", "bar_code"); // 支付场景 条码支付，取值：bar_code  声波支付，取值：wave_code
//            data.put("auth_code", authCode); // 支付授权码，25~30开头的长度为16~24位的数字，实际字符串长度以开发者获取的付款码长度为准
//            data.put("subject", subject); // 订单标题
//            Map<String, Object> SubMerchant = new HashMap<String, Object>();
//            SubMerchant.put("merchant_id", subMerId);
//            SubMerchant.put("merchant_type", "alipay");
//            data.put("sub_merchant", SubMerchant);
//            data.put("total_amount", orderAmount);
//            data.put("trans_currency", "CNY");
//            data.put("settle_currency", "CNY");
//            if (!StringUtil.isEmpty(body)) {
//                data.put("body", body); // 订单描述
//            }
//            data.put("timeout_express", "5m");
//
//            Map<String, String> needData = new HashMap<String, String>();
//            needData.put(Dict.interfaceName, asdk_tradePay);
//
//            String returnData = ylAliPayService.aliSdk(data, needData); //  条码支付
//
//        } catch (Exception e) {

//        } finally {
//
//        }

        return outputParam;

    }

    @Override
    public OutputParam prePay(InputParam inputParam) {
        OutputParam outputParam = new OutputParam();
        try {
            String outTradeNo = inputParam.getParam(Dict.outTradeNo);
            String orderAmount = inputParam.getParam(Dict.orderAmount);
            String subject = inputParam.getParam(Dict.subject);
            String subMchId = inputParam.getParam(Dict.subMchId);
            String body = inputParam.getParam(Dict.body);

            HashMap<String, Object> data = new HashMap<String, Object>();
            data.put("out_trade_no", outTradeNo);
            data.put("total_amount", orderAmount);
            data.put("subject", subject); // 订单标题
            Map<String, Object> SubMerchant = new HashMap<String, Object>();
            SubMerchant.put("merchant_id", subMchId);
            SubMerchant.put("merchant_type", "alipay");
            data.put("sub_merchant", SubMerchant);
            data.put("body", body); // 对交易或商品的描述
            data.put("timeout_express", "5m");

            Map<String, String> needData = new HashMap<String, String>();
            needData.put(Dict.interfaceName, asdk_tradePrecreate);

            String returnMsg = ylAliPayService.aliSdk(data, needData); // 扫码支付
            outputParam.putParam(Dict.respContent,returnMsg);
            outputParam.setRetMsg("成功");
            outputParam.setRetCode("success");
        } catch (Exception e) {

        } finally {

        }
        return outputParam;

    }
}
