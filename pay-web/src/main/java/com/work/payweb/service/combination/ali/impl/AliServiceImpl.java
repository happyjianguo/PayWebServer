package com.work.payweb.service.combination.ali.impl;

import com.work.general.dicts.Dict;
import com.work.general.parameters.InputParam;
import com.work.general.parameters.OutputParam;
import com.work.general.util.DateUtil;
import com.work.generaldb.model.TblOrder;
import com.work.payweb.service.combination.ali.AliService;
import com.work.payweb.service.micro.ali.AliMicroService;
import com.work.payweb.service.micro.db.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AliServiceImpl implements AliService{

    @Autowired
    OrderService orderService;
    @Autowired
    AliMicroService aliMicroService;


    @Override
    public String microPay(String params) {
        return null;
    }


    @Override
    public String prePay(Map<String, String> map) {
        TblOrder tblOrder = new TblOrder();
//        tblOrder.setTxnSeqId(map.get(Dict.outTradeNo));
        tblOrder.setTxnSeqId(DateUtil.getDateHHMMSS());
        tblOrder.setOrderAmount(map.get(Dict.orderAmount));
        tblOrder.setOutNumber(map.get(Dict.outTradeNo));
        tblOrder.setPayChannel("ALI");
        orderService.insertOrder(tblOrder);
        InputParam inputParam = new InputParam();
        inputParam.setParams(map);
        OutputParam outputParam = aliMicroService.prePay(inputParam);
        System.out.println(outputParam.toString());

        return outputParam.getParam(Dict.respContent);
    }
}
