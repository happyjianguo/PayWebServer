package com.work.generaldb.service;

import com.work.generaldb.model.TblMerchant;

public interface MerchantService {

    boolean insertMerchant(TblMerchant tblMerchant);

    TblMerchant queryMerchant(String key);

    boolean deleteMerchant(String key);

    TblMerchant updateMerchant(TblMerchant tblMerchant);
}
