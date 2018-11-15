package com.work.payweb.service.combination.ali.impl;

import com.work.general.dicts.Dict;
import com.work.general.parameters.InputParam;
import com.work.general.parameters.OutputParam;
import com.work.general.pub.PubClz;
import com.work.general.util.DateUtil;
import com.work.generaldb.constants.DbConstants;
import com.work.generaldb.mapper.SequenceMapper;
import com.work.generaldb.model.TblMerchant;
import com.work.generaldb.model.TblOrder;
import com.work.payweb.service.combination.ali.AliService;
import com.work.payweb.service.micro.ali.AliMicroService;
import com.work.payweb.service.micro.db.MerchantService;
import com.work.payweb.service.micro.db.OrderService;
import com.work.payweb.service.micro.db.SeqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AliServiceImpl extends PubClz implements AliService{

    @Autowired
    OrderService orderService;
    @Autowired
    MerchantService merchantService;
    @Autowired
    SeqService seqService;
    @Autowired
    AliMicroService aliMicroService;


    @Override
    public String microPay(String params) {
        return null;
    }


    @Override
    public String prePay(Map<String, String> map) {
        TblOrder tblOrder = new TblOrder();
        tblOrder.setTxnSeqId(seqService.getSeqNextVal(DbConstants.SEQ.OrderSeq));
//        tblOrder.setTxnSeqId(DateUtil.getDateHHMMSS());
        tblOrder.setOrderAmount(map.get(Dict.orderAmount));
        tblOrder.setOutNumber(map.get(Dict.outTradeNo));
        tblOrder.setPayChannel("ALI");
        orderService.insertOrder(tblOrder);
        InputParam inputParam = new InputParam();
        inputParam.setParams(map);
        OutputParam outputParam = aliMicroService.prePay(inputParam);
        logger.info(outputParam.toString());

        return outputParam.getParam(Dict.respContent);
    }

    @Override
    public String createMer(Map<String, String> map) {
        TblMerchant tblMerchant = new TblMerchant();
        String seq = seqService.getSeqNextVal(DbConstants.SEQ.MerchantSeq);
        String merId = "900" + DateUtil.getDateYYYYMMDD() + seq;
        tblMerchant.setMerId(merId);
        tblMerchant.setName("ss"+seq);
        tblMerchant.setAliasName("ss");
        tblMerchant.setContactName("ddddd");
        tblMerchant.setContactPhone("15959999999");
        tblMerchant.setAddress("ddddddddd");
        tblMerchant.setChannel("ALI");

        map.put(Dict.merId, merId);
        InputParam inputParam = new InputParam();
        inputParam.setParams(map);
        OutputParam outputParam = aliMicroService.createMer(inputParam);
        String subMerId = outputParam.getParam(Dict.subMerId);
        tblMerchant.setSubMerId(subMerId);
        merchantService.insertMerchant(tblMerchant);
        return outputParam.getParam(Dict.respContent);
    }
}
