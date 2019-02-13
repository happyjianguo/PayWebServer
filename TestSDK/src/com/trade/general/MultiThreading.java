package com.trade.general;

import com.trade.ali.test.AliQueryMerTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程：丰收家查询三码合一
 * @author Administrator
 *
 */
public class MultiThreading {
	

	
	public static void main(String[] args) throws InterruptedException {
		
		final int threadCount = 1;
		final AtomicInteger count = new AtomicInteger(threadCount);
		final Object waitObject = new Object();
		
		ExecutorService pool = Executors.newCachedThreadPool();
		for (int i = 0; i < threadCount; i++) {
			pool.execute(new Runnable() {
				@Override
				public void run() {

					try {
						new AliQueryMerTest().SocketReq();
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
