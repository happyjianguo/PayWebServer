package com.work.generaldb.service.impl;

import com.work.general.service.idworker.SnowflakeIdWorker;
import com.work.general.util.DateUtil;
import com.work.generaldb.constants.DbConstants;
import com.work.generaldb.mapper.SequenceMapper;
import com.work.generaldb.service.SeqService;
import io.shardingjdbc.core.api.HintManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeqServiceImpl implements SeqService {
    @Autowired
    SequenceMapper sequenceMapper;
    @Autowired
    SnowflakeIdWorker snowflakeIdWorker;


    private String getSeqNextVal(String seqName) {
        //强制路由主库
        HintManager.getInstance().setMasterRouteOnly();
        return sequenceMapper.getSeqNextVal(seqName);
    }

    /**
     * 商户号
     *
     * @return
     */
    @Override
    public String getMerSeq() {
        String seq = getSeqNextVal(DbConstants.SEQ.MerchantSeq);
        return "900" + DateUtil.getDateYYYYMMDD() + seq;
    }

    /**
     * 订单号
     *
     * @return
     */
    @Override
    public String getOrderSeq() {
        String seq = getSeqNextVal(DbConstants.SEQ.OrderSeq);
        return seq;
    }
}
