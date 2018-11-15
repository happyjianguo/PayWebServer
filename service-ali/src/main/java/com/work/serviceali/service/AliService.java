package com.work.serviceali.service;

import com.work.general.parameters.InputParam;
import com.work.general.parameters.OutputParam;

public interface AliService {

    OutputParam microPay(InputParam inputParam);

    OutputParam prePay(InputParam inputParam);

    OutputParam createMer(InputParam inputParam);
}
