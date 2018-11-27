package com.work.payweb.service.micro.db;

import com.work.generaldb.model.TblMerchant;

public interface MerchantService {

    boolean insertMerchant(TblMerchant tblMerchant);

    TblMerchant queryMerchant(String key);

    boolean deleteMerchant(String key);

    TblMerchant updateMerchant(TblMerchant tblMerchant);
}
