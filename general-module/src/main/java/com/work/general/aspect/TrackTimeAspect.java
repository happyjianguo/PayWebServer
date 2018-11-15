package com.work.general.aspect;

import com.work.general.annotations.TrackTime;
import com.work.general.pub.PubClz;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TrackTimeAspect extends PubClz{
    @Around("@annotation(trackTime)")
    public Object around(ProceedingJoinPoint joinPoint, TrackTime trackTime) throws Throwable {
        Object result = null;
        long startTime = System.currentTimeMillis();
        result = joinPoint.proceed();
        long timeTaken = System.currentTimeMillis() - startTime;
        logger.info(" -------------> 水电费Time Taken by " + joinPoint + " with param[" + trackTime.param() + "] is " + timeTaken);
        return result;
    }
}