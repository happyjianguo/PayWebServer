package com.work.payweb.service.micro.db.impl;

import com.work.generaldb.mapper.TblMerchantMapper;
import com.work.generaldb.model.TblMerchant;
import com.work.payweb.service.micro.db.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
