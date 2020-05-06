package com.bwx.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 多线程同步test
 */
public class TestMain {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                SynchronizedTest s = SynchronizedTest.getInstance();
                s.method1();

//                SynchronizedTest.staticIn.method1();
//
//		SynchronizedTest.staticMethod1();
////		SynchronizedTest.staticMethod2();
//
//
                SynchronizedTest.staticMethod1();;

            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                SynchronizedTest s1 = new SynchronizedTest();
                s1.method2();

//                SynchronizedTest.staticMethod1();
//                SynchronizedTest.staticMethod2();

            }
        });
        long aa =System.currentTimeMillis();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(t2);
        exec.execute(t1);
        exec.shutdown();
        System.out.println(System.currentTimeMillis()-System.currentTimeMillis());

    }


}
