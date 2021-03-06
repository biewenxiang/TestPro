package com.bwx.concurrent;

/**
 * 测试多线程notify
 */
public class TestNotify {
    public static void main(String[] args) {
        new Thread(new Thread1()).start();
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Thread thread2 = new Thread(new Thread2());
        thread2.start();
//        TestNotify.class.notify();
//        try {
//            Thread.sleep(5000);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    private static class Thread1 implements Runnable {

        @Override
        public void run() {
            synchronized (TestNotify.class) {
                System.out.println("enter thread1...");
                System.out.println("thread1 is waiting...");
                try {
                    //调用wait()方法，线程会放弃对象锁，进入等待此对象的等待锁定池
                    TestNotify.class.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                System.out.println("thread1 is going on ....");
                System.out.println("thread1 is over!!!");
//                TestNotify.class.notify();

            }
        }
    }

    private static class Thread2 implements Runnable {

        @Override
        public void run() {

            synchronized (TestNotify.class) {
                System.out.println("enter thread2....");
                System.out.println("thread2 is sleep....");
                //只有针对此对象调用notify()方法后本线程才进入对象锁定池准备获取对象锁进入运行状态。
//                TestNotify.class.notify();
                //==================
                //区别
                //如果我们把代码：TestD.class.notify();给注释掉，即TestD.class调用了wait()方法，但是没有调用notify()
                //方法，则线程永远处于挂起状态。
                try {
//                    TestNotify.class.wait();
                    //sleep()方法导致了程序暂停执行指定的时间，让出cpu该其他线程，
                    //但是他的监控状态依然保持者，当指定的时间到了又会自动恢复运行状态。
                    //在调用sleep()方法的过程中，线程不会释放对象锁。
                    Thread.sleep(2000);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("thread2 is beginning wait....");

                System.out.println("thread2 is going on....");
                System.out.println("thread2 is over!!!");
                TestNotify.class.notify();

            }

        }
    }

    public void testMethod(Object lock) {
        try {
            synchronized (lock) {
                System.out.println("begin wait() ThreadName="
                        + Thread.currentThread().getName());
                lock.wait();
                System.out.println("  end wait() ThreadName="
                        + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void synNotifyMethod(Object lock) {
        try {
            synchronized (lock) {
                System.out.println("begin notify() ThreadName="
                        + Thread.currentThread().getName() + " time="
                        + System.currentTimeMillis());
                lock.notify();
                Thread.sleep(5000);
                System.out.println("  end notify() ThreadName="
                        + Thread.currentThread().getName() + " time="
                        + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


