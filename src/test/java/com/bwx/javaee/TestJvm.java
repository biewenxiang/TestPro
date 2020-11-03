package com.bwx.javaee;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Author: beiwenxiang
 * @Description:
 * @Date: create in 2020/5/26 10:13
 */
public class TestJvm {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Max_memory=" + Runtime.getRuntime().maxMemory() / (double) 1024 / 1024 + "M");
        System.out.println("Total_memory=" + Runtime.getRuntime().totalMemory() / (double) 1024 / 1024 + "M");
        Random random = new Random();
        String string = "213123";
        while (true) {
            string += string + random.nextInt(8888888) + random.nextInt(8888888);
            string.intern(); //强制产生垃圾
            System.out.println(string.length());

//            System.out.println("Max_memory=" + Runtime.getRuntime().maxMemory() / (double) 1024 / 1024 + "M");
            if (Runtime.getRuntime().totalMemory() / (double) 1024 / 1024>500){
                Thread.sleep(2*1000);
            }
        }
    }
}

