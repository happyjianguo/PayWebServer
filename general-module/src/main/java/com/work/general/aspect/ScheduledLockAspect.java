package com.work.general.aspect;

import com.work.general.annotations.ExtCacheable;
import com.work.general.annotations.ScheduledLock;
import com.work.general.pub.PubClz;
import com.work.general.redisservice.RedisLockService;
import com.work.general.redisservice.RedisStringUtil;
import com.work.general.util.EnvironmentUtil;
import com.work.general.util.ReflectUtil;
import com.work.general.util.SerializaUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class ScheduledLockAspect extends PubClz{

    @Autowired
    RedisLockService redisLockService;
    @Autowired
    EnvironmentUtil environmentUtil;

    @Around("@annotation(scheduledLock)")
    public Object around(ProceedingJoinPoint joinPoint, ScheduledLock scheduledLock) throws Throwable {

        //注解参数
        String lockName = scheduledLock.lockName();
        String desc = scheduledLock.desc();
        long expireTime = scheduledLock.expireTime();

        String msg = "ip:" + environmentUtil.getIP() + " 端口:" + environmentUtil.getPort();
        boolean isGetKey = redisLockService.lockOnce(lockName, expireTime);
        logger.info(msg + "获取锁结果：" + isGetKey);
        if (!isGetKey) {
            return null;
        }
        Object result = joinPoint.proceed();
        return result;
    }
}