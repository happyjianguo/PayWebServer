package com.work.payweb.service.micro.db.impl;

import com.work.generaldb.mapper.TblMerchantMapper;
import com.work.generaldb.model.TblMerchant;
import com.work.payweb.service.micro.db.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class MerchantServiceImpl implements MerchantService {
    @Autowired
    TblMerchantMapper tblMerchantMapper;

    @Override
    public boolean insertMerchant(TblMerchant tblMerchant) {
        int num = tblMerchantMapper.insert(tblMerchant);
        return num == 1;
    }

    @Cacheable(value = "merchant", key = "#key")
    @Override
    public TblMerchant queryMerchant(String key) {
        return tblMerchantMapper.selectByPrimaryKey(key);
    }

    @CacheEvict(value = "merchant", key = "#key")
    @Override
    public boolean deleteMerchant(String key) {
        int num = tblMerchantMapper.deleteByPrimaryKey(key);
        return num == 1;
    }

    @CachePut(value = "merchant", key = "#tblMerchant.merId")
    @Override
    public TblMerchant updateMerchant(TblMerchant tblMerchant) {
        int num = tblMerchantMapper.updateByPrimaryKeySelective(tblMerchant);
        TblMerchant tblMerchantQuery = null;
        if (num == 1) {
            tblMerchantQuery = tblMerchantMapper.selectByPrimaryKey(tblMerchant.getMerId());
        }
        return tblMerchantQuery;
    }
}
