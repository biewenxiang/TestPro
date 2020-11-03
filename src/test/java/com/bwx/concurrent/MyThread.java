package com.bwx.concurrent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Thread.yield()方法的作用：暂停当前正在执行的线程，并执行其他线程。（可能没有效果）
 */
public class MyThread implements Callable<String> {
    private int count = 20;

    @Override
    public String call() throws Exception {
        for (int i = count; i > 0; i--) {

            System.out.println(Thread.currentThread().getName() + "当前票数：" + i);
//            Thread.yield();

        }
        return "sale out";
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List aa = new ArrayList();
        for (int i = 0; i < 3; i++) {
            Callable worker = new MyThread();
            executorService.submit(worker);
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {
        }
        System.out.println("Finished all threads");
        System.out.println(aa);
    }
}


