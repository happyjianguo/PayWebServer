package com.work.general.aspect;

import com.work.general.annotations.ExtCacheable;
import com.work.general.pub.PubClz;
import com.work.general.service.redisservice.RedisStringUtil;
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
public class ExtCacheableAspect extends PubClz{

    @Autowired
    RedisStringUtil redisStringUtil;

    @Around("@annotation(extCacheable)")
    public Object around(ProceedingJoinPoint joinPoint, ExtCacheable extCacheable) throws Throwable {

        //注解参数
        String value = extCacheable.value();
        String key = extCacheable.key();
        long expireTime = extCacheable.expireTime();
        TimeUnit timeUnit = extCacheable.timeUnit();

        if(key.startsWith("#")){//自定义
            key = key.split("#", 2)[1];
        }

        String redisKey = "";

        //方法请求参数和参数类型
        Object[] args = joinPoint.getArgs();
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        String[] parameterNames = methodSignature.getParameterNames();
        for (int i = 0; i < args.length ; i++) {
            Object arg = args[i];//参数值
            String type = args[i].getClass().getName();//参数类型
            String parameterName = parameterNames[i];//参数名称
            logger.info("参数值:"+arg+",参数类型:"+type+",参数名称:"+parameterName);

            //处理自定义注解的参数
            if (key.indexOf(parameterName) < 0) {
                continue;
            }

            //需要的类型自己添加
            if ("java.lang.String".equals(type)) {
                redisKey = value + "::" + arg;
            } else {
                String name = key.split("[.]")[1];
                redisKey = value + "::" + ReflectUtil.getter(arg,name);
            }
            logger.info(redisKey+":"+redisKey);
            break;

        }
        String declaringTypeName = methodSignature.getDeclaringTypeName();


        String str = redisStringUtil.get(redisKey);
        if (null != str) {
            Object obj = SerializaUtil.deserialize(str);
            logger.info("反序列化结果:"+obj.toString());
            return obj;
        }

        Object result = joinPoint.proceed();
        if (result != null) {
            logger.info("进行查询结果:" + result.toString());
            redisStringUtil.setEx(redisKey, SerializaUtil.serialize(result), expireTime, timeUnit);
        } else {
            redisStringUtil.setEx(redisKey, "", 300L, timeUnit);
        }
        return result;
    }
}