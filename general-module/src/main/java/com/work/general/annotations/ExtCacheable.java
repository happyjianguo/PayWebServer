package com.work.general.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExtCacheable {

    String value() default "";

    String key() default "";

    long expireTime() default 1800;//30分钟

    TimeUnit timeUnit() default TimeUnit.SECONDS;//默认时间单位:秒


}