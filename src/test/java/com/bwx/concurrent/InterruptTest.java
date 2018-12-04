package com.bwx.concurrent;

public class InterruptTest {

    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(new Thread1());
        t.start();

        Thread.sleep(200);
        t.interrupt();
        System.out.println("main Worker IsInterrupted: " + t.isInterrupted());

        System.out.println("Main thread stopped.");
    }

}

class Thread1 implements Runnable {

    @Override
    public void run() {
        System.out.println("Worker started.");

        try {

            Thread.sleep(5000);

        } catch (InterruptedException e) {
//            System.out.println("Worker IsInterrupted: " +
//                    Thread.currentThread().isInterrupted());
            Thread curr = Thread.currentThread();

            curr.interrupt();

            System.out.println("Worker IsInterrupted: " + curr.isInterrupted());
            System.out.println("Worker IsInterrupted: " + curr.isInterrupted());
            System.out.println("Static Call: " + Thread.interrupted());//clear status

            System.out.println("---------After Interrupt Status Cleared----------");
            System.out.println("Static Call: " + Thread.interrupted());
            System.out.println("Worker IsInterrupted: " + curr.isInterrupted());
            System.out.println("Worker IsInterrupted: " + curr.isInterrupted());
        }

        System.out.println("Worker stopped.");
    }

}
