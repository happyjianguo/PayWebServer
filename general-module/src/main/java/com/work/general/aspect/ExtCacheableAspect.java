package com.work.general.aspect;

import com.work.general.annotations.ExtCacheable;
import com.work.general.annotations.TrackTime;
import com.work.general.pub.PubClz;
import com.work.general.util.RedisStringUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class ExtCacheableAspect extends PubClz{

    @Autowired
    RedisStringUtil redisStringUtil;

    @Around("@annotation(extCacheable)")
    public Object around(ProceedingJoinPoint joinPoint, ExtCacheable extCacheable) throws Throwable {
        String value = extCacheable.value();
        String key = extCacheable.key();
        long expireTime = extCacheable.expireTime();
        TimeUnit timeUnit = extCacheable.timeUnit();
        String out = redisStringUtil.get(value+"::"+key);

        Object result = joinPoint.proceed();
        return result;
    }
}