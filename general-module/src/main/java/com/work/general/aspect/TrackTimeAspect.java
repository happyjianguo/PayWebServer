package com.work.general.aspect;

import com.work.general.annotations.TrackTime;
import com.work.general.pub.PubClz;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class TrackTimeAspect extends PubClz{
    @Around("@annotation(trackTime)")
    public Object around(ProceedingJoinPoint joinPoint, TrackTime trackTime) throws Throwable {
        String param = trackTime.param();
        Object result = null;
        long startTime = System.currentTimeMillis();
        logger.info("进入:"+param+",请求报文:"+ Arrays.toString(joinPoint.getArgs()));
        result = joinPoint.proceed();
        logger.info("结束:"+param+",返回报文:"+result);
        long timeTaken = System.currentTimeMillis() - startTime;
        logger.info("["+param+"]请求经历时间:"+timeTaken);
        return result;
    }
}