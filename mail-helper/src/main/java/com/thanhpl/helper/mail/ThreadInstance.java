package com.thanhpl.helper.mail;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// https://stackoverflow.com/questions/39353020/creating-a-background-thread-for-sending-email
// https://www.baeldung.com/java-executor-service-tutorial
public class ThreadInstance {
	private static ThreadPoolExecutor executor;

	private ThreadInstance() {

	}

	public static ThreadPoolExecutor getExecutor() {
		if (executor == null) {
			return new ThreadPoolExecutor(10, 10, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		} else {
			return executor;
		}
	}
}
