package com.work.generaldb.mapper;

import com.work.generaldb.model.TblMerchant;

public interface TblMerchantMapper {
    int deleteByPrimaryKey(String merId);

    int insert(TblMerchant record);

    int insertSelective(TblMerchant record);

    TblMerchant selectByPrimaryKey(String merId);

    int updateByPrimaryKeySelective(TblMerchant record);

    int updateByPrimaryKey(TblMerchant record);
}