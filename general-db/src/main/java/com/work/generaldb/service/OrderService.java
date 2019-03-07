package com.work.generaldb.service;

import com.work.generaldb.model.TblOrder;

public interface OrderService {

    boolean insertOrder(TblOrder tblOrder);

    TblOrder queryOrder(String txnSeqId);

    boolean updateOrder(TblOrder tblOrder);

}
