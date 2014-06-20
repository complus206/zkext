package com.elong.hotel.zk_lock_test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class ZKClientFactory {

	private int sessionTimeout = 5000;
	private String connectionString = "";
	private static ZKClientFactory instance = new ZKClientFactory();

	private ZKClientFactory() {

	}

	public static ZKClientFactory getInstnace() {
		if (instance == null) {
			createInstance();
		}
		return instance;
	}

	public ZooKeeper createByDefault(Watcher w) throws IOException {
		ZooKeeper zk = new ZooKeeper(connectionString, sessionTimeout, w);
		return zk;
	}

	private static synchronized void createInstance() {

		if (instance == null) {
			ZKClientFactory fac = new ZKClientFactory();

			Properties prop = new Properties();
			InputStream in = Object.class.getResourceAsStream("/zk.properties");
			try {
				prop.load(in);
				fac.sessionTimeout = Integer.parseInt(prop.getProperty(
						"Session_Timeout").trim());
				fac.connectionString = prop.getProperty("Connection").trim();
				instance = fac;
			} catch (IOException ex) {
				Logger.error(ex);
				instance = null;
			} finally {
				try {
					in.close();
				} catch (IOException ex) {
					// TODO Auto-generated catch block
					Logger.error(ex);
				}
			}
		}
	}
}
