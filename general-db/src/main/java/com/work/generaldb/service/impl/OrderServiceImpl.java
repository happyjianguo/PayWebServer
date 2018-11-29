package com.work.generaldb.service.impl;

import com.work.generaldb.mapper.TblOrderMapper;
import com.work.generaldb.model.TblOrder;
import com.work.generaldb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    TblOrderMapper tblOrderMapper;

    @Override
    public boolean insertOrder(TblOrder tblOrder) {
        int num = tblOrderMapper.insert(tblOrder);
        return num == 1 ;
    }

    @Override
    public TblOrder queryOrder(String merId) {
        return tblOrderMapper.selectByPrimaryKey(merId);
    }

    @Override
    public boolean updateOrder(TblOrder tblOrder) {
        int num = tblOrderMapper.updateByPrimaryKeySelective(tblOrder);
        return num == 1 ;
    }
}
