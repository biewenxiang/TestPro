package com.bwx.concurrent;

import java.util.concurrent.*;


public class ThreadPoolTest {
    public static void main(String[] args) {
//        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
        int coreThreadSize = 5; //核心线程数
        int MaxThreadSize = 10;//最大线程数
        int MaxWaitQuene = 10 ;//最大等待队列
        long keep_alive_time = 1l;
        ExecutorService fixedThreadPool = null;
        fixedThreadPool = new ThreadPoolExecutor(coreThreadSize,MaxThreadSize,
                keep_alive_time, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(MaxWaitQuene),new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 100; i++) {
            try {
                fixedThreadPool.submit(new ThreadStatic2(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("aa");
        fixedThreadPool.shutdown();
        while (!fixedThreadPool.isTerminated()){

        }
        System.out.print("all finished");
    }
    static class ThreadStatic2 implements Runnable {
        private String aa ;
        public ThreadStatic2(int i) {
            this.aa = i+"";
        }

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
