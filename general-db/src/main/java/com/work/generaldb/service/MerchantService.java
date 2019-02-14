package com.work.generaldb.service;

import com.work.generaldb.model.TblMerchant;

public interface MerchantService {

    boolean insertMerchant(TblMerchant tblMerchant);

    TblMerchant queryMerchant(String key);

    TblMerchant queryMerchant(TblMerchant tblMerchant);

    boolean deleteMerchant(String key);

    TblMerchant updateMerchant(TblMerchant tblMerchant);
}
