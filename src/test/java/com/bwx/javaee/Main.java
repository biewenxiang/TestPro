package com.bwx.javaee;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        // 不会初始化静态块
//        Base base = new Derived();
//        if (base instanceof Derived) {
//            // 这里可以向下转换了
//            System.out.println("ok");
//        }
//        else {
//            System.out.println("not ok");
//        }
        Class clazz1 = Base.class;
        System.out.println("------");
        // 会初始化
        try {
            Class clazz2 = Class.forName("com.bwx.javaee.Base");
            "com.bwx.javaee.Base".getClass();
//            Method[] method = clazz2.getMethods();
            Method method2 = clazz2.getMethod("test2", String.class);
            Constructor constructor = clazz2.getConstructor();
            Object object = constructor.newInstance();
            method2.invoke(object, "3");
            Field[] field = clazz2.getDeclaredFields();
            Method[] methods = clazz2.getMethods();
            methods.hashCode();
            field.hashCode();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
