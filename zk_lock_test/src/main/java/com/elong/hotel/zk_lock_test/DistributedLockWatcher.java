package com.elong.hotel.zk_lock_test;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

public class DistributedLockWatcher implements Watcher {

	private DistributedLock connectoin;
	private String groupPath;
	private String myName;
	private Integer waitLockTime = 3000;

	public DistributedLockWatcher(DistributedLock conn, String path, String name) {
		this.connectoin = conn;
		this.groupPath = path;
		this.myName = name;
	}

	public void process(WatchedEvent event) {
		Logger.info("event trigger " + event.getType() + "！");

		try {
			while (true) {
				if (this.connectoin.checkState(this.groupPath, this.myName)) {
					Logger.info("Check state pass!");
					break;
					// this.worker.run();
				} else {
					Logger.info("Check state failed, continue waite!");
					Thread.sleep(waitLockTime);
				}
			}
		} catch (KeeperException e) {
			Logger.info(e.getMessage());
			e.printStackTrace();
		} catch (InterruptedException e) {
			Logger.info(e.getMessage());
			e.printStackTrace();
		}
	}
}
