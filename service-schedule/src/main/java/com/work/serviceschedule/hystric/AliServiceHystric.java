package com.work.serviceschedule.hystric;

import com.work.general.parameters.InputParam;
import com.work.general.parameters.OutputParam;
import com.work.serviceschedule.service.micro.ali.AliMicroService;
import org.springframework.stereotype.Component;

@Component
public class AliServiceHystric implements AliMicroService {

    @Override
    public OutputParam orderQuery(InputParam inputParam) {
        OutputParam outputParam = new OutputParam();
        outputParam.setRetMsg("ali orderQuery service is not available !");
        return outputParam;
    }

}
