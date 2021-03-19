package com.bwx.concurrent;

/**
 * @Author: beiwenxiang
 * @Description:
 * @Date: create in 2020/6/2 14:16
 */
public class ApplicationLoader extends ThreadGroup

{

    private ApplicationLoader()

    {

        super("ApplicationLoader");

    }


    public static void main(String[] args)

    {

        Runnable appStarter = new Runnable() {
            @Override
            public void run() {

                {

                    //invoke your application
                    ThreadPoolTest.main(args);
//                    System.main(args);

                }
            }
        };

        new Thread(new ApplicationLoader(), appStarter).start();

    }


    //We overload this method from our parent

    //ThreadGroup , which will make sure that it

    //gets called when it needs to be.  This is

    //where the magic occurs.

    public void uncaughtException(Thread thread, Throwable exception)

    {
        exception.printStackTrace();
        System.out.println("0---");

        //Handle the error/exception.

        //Typical operations might be displaying a

        //useful dialog, writing to an event log, etc.

    }
}
