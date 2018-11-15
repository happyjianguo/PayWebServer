package com.work.payweb.service.micro.ali;

import com.work.general.parameters.InputParam;
import com.work.general.parameters.OutputParam;
import com.work.payweb.hystric.AliServiceHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "service-ali", fallback = AliServiceHystric.class)
public interface AliMicroService {

    @RequestMapping(value = "/microPay")
    String microPay(String params);

    @RequestMapping(value = "/prePay")
    OutputParam prePay(InputParam inputParam);

    @RequestMapping(value = "/createMer")
    OutputParam createMer(InputParam inputParam);
}
