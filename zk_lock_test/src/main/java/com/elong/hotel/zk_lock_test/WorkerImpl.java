package com.elong.hotel.zk_lock_test;

public class WorkerImpl implements IWorker{
	public void run() throws InterruptedException{
		Logger.info("Start do someting...");
		Thread.sleep(2000);
		Logger.info("End do someting...");
	}
}
