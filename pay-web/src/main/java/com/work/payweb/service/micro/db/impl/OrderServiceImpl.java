package com.work.payweb.service.micro.db.impl;

import com.work.generaldb.mapper.TblOrderMapper;
import com.work.generaldb.model.TblOrder;
import com.work.payweb.service.micro.db.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    TblOrderMapper tblOrderMapper;

    @Override
    public boolean insertOrder(TblOrder tblOrder) {
        int num = tblOrderMapper.insert(tblOrder);
        return num == 1 ;
    }
}
