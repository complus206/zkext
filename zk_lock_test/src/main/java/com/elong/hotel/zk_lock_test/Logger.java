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
		StackTraceElement[] els = ex.getStackTrace();
		StringBuilder builder = new StringBuilder();
		builder.append(ex.toString() + "\r\n");
		
		for (int i = 0; i < els.length; i++) {
			builder.append(els[i].toString()); 
			builder.append("\r\n");
		}
		
		error(builder.toString());
	}

	private static String buildContent(String s) {
		String threadID = Long.toString(Thread.currentThread().getId());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.ms");
		String content = threadID + " : " + df.format(new Date()) + "\t" + s;
		return content;
	}
}
