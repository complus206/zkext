package com.elong.hotel.zk_lock_test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.zookeeper.KeeperException;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) throws InterruptedException {

		int threadCount = 5;
		ArrayList<WorkThread> list = new ArrayList<WorkThread>();
		for (int i = 0; i < threadCount; i++) {
			WorkThread wt = new WorkThread(i);
			list.add(wt);
		}
		
		System.out.println("Start threads...");
		for (int i = 0; i < threadCount; i++) {
			WorkThread wt = list.get(i);
			wt.start();
		}
		
		Thread.sleep(10000);
		System.out.println("Finished");
	}
}
