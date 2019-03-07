package com.work.serviceschedule.quartz;

import com.work.general.constants.StringConstans;
import com.work.general.dicts.Dict;
import com.work.general.parameters.InputParam;
import com.work.general.parameters.OutputParam;
import com.work.general.pub.PubClz;
import com.work.general.redisservice.RedisLockService;
import com.work.general.util.EnvironmentUtil;
import com.work.general.util.StringUtil;
import com.work.generaldb.mapper.TblOrderMapper;
import com.work.generaldb.model.TblOrder;
import com.work.generaldb.service.OrderService;
import com.work.serviceschedule.service.micro.ali.AliMicroService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderQuartz extends PubClz{

    @Autowired
    TblOrderMapper tblOrderMapper;
    @Autowired
    AliMicroService aliMicroService;
    @Autowired
    OrderService orderService;


    /**
     * 每五分钟查询订单表中初始状态的订单
     */
//    @Scheduled(cron = "0 0/1 * * * ?")
    public String queryInitOrder() {
        Map map = new HashMap();
        map.put(Dict.status, StringConstans.ORDER_STATUS.STATUS_01);
        List<TblOrder> list = tblOrderMapper.selectInitOrder(map);

        for (TblOrder tblOrder : list) {
            String txnSeqId = tblOrder.getTxnSeqId();

            //去支付宝查询订单信息
            InputParam inputParam = new InputParam();
            inputParam.putParam(Dict.txnSeqId, txnSeqId);
            OutputParam outputParam = aliMicroService.orderQuery(inputParam);
            logger.info("返回:"+outputParam.toString());
            String respContent = outputParam.getParam(Dict.respContent);

            JSONObject jsonObject = JSONObject.fromObject(respContent);
            String code = StringUtil.toString(jsonObject.get(Dict.code));
            String msg = StringUtil.toString(jsonObject.get(Dict.msg));
            String subMsg = StringUtil.toString(jsonObject.get(Dict.sub_msg));
            String tradeNo = StringUtil.toString(jsonObject.get(Dict.trade_no));
            String tradeStatus = StringUtil.toString(jsonObject.get(Dict.trade_status));

            //更新订单
            TblOrder tblOrderUpd = new TblOrder();
            tblOrderUpd.setTxnSeqId(txnSeqId);
            if ("10000".equals(code) && msg.equals("Success")) {
//              tblOrderUpd.setOthChannelNumber(tradeNo);
                tblOrderUpd.setStatus(StringConstans.ORDER_STATUS.STATUS_02);
                tblOrderUpd.setMsg(tradeStatus);
            } else {
                tblOrderUpd.setStatus(StringConstans.ORDER_STATUS.STATUS_03);
                tblOrderUpd.setMsg(subMsg);
            }
            boolean result = orderService.updateOrder(tblOrderUpd);
            logger.info("更新订单结果:" + result);
        }
        System.out.println(list);
        return "结束";
    }


}
