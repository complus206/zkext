package com.elong.hotel.zk_lock_test;
import java.nio.charset.Charset;
import java.util.HashMap;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.data.Stat;

public class RealTimeConfig extends ConnectionWatcher {

	private static RealTimeConfig instance = new RealTimeConfig();;
	private HashMap<String, String> map = new HashMap<String, String>();;

	private RealTimeConfig(){
		try {
			this.connect();
		} catch (Exception ex){
			Logger.error(ex);
		}
	}

	public static RealTimeConfig getInstnace() {
		return instance;
	}

	public void process(WatchedEvent event) {

		super.process(event);

		try {
			if (event.getType() == EventType.NodeDataChanged) {
				// 响应内容变更
				String path = event.getPath();
				if (map.containsKey(path) == true) {
					byte[] buffer = zk.getData(path, this, null);
					String content = new String(buffer,
							Charset.forName("UTF-8"));
					setData(path, content);
				}
			} else if (event.getType() == EventType.NodeDeleted) {
				// 响应节点删除
				String path = event.getPath();
				if (map.containsKey(path) == true) {
					setData(path, "");
					zk.exists(path, this);
				}
			} else if (event.getType() == EventType.NodeCreated) {
				// 响应节点创建
				String path = event.getPath();
				if (map.containsKey(path) == true) {
					byte[] buffer = zk.getData(path, this, null);
					String content = new String(buffer,
							Charset.forName("UTF-8"));
					setData(path, content);
				}
			}
		} catch (Exception ex) {
			Logger.error(ex);
		}
	}

	public String getConfig(String path) throws KeeperException,
			InterruptedException {

		if (map.containsKey(path) == true) {
			return map.get(path);
		} else {
			// 缓存中找不到去ZK找
			Stat stat = zk.exists(path, this);
			if (stat != null) {
				byte[] buffer = zk.getData(path, this, stat);
				String content = new String(buffer, Charset.forName("UTF-8"));
				setData(path, content);
			} else {
				setData(path, "");
			}

			return map.get(path);
		}
	}

	private void setData(String path, String data) {
		synchronized (RealTimeConfig.class) {
			if (map.containsKey(path) == true) {
				map.remove(path);
			}
			map.put(path, data);
		}
	}
}