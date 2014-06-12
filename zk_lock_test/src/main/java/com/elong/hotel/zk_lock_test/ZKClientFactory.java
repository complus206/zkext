package com.elong.hotel.zk_lock_test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class ZKClientFactory {

	private static int sessionTimeout = 5000;
	private static String connectionString = "";
	private static ZKClientFactory instance = null;

	private ZKClientFactory() throws IOException {
		Properties prop = new Properties();
		InputStream in = Object.class.getResourceAsStream("/zk.properties");
		try {
			prop.load(in);
			sessionTimeout = new Integer(prop.getProperty("Session_Timeout")
					.trim());
			connectionString = prop.getProperty("Connection").trim();
		} catch (IOException ex) {
			Logger.error(ex);
		}
		in.close();
	}

	public static ZKClientFactory getInstnace() {
		if (instance == null) {
			synchronized (ZKClientFactory.class) {
				if (instance == null) {
					try {
						instance = new ZKClientFactory();
					} catch (Exception ex) {
						Logger.error(ex);
					}
				}
				return instance;
			}
		} else {
			return instance;
		}
	}

	public ZooKeeper CreateByDefault(Watcher w) throws IOException {
		ZooKeeper zk = new ZooKeeper(connectionString, sessionTimeout, w);
		return zk;
	}
}
