package com.elong.hotel.zk_lock_test;

import java.util.ArrayList;

import org.apache.zookeeper.KeeperException;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) throws InterruptedException, KeeperException {

//		int threadCount = 5;
//		ArrayList<WorkThread> list = new ArrayList<WorkThread>();
//		for (int i = 0; i < threadCount; i++) {
//			WorkThread wt = new WorkThread(i);
//			list.add(wt);
//		}
//		
//		System.out.println("Start threads...");
//		for (int i = 0; i < threadCount; i++) {
//			WorkThread wt = list.get(i);
//			wt.start();
//		}
//		
//		Thread.sleep(11000);
		
		Logger.info("Starting...");
		RealTimeConfig config = RealTimeConfig.getInstnace();
		Logger.info("Got config...");
		
		while(true){
			String s = config.getConfig("/svraddr/platform/pay");
			Logger.info(s);
			
			Thread.sleep(1000);
		}
		
		//System.out.println("Finished");
	}
}
