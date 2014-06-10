package com.elong.hotel.zk_lock_test;

import java.util.Date;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

public class WatcherExtend implements Watcher {

	private DistributedLock connectoin;
	private String groupPath;
	private String myName;
	private Integer waitLockTime = 3000;

	public WatcherExtend(DistributedLock conn, String path, String name) {
		this.connectoin = conn;
		this.groupPath = path;
		this.myName = name;
	}

	public void process(WatchedEvent event) {
		Util.Output("event trigger " + event.getType() + "！");

		try {
			while (true) {
				if (this.connectoin.checkState(this.groupPath, this.myName)) {
					Util.Output("Check state pass!");
					break;
					// this.worker.run();
				} else {
					Util.Output("Check state failed, continue waite!");
					Thread.sleep(waitLockTime);
				}
			}
		} catch (KeeperException e) {
			Util.Output(e.getMessage());
			e.printStackTrace();
		} catch (InterruptedException e) {
			Util.Output(e.getMessage());
			e.printStackTrace();
		}
	}
}
