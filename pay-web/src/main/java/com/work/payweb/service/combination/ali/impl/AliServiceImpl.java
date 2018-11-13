package com.work.payweb.service.combination.ali.impl;

import com.work.general.parameters.InputParam;
import com.work.general.parameters.OutputParam;
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
        orderService.insertOrder(tblOrder);
        InputParam inputParam = new InputParam();
        inputParam.setParams(map);
        OutputParam outputParam = aliMicroService.prePay(inputParam);


        return "ssss";
    }
}
