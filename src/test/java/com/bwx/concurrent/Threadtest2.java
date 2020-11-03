package com.bwx.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Threadtest2 {
    public static void main(String[] args) {
        ExecutorService fixedThreadPool = null;
        fixedThreadPool = Executors.newFixedThreadPool(30);
        for (int i = 0; i < 100; i++) {
            try {
                fixedThreadPool.submit(new ThreadStatic2());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("aa");
        fixedThreadPool.shutdown();
    }
    static class ThreadStatic2 implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }
    }
}
