package com.work.generaldb.mapper;

import com.work.generaldb.model.TblOrder;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TblOrderMapper {
    int deleteByPrimaryKey(String txnSeqId);

    int insert(TblOrder record);

    int insertSelective(TblOrder record);

    TblOrder selectByPrimaryKey(String txnSeqId);

    int updateByPrimaryKeySelective(TblOrder record);

    int updateByPrimaryKey(TblOrder record);
}