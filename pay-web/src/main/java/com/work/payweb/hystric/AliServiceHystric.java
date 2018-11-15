package com.work.payweb.hystric;

import com.work.general.parameters.InputParam;
import com.work.general.parameters.OutputParam;
import com.work.payweb.service.combination.ali.AliService;
import com.work.payweb.service.micro.ali.AliMicroService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AliServiceHystric implements AliMicroService{
    @Override
    public String microPay(String params) {
        return "ali microPay service is not available !";
    }

    @Override
    public OutputParam prePay(InputParam inputParam) {
        OutputParam outputParam = new OutputParam();
        outputParam.setRetMsg("ali prePay service is not available !");
        return outputParam;
    }

    @Override
    public OutputParam createMer(InputParam inputParam) {
        OutputParam outputParam = new OutputParam();
        outputParam.setRetMsg("ali createMer service is not available !");
        return outputParam;
    }

}
