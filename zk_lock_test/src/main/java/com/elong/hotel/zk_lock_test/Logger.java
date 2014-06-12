package com.elong.hotel.zk_lock_test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
	public static void info(String s) {
		String content = buildContent(s);
		System.out.println(content);
	}

	public static void error(String s) {
		String content = buildContent(s);
		System.err.println(content);
	}

	public static void error(Exception ex) {
		String content = ex.toString() + "\r\n";
		StackTraceElement[] els = ex.getStackTrace();
		for (int i = 0; i < els.length; i++) {
			content += els[i].toString() + "\r\n";
		}
		
		error(content);
	}

	private static String buildContent(String s) {
		String threadID = Long.toString(Thread.currentThread().getId());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.ms");
		String content = threadID + " : " + df.format(new Date()) + "\t" + s;
		return content;
	}
}
