package com.elong.hotel.zk_lock_test;

public class WorkerImpl implements IWorker{
	public void run() throws InterruptedException{
		Util.Output("Start do someting...");
		Thread.sleep(1000);
		Util.Output("End do someting...");
	}
}
