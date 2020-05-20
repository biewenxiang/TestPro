package com.bwx.concurrent;


/**
 * interrupt()方法

   如果不会中断sleep，wait，join方法或文档描述的其他情况，就不会抛InterruptException异常，就不会清除中断标志位，isInterrupt()返回true。

   如果中断sleep，wait，join等，就会抛InterruptException异常，就会清除中断标志位，isInterrupt()返回false。

 interrupted()方法

   第一次使用返回true，并清除中断标志位，在此之后查询中断状态isInterrupt()都会返回false，刚刚第一个例子也看到了，利用    第一次返回的true可以跳出循环。第二次以及以后都是返回false。

 isInterrupted()方法

   仅仅查询中断标志位来判断是否发生中断并返回true或者false。
 ————————————————
 版权声明：本文为CSDN博主「洋__」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 原文链接：https://blog.csdn.net/qq_34115899/java/article/details/81114969
 */
public class InterruptTest {

    public static void main(String[] args) throws InterruptedException {

//        Thread t = new Thread(new Thread1());
//        t.start();
//
//        Thread.sleep(200);
//        t.interrupt();
//        System.out.println("main Worker IsInterrupted: " + t.isInterrupted());
//
//        System.out.println("Main thread stopped.");

        Thread t1 = new Thread() {
            public void run() {
                while (true) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Interruted!");
                        break;
                    }
                    try {
                        Thread.sleep(2000); // 睡眠时中断会清除中断标志位
                    } catch (InterruptedException e) {
                        //interrupt是触发中断
                        Thread.currentThread().interrupt();


//                        Thread.interrupted();
//                        Thread.currentThread().isInterrupted()查看线程的中断状态
                        System.out.println(Thread.currentThread().isInterrupted());
                        System.out.println(Thread.currentThread().isInterrupted());
//                        Thread.interrupted() 第一次使用返回true，并清除中断标志位，在此之后查询中断状态isInterrupt()都会返回false，
                        System.out.println(Thread.interrupted());

                        System.out.println(Thread.currentThread().isInterrupted());
                        Thread.currentThread().interrupt();


                        System.out.println(Thread.currentThread().isInterrupted());




                        // 如果少了下面这句，这个线程虽然在外面中断，但是只要中断睡眠中的进程
                        // 就会清除中断标志位，仍然处于无限循环，会竞争CPU资源
//                        Thread.currentThread().interrupt(); // 再次中断置上中断标记
                    }
                    Thread.yield();
                }
            }
        };
        t1.start();
        Thread.sleep(200);
        t1.interrupt();
    }

}

class Thread1 implements Runnable {

    @Override
    public void run() {
        System.out.println("Worker started.");

        try {

            Thread.sleep(5000);

        } catch (InterruptedException e) {
            System.out.println("Worker IsInterrupted: " +
                    Thread.currentThread().isInterrupted());
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
