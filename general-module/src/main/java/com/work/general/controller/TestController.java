package com.work.general.controller;

import com.work.general.annotations.TrackTime;
import com.work.general.pub.PubClz;
import com.work.general.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController extends PubClz{

    @Autowired
    RedisUtil redisUtil;

    @TrackTime(param = "test")
    @RequestMapping(value="/test")
    public String test() {
        System.out.println("进入");
        boolean b = redisUtil.set("sdf","asdf");
        logger.info("b:"+b);
        Object obj = redisUtil.get("sdf");
        logger.info("obj:"+obj);
        redisUtil.del("sdf");
        Object obj1 = redisUtil.get("sdf");
        logger.info("obj1:"+obj1);
        logger.info("结束test");
        return "";
    }
}
