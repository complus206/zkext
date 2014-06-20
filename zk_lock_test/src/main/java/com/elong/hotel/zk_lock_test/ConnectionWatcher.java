package com.elong.hotel.zk_lock_test;

//cc ConnectionWatcher A helper class that waits for the connection to ZooKeeper to be established
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.Event.KeeperState;

// vv ConnectionWatcher
public class ConnectionWatcher implements Watcher {
	protected ZooKeeper zk;
	private CountDownLatch connectedSignal = new CountDownLatch(1);

	public void connect() throws IOException, InterruptedException {
		zk = ZKClientFactory.getInstnace().createByDefault(this);
		connectedSignal.await();
	}

	public void process(WatchedEvent event) {
		if (event.getState() == KeeperState.SyncConnected) {
			connectedSignal.countDown();
		}
	}

	public void close() throws InterruptedException {
		zk.close();
	}
	
	public ZooKeeper getZK(){
		return zk;
	}
}
// ^^ ConnectionWatcher