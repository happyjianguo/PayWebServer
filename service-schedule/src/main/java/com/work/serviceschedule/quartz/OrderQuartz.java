package com.work.serviceschedule.quartz;

import com.work.general.constants.StringConstans;
import com.work.general.dicts.Dict;
import com.work.generaldb.mapper.TblOrderMapper;
import com.work.generaldb.model.TblOrder;
import com.work.serviceschedule.service.micro.ali.AliMicroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderQuartz {

    @Autowired
    TblOrderMapper tblOrderMapper;
    @Autowired
    AliMicroService aliMicroService;

    @Scheduled(fixedRate = 10000)
    public void testFixRate() {
        System.out.println("我每隔10秒冒泡一次");
        Map map = new HashMap();
        map.put(Dict.status, StringConstans.ORDER_STATUS.STATUS_01);
        List<TblOrder> list = tblOrderMapper.selectInitOrder(map);

        for (TblOrder tblOrder : list) {
//            String txnSeqId = tblOrder.getTxnSeqId();
//            TblOrder tblOrder = orderService.queryOrder(txnSeqId);
//            if (null == tblOrder) {
//                return "订单不存在";
//            }
//
//            String status = tblOrder.getStatus();
//            if (StringConstans.ORDER_STATUS.STATUS_02.equals(status)
//                    || StringConstans.ORDER_STATUS.STATUS_03.equals(status)) {
//                return TransUtil.objectToMap(tblOrder).toString();
//            }
//
//            //去支付宝查询订单信息
//            InputParam inputParam = new InputParam();
//            inputParam.putParam(Dict.txnSeqId, txnSeqId);
//            OutputParam outputParam = aliMicroService.orderQuery(inputParam);
//            String respContent = outputParam.getParam(Dict.respContent);
//
//            JSONObject jsonObject = JSONObject.fromObject(respContent);
//            String code = StringUtil.toString(jsonObject.get(Dict.code));
//            String msg = StringUtil.toString(jsonObject.get(Dict.msg));
//            String tradeNo = StringUtil.toString(jsonObject.get(Dict.trade_no));
//            String tradeStatus = StringUtil.toString(jsonObject.get(Dict.trade_status));
//
//            //更新订单
//            TblOrder tblOrderUpd = new TblOrder();
//            tblOrderUpd.setTxnSeqId(txnSeqId);
//            if("10000".equals(code) && msg.equals("Success")){
////            tblOrderUpd.setOthChannelNumber(tradeNo);
//                tblOrderUpd.setStatus(StringConstans.ORDER_STATUS.STATUS_02);
//                tblOrderUpd.setMsg(tradeStatus);
//                boolean result = orderService.updateOrder(tblOrderUpd);
//                logger.info("更新订单结果:"+result);
//            } else {
//                tblOrderUpd.setStatus(StringConstans.ORDER_STATUS.STATUS_03);
//                tblOrderUpd.setMsg("交易失败");
//            }
        }
        System.out.println(list);
    }

    /**
     * 每五分钟查询订单表中初始状态的订单
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void queryInitOrder() {

    }


}
