package com.appengine.myblog.util;

/**
 * 
* <p>Title: OnlineCountStatistics.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: NO</p>
* @author ThinkPadT420i
* @date 2013年12月20日
* @version 1.0
 */
public class OnlineCountStatistics {

	/**在线人数*/
	private static long count = 0;

	public static void increaseCount(){
		count++;
	}

	public static void decreaseCount() {
		count--;
	}

	public static long getCount() {
		return count;
	}

	public static void setCount(long count) {
		OnlineCountStatistics.count = count;
	}
}
