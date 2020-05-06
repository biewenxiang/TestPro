package com.bwx.javaee;

public class Base {
    static int num = 1;

    static {
        System.out.println("Base " + num);
    }

    public void test1() {

    }

    public void test2(String aa) {
        System.out.println(aa);
    }

}

class Derived extends Base {
    static {
        System.out.println("Derived " + num);
    }
}