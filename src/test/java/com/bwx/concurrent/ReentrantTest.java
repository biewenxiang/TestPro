package com.bwx.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: biewenxiang
 * @Description:
 * @Date: create in 2021/3/19 10:35
 */
public class ReentrantTest {
    private ReentrantLock reentrantLock = new ReentrantLock();
    private Condition condition = reentrantLock.newCondition();
    private boolean hasvalue = false;

    public void set() {
        reentrantLock.lock();
        while (hasvalue == true) {
            System.out.println("--");
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("-");
        hasvalue = true;
        condition.signalAll();
        reentrantLock.unlock();
    }

    public void get() {
        try {
            reentrantLock.lock();
            while (hasvalue == false) {
                System.out.println("**");
                condition.await();
            }
            System.out.println("*");
            hasvalue = false;
            condition.signalAll();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantTest service = new ReentrantTest();
        ThreadAAA[] a = new ThreadAAA[10];
        ThreadBBB[] b = new ThreadBBB[10];
        for (int i=0;i<10;i++) {
            a[i] = new ThreadAAA(service);
            b[i] = new ThreadBBB(service);
            a[i].start();
            b[i].start();
        }
    }

}

class ThreadAAA extends Thread {
    private ReentrantTest service;

    public ThreadAAA(ReentrantTest service) {
        this.service = service;
    }

    public void run() {
        while (true) {
            service.set();
        }
    }
}

class ThreadBBB extends Thread {
    private ReentrantTest service;

    public ThreadBBB(ReentrantTest service) {
        this.service = service;
    }

    public void run() {
        while (true) {
            service.get();
        }
    }
}

