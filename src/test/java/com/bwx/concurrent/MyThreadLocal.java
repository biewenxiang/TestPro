package com.bwx.concurrent;

import java.util.Date;

public class MyThreadLocal {
    private ThreadLocal date = new ThreadLocal();
    private Date d = null;

    public void process() {
        if (date.get() == null) {
            date.set(new Date());
            System.out.println("thread local fileld:" + date.get());
        }
    }

    //操作普通成员，需要同步处理
    public void p() {
//        Date d = null;

        synchronized (MyThreadLocal.class) {
        if (d == null) {
            d = new Date();
            System.out.println("ordinary field:" + d);
            }
        }
    }
}

