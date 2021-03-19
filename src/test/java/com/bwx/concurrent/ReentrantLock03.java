package com.bwx.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁与否
 */
public class ReentrantLock03 extends Thread {
    private static ReentrantLock lock = new ReentrantLock(true); // 参数true表示为公平锁。请对比输出结果
	
	@Override
	public void run() {

		try {
			for (int i = 0; i < 10; i++) {
				lock.lock();
				System.out.println(Thread.currentThread().getName() + "获得锁");
				lock.unlock();
			}
		} finally {
			
		}
	}
	
	public static void main(String[] args) {
		ReentrantLock03 lock03 = new ReentrantLock03();
		new Thread(lock03).start();
		new Thread(lock03).start();
	}
}
