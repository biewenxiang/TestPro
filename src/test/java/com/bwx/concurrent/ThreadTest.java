package com.bwx.concurrent;

import org.junit.Test;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * volatile  test java
 */
public class ThreadTest {


    private static volatile long _longVal = 0;
    private static long _longVal3 = 0;

    private static AtomicLong _longVal2 = new AtomicLong(0);
    private static volatile int account = 0;

    public int getAccount() {
        return account;
    }

    //这里不再需要synchronized
//    public int getAccount(){}

    private static ThreadTest test = new ThreadTest();

    /**
     * 读写锁
     * @param money
     */
    public synchronized void save(int money) {
        account = getAccount() + money;
    }

    private static class LoopVolatile implements Runnable {
        public void run() {
            long val = 0;
            while (val < 10000000L) {
//                _longVal2 = _longVal+1;
                _longVal2.addAndGet(1);
                _longVal++;
                test.add();
                test.save(1);
                val++;
            }
        }
    }


    private static class LoopVolatile2 implements Runnable {
        public void run() {
            long val = 0;
            while (val < 10000000L) {
//                _longVal2 = _longVal++;
                _longVal2.addAndGet(1);
//                ThreadTest test = new ThreadTest();
                test.save(1);

                test.add();
                _longVal++;
                val++;
            }
        }
    }

    private void add() {
        synchronized (ThreadTest.class) {
            _longVal3++;
        }
    }

    private void testVolatile() {
        Thread t1 = new Thread(new LoopVolatile());
        t1.start();

        Thread t2 = new Thread(new LoopVolatile2());
        t2.start();
//        t1.
        while (t1.isAlive() || t2.isAlive()) {
        }

        System.out.println("final val is: " + _longVal + " " + _longVal2 + " " + _longVal3 + " " + account);
    }

    public static void main(String[] args) {
        ThreadTest tt = new ThreadTest();
        tt.testVolatile();

//        final MyThreadLocal t = new MyThreadLocal();
//        for (int i = 0; i < 5; i++) {
//            Thread thread = new Thread() {
//                public void run() {
//                    t.process();
//                    t.p();
//                }
//            };
//            thread.start();
//        }
        Date d1 = new Date();
//        try {
//            Thread.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Date d2 = new Date();
        System.out.println(d1 == d2);
        System.out.println(d1.getTime() == d2.getTime());
        System.out.println(d1.hashCode() == d2.hashCode());
    }

    @Test
    public void teseThread() {
        ThreadLocal threadLocal = new ThreadLocal();


        threadLocal.get().getClass();
    }

}

