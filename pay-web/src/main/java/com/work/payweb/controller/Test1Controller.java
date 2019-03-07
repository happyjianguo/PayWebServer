package com.work.payweb.controller;

import com.work.general.annotations.TrackTime;
import com.work.general.pub.PubClz;
import com.work.general.redisservice.RedisLockService;
import com.work.general.redisservice.RedisStringUtil;
import com.work.general.util.EnvironmentUtil;
import com.work.generaldb.model.TblOrder;
import com.work.generaldb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

@RestController
public class Test1Controller extends PubClz {
    @Autowired
    RedisStringUtil redisStringUtil;
    @Autowired
    RedisLockService redisLockService;
    @Autowired
    OrderService orderService;
    @Autowired
    EnvironmentUtil environmentUtil;

    /**
     * 分布式锁
     */
    @TrackTime(param = "分布式锁")
    @RequestMapping(value="/redisLock")
    public String redisLock(){

        String key = "abc1";
        String str = "";
        String msg = "ip:" + environmentUtil.getIP() + " 端口:" + environmentUtil.getPort();
        try {
//            str = redisLockService.lock(key);
            str = redisLockService.lockWithTimeout(key, 3000L, 30000L);
            logger.info("str:"+str);
            if (null != str) {
                logger.info(msg+"服务获取了锁");
                TblOrder tblOrder = orderService.queryOrder("100000042");
                String time = tblOrder.getOutTime();
                int i = Integer.parseInt(time);
                logger.info("查询time："+time);
                TblOrder tblOrderUpd = new TblOrder();
                tblOrderUpd.setTxnSeqId("100000042");
                tblOrderUpd.setOutTime((i-1)+"");
                boolean b = orderService.updateOrder(tblOrderUpd);
                logger.info("更新结果:"+b);
            } else {
                logger.info(msg+"服务获取锁超时");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            boolean b = redisLockService.releaseLock(key, str);
            logger.info("释放锁:"+b);
        }
        return "redisLock";
    }


    @RequestMapping(value="/setRedis")
    public String setRedis() throws UnsupportedEncodingException {
        logger.info("setRedis");
        redisStringUtil.setEx("key123","value123",60L, TimeUnit.SECONDS);

//        redisStringUtil.sAdd("ss", "cc");
//        redisStringUtil.sAdd("ss", "dd");
//        redisStringUtil.sAdd("ss", "ddee");

//        Map<String,String> map = new HashMap();
//        map.put("xxxab:::abc1", "11");
//        map.put("xxxab:::abc2", "12");
//        map.put("xxxab:::abc3", "13");
//        map.put("xxxab:::abc4", "14");
//        redisStringUtil.multiSet(map);

//        TblMerchant TblMerchant1 = new TblMerchant();
//        TblMerchant1.setAddress("a");
//        TblMerchant TblMerchant2 = new TblMerchant();
//        TblMerchant2.setAddress("b");
//        Map<String,Object> map = new HashMap();
//        map.put("xxxab:::abc1", TblMerchant1);
//        map.put("xxxab:::abc2", TblMerchant2);
//        redisUtil.set("xxxab:::abc1", TblMerchant1);
//        redisUtil.set("xxxab:::abc2", TblMerchant2);

//        TblMerchant TblMerchant1 = new TblMerchant();
//        TblMerchant1.setAddress("a");
//        TblMerchant TblMerchant2 = new TblMerchant();
//        TblMerchant2.setAddress("b");
//        Map<String,String> map = new HashMap();
//        map.put("xxxab:::abc1", SerializaUtil.serialize(TblMerchant1));
//        map.put("xxxab:::abc2", SerializaUtil.serialize(TblMerchant2));
//        redisStringUtil.multiSet(map);
//        String str = redisStringUtil.get("xxxab:::abc1");
//        TblMerchant t = (TblMerchant) SerializaUtil.deserialize(str);
//        logger.info("ssssssss:"+t.toString());


//        TblMerchant TblMerchant1 = new TblMerchant();
//        TblMerchant1.setAddress("a");
//        Map<String,String> map = new HashMap();
//        map.put("xxxab:::abc1", SerializaUtil.serialize(TblMerchant1));
//        logger.info(SerializaUtil.serialize(TblMerchant1));
//        redisStringUtil.multiSet(map);
//        String str = redisStringUtil.get("xxxab:::abc1");
//        logger.info(str);
//        logger.info(String.valueOf(str.equals(SerializaUtil.serialize(TblMerchant1))));
//        TblMerchant t = (TblMerchant) SerializaUtil.deserialize(str);
//        logger.info("ssssssss:"+t.toString());

//        redisStringUtil.expire("xxxab:::abc1",60L, TimeUnit.SECONDS);


        return "success";
    }

}
