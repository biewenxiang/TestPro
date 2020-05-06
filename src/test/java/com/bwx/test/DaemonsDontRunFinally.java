package com.bwx.test;

import java.util.concurrent.*;
class ADaemon implements Runnable{
    public void run(){
        try {
            System.out.println("Starting Adaemon");
// 去掉反而打出finally、	
//	TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally{
            System.out.println("this shoould always run?");
        }
    }
}

public class DaemonsDontRunFinally{
    public static void main(String args[]){
        Thread t=new Thread(new ADaemon());
        t.setDaemon(true);
        t.start();

    }
}