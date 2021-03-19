package com.bwx.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock01 {
    Lock lock = new ReentrantLock();
	Condition condition = lock.newCondition();
	void m1() {
		try {
			lock.lock(); // 相当于 synchronized （this）
			for(int i=0; i < 10; i++) {
				try {
					TimeUnit.SECONDS.sleep(1);
					if (i==5){
						m2();
//						condition.await();
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(i);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			lock.unlock();  // 必须要手动释放锁 
		}
	}
	
	void m2() {
		lock.lock();
		System.out.println("m2....");
//		condition.signal();
		lock.unlock();
	}
	
	public static void main(String[] args) {
		ReentrantLock01 lock01 = new ReentrantLock01();
		new Thread(lock01::m1).start();
		new Thread(lock01::m2).start();
	}
}

