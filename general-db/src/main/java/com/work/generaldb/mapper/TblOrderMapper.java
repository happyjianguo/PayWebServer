package com.work.generaldb.mapper;

import com.work.generaldb.model.TblOrder;

public interface TblOrderMapper {
    int deleteByPrimaryKey(String txnSeqId);

    int insert(TblOrder record);

    int insertSelective(TblOrder record);

    TblOrder selectByPrimaryKey(String txnSeqId);

    int updateByPrimaryKeySelective(TblOrder record);

    int updateByPrimaryKey(TblOrder record);
}