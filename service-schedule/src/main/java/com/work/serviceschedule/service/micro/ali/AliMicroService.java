package com.work.serviceschedule.service.micro.ali;

import com.work.general.parameters.InputParam;
import com.work.general.parameters.OutputParam;
import com.work.serviceschedule.hystric.AliServiceHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "service-ali", fallback = AliServiceHystric.class)
public interface AliMicroService {

    @RequestMapping(value = "/orderQuery")
    OutputParam orderQuery(InputParam inputParam);
}
