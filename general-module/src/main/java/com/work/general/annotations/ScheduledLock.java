package com.work.general.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ScheduledLock {

    String lockName() default "";//锁名

    String desc() default "";//描述

    long expireTime() default 30000;//默认30秒，锁自动释放


}