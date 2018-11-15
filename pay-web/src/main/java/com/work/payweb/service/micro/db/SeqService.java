package com.work.payweb.service.micro.db;

import com.work.generaldb.model.TblOrder;

public interface SeqService {

    String getSeqNextVal(String seqName);
}
