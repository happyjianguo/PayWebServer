package com.work.general.redisservice;

import java.util.List;
import java.util.UUID;

import com.work.general.pub.PubClz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisLockService extends PubClz{

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public String lock(String lockName){
        return lockWithTimeout(lockName, 3000L, 3000L);
    }

    /**
     * 获取锁
     * @param locaName  key名
     * @param acquireTimeout  如果在这个时间内没获取到锁，就放弃获取
     * @param timeout   设置锁的自动过期时间
     * @return
     */
    public String lockWithTimeout(String locaName, long acquireTimeout, long timeout){
        String retIdentifier = null;
        // 获取连接
        RedisConnectionFactory connectionFactory = stringRedisTemplate.getConnectionFactory();
        RedisConnection redisConnection = connectionFactory.getConnection();
        // 随机生成一个value
        String identifier = UUID.randomUUID().toString();
        // 锁名，即key值
        String lockKey = "lock:" + locaName;
        // 超时时间，上锁后超过此时间则自动释放锁
        int lockExpire = (int)(timeout / 1000);
        // 获取锁的超时时间，超过这个时间则放弃获取锁
        long end = System.currentTimeMillis() + acquireTimeout;
        while (System.currentTimeMillis() < end) {
            if (redisConnection.setNX(lockKey.getBytes(), identifier.getBytes())) {
                redisConnection.expire(lockKey.getBytes(), lockExpire);
                // 返回value值，用于释放锁时间确认
                retIdentifier = identifier;
                RedisConnectionUtils.releaseConnection(redisConnection, connectionFactory);
                return retIdentifier;
            }
            // 返回-1代表key没有设置超时时间，为key设置一个超时时间
            if (redisConnection.ttl(lockKey.getBytes()) == -1) {
                redisConnection.expire(lockKey.getBytes(), lockExpire);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                logger.warn("获取到分布式锁：线程中断！");
                Thread.currentThread().interrupt();
            }
        }
        RedisConnectionUtils.releaseConnection(redisConnection, connectionFactory);
        return retIdentifier;
    }

    /**
     * 获取锁，没获取的直接失败，只允许同一时间一个线程获取锁
     * @param locaName  锁名
     * @param timeout  设置锁的自动过期时间(毫秒ms)
     * @return
     */
    public boolean lockOnce(String locaName, long timeout){
        // 获取连接
        RedisConnectionFactory connectionFactory = null;
        RedisConnection redisConnection = null;
        try {
            connectionFactory = stringRedisTemplate.getConnectionFactory();
            redisConnection = connectionFactory.getConnection();
            // 随机生成一个value
            String identifier = UUID.randomUUID().toString();
            // 锁名，即key值
            String lockKey = "lock:" + locaName;
            // 超时时间，上锁后超过此时间则自动释放锁
            int lockExpire = (int)(timeout / 1000);
            if (redisConnection.setNX(lockKey.getBytes(), identifier.getBytes())) {
                redisConnection.expire(lockKey.getBytes(), lockExpire);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            RedisConnectionUtils.releaseConnection(redisConnection, connectionFactory);
        }
        return false;
    }

     /**
     * 释放锁
     * @param lockName 锁的key
     * @param identifier 释放锁的标识
     * @return
     */
    public boolean releaseLock(String lockName, String identifier) {
        if(identifier == null || "".equals(identifier)){
            return false;
        }
        RedisConnectionFactory connectionFactory = stringRedisTemplate.getConnectionFactory();
        RedisConnection redisConnection = connectionFactory.getConnection();
        String lockKey = "lock:" + lockName;
        boolean releaseFlag = false;
        while (true) {
            try{
                // 监视lock，准备开始事务
                redisConnection.watch(lockKey.getBytes());
                // 通过前面返回的value值判断是不是该锁，若是该锁，则删除，释放锁
                byte[] valueBytes = redisConnection.get(lockKey.getBytes());
                if(valueBytes == null){
                    redisConnection.unwatch();
                    releaseFlag = false;
                    break;
                }
                String identifierValue = new String(valueBytes);
                if (identifier.equals(identifierValue)) {
                    redisConnection.multi();
                    redisConnection.del(lockKey.getBytes());
                    List<Object> results = redisConnection.exec();
                    if (results == null) {
                        continue;
                    }
                    releaseFlag = true;
                }
                redisConnection.unwatch();
                break;
            }catch(Exception e){
                logger.warn("释放锁异常", e);
                e.printStackTrace();
            }
        }
        RedisConnectionUtils.releaseConnection(redisConnection, connectionFactory);
        return releaseFlag;
    }

}