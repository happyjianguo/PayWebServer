package com.work.serviceali.service.impl;

import com.work.general.dicts.Dict;
import com.work.general.pub.PubClz;
import com.work.serviceali.common.AliPayConstants;
import com.work.serviceali.common.AliPayRequest;
import com.work.serviceali.common.AliPayUtil;
import com.work.serviceali.service.YLAliPayService;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class YLAliPayServiceImpl extends PubClz implements YLAliPayService {

    @Value("${asdk.appid}")
    String ALI_APPID;
    @Value("${asdk.privateKey}")
    String ALI_PRIVATE_KEY;
    @Value("${asdk.publicKey}")
    String ALI_PUBLIC_KEY;
    @Value("${asdk.frontTransUrl}")
    String ALI_FRONT_TRANS_URL;


    /**
     * 向 Map 中添加公共请求参数 <br>
     *
     * @param reqData
     * @return
     * @throws Exception
     */
    public Map<String, String> fillRequestData(Map<String, Object> reqData, Map<String, String> needData) throws Exception {
        logger.info("支付宝组合请求报文reqData:" + reqData.toString() + ",needData:" + needData.toString());
        String method = needData.get(Dict.interfaceName);
        Map<String, String> reqContent = new HashMap<String, String>();
        JSONObject fromObject = JSONObject.fromObject(reqData);
        reqContent.put("biz_content", fromObject.toString()); // 请求参数的集合
        reqContent.put("app_id", ALI_APPID); // 支付宝分配给开发者的应用ID
        reqContent.put("method", method); // 接口名称
        reqContent.put("charset", AliPayConstants.CHARSET_UTF_8); // 请求使用的编码格式
        reqContent.put("sign_type", "RSA2");
        reqContent.put("timestamp", AliPayUtil.toDate(new Date())); // 发送请求的时间，格式"yyyy-MM-dd HH:mm:ss
        reqContent.put("version", AliPayConstants.VERSION); // 调用的接口版本，固定为：1.0
        reqContent.put("sign", AliPayUtil.generateSignature(reqContent, ALI_PRIVATE_KEY));
        return reqContent;
    }


    public String aliSdk(Map<String, Object> reqData, Map<String, String> needData) throws Exception {
        Map<String, String> map = this.fillRequestData(reqData, needData);
        String url = ALI_FRONT_TRANS_URL;
        String resp = this.post(map, url, AliPayConstants.CHARSET_UTF_8);
        String methodName = (String) map.get("method");
        return this.processResponse(methodName, resp);
    }

    /**
     * 处理 HTTPS API返回数据，转换成Map对象。return_code为SUCCESS时，验证签名。
     *
     * @param xmlStr API返回的相应的数据
     * @return Map类型数据
     * @throws Exception
     */
    public String processResponse(String methodName, String respStr) throws Exception {
        JSONObject respjSON = JSONObject.fromObject(respStr);
        String sign = respjSON.getString("sign");
        methodName = methodName.replace(".", "_");
        String rstStr = respjSON.getString(methodName + "_response");
        if (isResponseSignatureValid(sign, rstStr)) {
            return rstStr;
        } else {
            throw new Exception(String.format("Invalid sign value in resp: %s", rstStr));
        }
    }


    // 扫码支付结果通知
    public String processNotifyResponse(String respStr) throws Exception {
        Map<String, String> map = AliPayUtil.convertResultStringToMap(respStr);
        String sign = map.remove("sign");
        map.remove("sign_type");
        String resp = AliPayUtil.getSignContent(map);
        if (isResponseSignatureValid(sign, resp)) {
            return respStr;
        } else {
            throw new Exception(String.format("Invalid sign value in resp: %s", resp));
        }
    }

    /**
     * 判断sign是否有效，必须包含sign字段，否则返回false。
     *
     * @param reqData 向Alipay post的请求数据
     * @return 签名是否有效
     * @throws Exception
     */
    public boolean isResponseSignatureValid(String sign, String content) throws Exception {
        // 获取sign 和 content
        if (StringUtils.isBlank(sign)) {
            return false;
        }
        return AliPayUtil.rsa256CheckContent(content, sign, ALI_PUBLIC_KEY);
    }


    public String post(Map<String, String> reqData, String reqUrl, String encoding) {
        Map<String, String> rspData = new HashMap<String, String>();
        logger.info("请求银联地址:" + reqUrl);
        // 发送后台请求数据
        AliPayRequest hc = new AliPayRequest(reqUrl, 30000, 30000);// 连接超时时间，读超时时间（可自行判断，修改）
        String resultString = "";
        try {
            int status = hc.send(reqData, encoding);
            if (200 == status) {
                resultString = hc.getResult();
                if (null != resultString && !"".equals(resultString)) {
                    // 将返回结果转换为map
                    Map<String, String> tmpRspData = AliPayUtil.convertResultStringToMap(resultString);
                    rspData.putAll(tmpRspData);
                }
            } else {
                logger.info("返回http状态码[" + status + "]，请检查请求报文或者请求地址是否正确");
            }
        } catch (Exception e) {
            AliPayUtil.getLogger().error(e.getMessage(), e);
        }
        logger.info("银联返回报文:" + resultString);
        return resultString;
    }

    /**
     * 功能：http Get方法<br>
     */
    public String get(String reqUrl, String encoding) {
        logger.info("请求银联地址:" + reqUrl);
        // 发送后台请求数据
        AliPayRequest hc = new AliPayRequest(reqUrl, 30000, 30000);
        try {
            int status = hc.sendGet(encoding);
            if (200 == status) {
                String resultString = hc.getResult();
                if (null != resultString && !"".equals(resultString)) {
                    return resultString;
                }
            } else {
                logger.info("返回http状态码[" + status + "]，请检查请求报文或者请求地址是否正确");
            }
        } catch (Exception e) {
            AliPayUtil.getLogger().error(e.getMessage(), e);
        }
        return null;
    }

}
