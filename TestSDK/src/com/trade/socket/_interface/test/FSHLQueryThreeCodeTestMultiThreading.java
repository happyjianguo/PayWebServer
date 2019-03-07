package com.trade.socket._interface.test;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import com.common.constants.SDKConstant;
import com.common.dicts.Dict;
import com.trade.socket._interface.trading.FSHLQueryThreeCode;
import com.util.CommonUtil;
import com.util.HttpUtil;
import com.util.SocketUtil;
import com.util.TransUtil;

/**
 * 多线程：丰收家查询三码合一
 * @author Administrator
 *
 */
public class FSHLQueryThreeCodeTestMultiThreading {
	

	
	public static void main(String[] args) throws InterruptedException {
		
		final int threadCount = 5;
		final AtomicInteger count = new AtomicInteger(threadCount);
		final Object waitObject = new Object();
		
		ExecutorService pool = Executors.newCachedThreadPool();
		for (int i = 0; i < threadCount; i++) {
			pool.execute(new Runnable() {
				@Override
				public void run() {

					String url = "http://127.0.0.1:9999/pay-web/redisLock";
					try {
						HttpUtil.httpRequest(url,"");
					} catch (Exception e) {
						e.printStackTrace();
					}


					synchronized (waitObject) {
						int cnt = count.decrementAndGet();
						if(cnt == 0){
							waitObject.notifyAll();
						}
							
					}
					
				}
				
			});
			
		}
		
		synchronized (waitObject) {
			while(count.get() != 0){
				waitObject.wait();
			}
		}
		Thread.sleep(1000);
		System.exit(0);
		
	}
	
}
