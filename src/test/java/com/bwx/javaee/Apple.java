package com.bwx.javaee;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Apple {

    private static int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public static void main(String[] args) throws Exception {
        //正常的调用
        Apple apple = new Apple();
        apple.setPrice(5);
        System.out.println("Apple Price:" + apple.getPrice());
        //使用反射调用
        Class clz = Class.forName("com.bwx.javaee.Apple");
        Method setPriceMethod = clz.getMethod("setPrice", int.class);

        Constructor appleConstructor = clz.getConstructor();
        Object appleObj = appleConstructor.newInstance();

        setPriceMethod.invoke(appleObj, 14);
        Method getPriceMethod = clz.getMethod("getPrice");
        System.out.println("Apple Price:" + getPriceMethod.invoke(appleObj));
        System.out.println("Apple Price:" + apple.getPrice());

    }
}