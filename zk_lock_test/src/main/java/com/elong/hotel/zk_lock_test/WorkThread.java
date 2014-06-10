package com.elong.hotel.zk_lock_test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.zookeeper.KeeperException;

public class WorkThread extends Thread {

	private int threadNumber;

	public WorkThread(int number) {
		this.threadNumber = number;
	}

	public void run() {
		try {
			Util.Output("Thread started...");

			Util.Output("Init Connection string...");
			DistributedLock joinGroup = new DistributedLock();
			joinGroup
					.connect("172.16.207.150:2181,172.16.207.150:2182,172.16.207.150:2183");
			Util.Output("Connected...");

			// zookeeper的根节点；运行本程序前，需要提前生成
			String groupName = "zkRoot";
			String memberName = "_locknode_";
			String path = "/" + groupName + "/" + memberName;

			String myName = joinGroup.join(path, new WorkerImpl());
			if (!joinGroup.checkState(path, myName)) {
				joinGroup.listenNode(path, myName);
			}
			
		} catch (Exception ex) {
			Util.Output("Exception: " + ex.getMessage() + "\r\n"
					+ ex.getStackTrace());
		}
	}
}
