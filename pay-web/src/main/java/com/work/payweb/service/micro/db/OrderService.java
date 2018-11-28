package com.work.payweb.service.micro.db;

import com.work.generaldb.model.TblOrder;

public interface OrderService {

    boolean insertOrder(TblOrder tblOrder);

    TblOrder queryOrder(String merId);

    boolean updateOrder(TblOrder tblOrder);

}
