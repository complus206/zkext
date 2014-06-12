package com.elong.hotel.zk_lock_test;

public class WorkThread extends Thread {

	public WorkThread(int number) {
	}

	public void run() {
		try {
			Logger.info("Thread started...");

			Logger.info("Init Connection string...");
			DistributedLock joinGroup = new DistributedLock();
			joinGroup.connect();
			Logger.info("Connected...");

			// zookeeper的根节点；运行本程序前，需要提前生成
			String groupName = "zkRoot";
			String memberName = "_locknode_";
			String path = "/" + groupName + "/" + memberName;

			String myName = joinGroup.join(path, new WorkerImpl());
			if (!joinGroup.checkState(path, myName)) {
				joinGroup.listenNode(path, myName);
			}

		} catch (Exception ex) {
			Logger.error(ex);
		}
	}
}
