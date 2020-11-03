package com.bwx.javaee;


import org.junit.runners.JUnit4;

/**
 * @Author: beiwenxiang
 * @Description:
 * @Date: create in 2020/10/30 15:47
 */
public class Generic<T> {
    private T key;

    public Generic(T key) {
        this.key = key;
    }

    public T getKey() {
        return key;
    }

    public static void main(String[] args) {
        java.lang.String ab = new java.lang.String("aa");
        java.lang.String ab2 = new java.lang.String("aa");

        String a = "aa";
        String a1 = "aa";
        System.out.println(a ==a1);
        System.out.println(ab ==ab2);
        System.out.println(ab.equals(ab2));

    }
}

