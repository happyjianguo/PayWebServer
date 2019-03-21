package com.work.general.service.redisservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
public class RedisLock {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 加锁标志
     */
    public static final String LOCKED = "TRUE";
    /**
     * 毫秒与毫微秒的换算单位 1毫秒 = 1000000毫微秒
     */
    public static final long MILLI_NANO_CONVERSION = 1000 * 1000L;
    /**
     * 默认超时时间（毫秒）
     */
    public static final long DEFAULT_TIME_OUT = 1000;
    public static final Random RANDOM = new Random();
    /**
     * 锁的超时时间（秒），过期删除
     */
    public static final int EXPIRE = 3 * 60;
 
    private boolean locked = false;
 
    /**
     * 加锁
     * 应该以：
     * lock();
     * try {
     *      doSomething();
     * } finally {
     *      unlock()；
     * }
     * 的方式调用
     * @param timeout
     * @return
     */
    public boolean lock(String key,long timeout){
        long nano = System.nanoTime();
        timeout *= MILLI_NANO_CONVERSION;
        if (addLock(key,timeout, nano, EXPIRE)){
            this.locked = true;
            return locked;
        }
        return locked;
    }

    /**
     * 加锁
     * 应该以：
     * lock();
     * try {
     *      doSomething();
     * } finally {
     *      unlock()；
     * }
     * 的方式调用
     * @param timeout
     * @param expire
     * @return
     */
    public boolean lock(String key,long timeout,int expire){
        long nano = System.nanoTime();
        timeout *= MILLI_NANO_CONVERSION;
        if (addLock(key,timeout, nano, expire)) {
            this.locked = true;
            return locked;

        }
        return locked;
    }

    /**
     * 加锁
     * 应该以：
     * lock();
     * try {
     *      doSomething();
     * } finally {
     *      unlock()；
     * }
     * 的方式调用
     * @return
     */
    //默认加锁
    public boolean lock(String key){
        return lock(key,DEFAULT_TIME_OUT);
    }
 
    /**
     * @param timeout 加锁超时时间
     * @param nano
     * @param expire 锁的超时时间(秒),过期删除
     * @return 成功或失败标志
     */
    private boolean addLock(String key, long timeout, long nano, int expire) {
        try{
            while ((System.nanoTime()-nano)<timeout){
                if(this.redisTemplate.opsForValue().setIfAbsent(key,LOCKED)){
                    this.redisTemplate.expire(key,expire,TimeUnit.SECONDS);
                    return true;
                }
                // 短暂休眠，避免出现活锁
                Thread.sleep(3, RANDOM.nextInt(500));
            }
        }catch (Exception e){
            throw new RuntimeException("Locking error",e);
        }
        return false;
    }
    /**
     *解锁
     * 无论是否加锁成功，都需要调用unlock
     * 应该以：
     * lock();
     * try {
     *      doSomething();
     * } finally {
     *      unlock()；
     * }
     * 的方式调用
     */
    public void unlock(String key){
        try {
            this.redisTemplate.delete(key);
        }catch (Exception e){
            throw new RuntimeException("Unlock error",e);
        }
    }
}