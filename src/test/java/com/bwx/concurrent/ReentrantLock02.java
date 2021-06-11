package com.bwx.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock02 {
    Lock lock = new ReentrantLock();
	
	
	void m1() {
		lock.lock();
		try {
			for (int i = 0; i < 10; i++) {
				System.out.println(i);
				TimeUnit.SECONDS.sleep(1);
			}
		} catch (Exception e) {
			
		} finally {
			lock.unlock();
		}
	}
	/**
	 * 使用tryLock 进行尝试锁定，不管锁定与否，方法都将继续执行
	 * 可以根据tryLock 的返回值来判定是否锁定
	 * 也可以指定tryLock的时间。由于tryLock（time） 抛出异常。 所以要注意unlock的处理。 必须放到finally中
	 */
	void m2() {
		while (true) {
			boolean tryLock = false;
			try {
				TimeUnit.SECONDS.sleep(1);
				// 尝试去锁定
				tryLock = lock.tryLock();
				// 判断是否锁定成功
				if (tryLock) {
					// 如果成功  释放锁
					System.out.println("是否锁定" + tryLock);
					break;
				}else {
					// 不成功！
					System.out.println("是否锁定" + tryLock);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				if (tryLock) {
					lock.unlock();
				}
			}
		}
	}
	
	void m3() {
		boolean locked = false;
		try {
			// 尝试锁定五秒钟。如果五秒钟拿不到锁
			locked = lock.tryLock(5,TimeUnit.SECONDS);
			System.out.println("m3..." + locked);
			// 判断是否锁定成功
			if (locked) {
				// 如果成功  释放锁
				System.out.println("是否锁定" + locked);
			
			}else {
				// 不成功！
				System.out.println("是否锁定" + locked);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally{
			if (locked) {
				lock.unlock();
			}
		}
	}
	
	
	public static void main(String[] args) {
		ReentrantLock02 lock02 = new ReentrantLock02();
		new Thread(lock02::m1).start();
		new Thread(lock02::m2).start();
		new Thread(lock02::m3).start();

	}
}
