package com.elong.hotel.zk_lock_test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	public static void Output(String s) {
		String threadID = Long.toString(Thread.currentThread().getId());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.ms");
		System.out.println(threadID + " : " + df.format(new Date()) + "\t"+ s);
	}
}
