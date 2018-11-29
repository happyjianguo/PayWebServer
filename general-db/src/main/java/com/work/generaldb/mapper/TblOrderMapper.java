package com.work.generaldb.mapper;

import com.work.generaldb.model.TblOrder;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface TblOrderMapper {
    int deleteByPrimaryKey(String txnSeqId);

    int insert(TblOrder record);

    int insertSelective(TblOrder record);

    TblOrder selectByPrimaryKey(String txnSeqId);

    int updateByPrimaryKeySelective(TblOrder record);

    int updateByPrimaryKey(TblOrder record);

//    @Select("SELECT * from tbl_order where 1=1 and status = #{status} ")
    List<TblOrder> selectInitOrder(Map map);
}