package com.bwx.javaee;

import com.bwx.config.Constant;
import org.apache.hadoop.hdfs.protocol.proto.HdfsProtos;
import org.mortbay.util.Loader;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Properties;

public class TestMain2 {
    //    public static void main(String[] args) throws ClassNotFoundException {
//        ClassLoader loader = ;
//        Class class1 = Class.forName("com.bwx.javaee.Apple", true, loader);
//    }
    enum FileType {
        JAR, CLASS, OTHER
    }

    static class MyClassLoader extends ClassLoader {
        public synchronized Class<?> loadClass(String name, File file) throws FileNotFoundException {
            Class<?> cls = findLoadedClass(name);
            if (cls != null) {
                return cls;
            }
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            try {
                while (true) {
                    len = fis.read(buffer);
                    if (len == -1) {
                        break;
                    }
                    baos.write(buffer, 0, len);
                }
                //FileInputStream的flush是空操作，因为flush的作用是把缓存中的东西写入实体(硬盘或网络流)中，这里没有这种必要所以为空
                //baos.flush();
                byte[] data = baos.toByteArray();
                return defineClass(null, data, 0, data.length);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }


    public static void main(String[] args) throws MalformedURLException {

        String kafka_host = Constant.kafka_host;
        Properties props = new Properties();
        props.put("bootstrap.servers", kafka_host);
        props.put("acks", "all");
        props.put("retries", Integer.valueOf(0));
        props.put("batch.size", Integer.valueOf(16384));
        props.put("linger.ms", Integer.valueOf(1));
        props.put("buffer.memory", Integer.valueOf(33554432));
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");


        String className = "com.bwx.kafka.KafkaProvide";
        String paths[] = {"TestPro.class"};
        for (String path : paths) {
            String lowerPath = path.toLowerCase();
            FileType fileType = FileType.OTHER;
            if (lowerPath.endsWith(".jar") || lowerPath.endsWith(".zip")) {
                fileType = FileType.JAR;
            } else if (lowerPath.endsWith(".class")) {
                fileType = FileType.CLASS;
            }
            if (fileType == FileType.OTHER) {
                return;
            }
            String resouerce = "D:\\IdeaProjects\\TestPro\\out\\artifacts\\TestPro_jar\\TestPro.jar";
            File file = new File(resouerce);
            if (!file.exists()) {
//                return;
            }
            try {
                URL url = file.toURI().toURL();
                System.out.println(url.toString());
                Class<?> cls = null;
                switch (fileType) {
                    case JAR:
                        URLClassLoader classLoader = new URLClassLoader(new URL[]{url}, Thread.currentThread().getContextClassLoader());
                        cls = classLoader.loadClass(className);
                        break;
                    case CLASS:
                        MyClassLoader myClassLoader = new MyClassLoader();
//                        cls = myClassLoader.loadClass(className, file);
                        cls = Class.forName(className);
                        break;

                    default:
                        break;
                }
                if (cls == null) {
                    return;
                }
// 实例变量


                Method staticMethod2 = cls.getDeclaredMethod("sendMessage", Properties.class);
                if (!staticMethod2.isAccessible()) {
                    staticMethod2.setAccessible(true);
                }
// 如果函数的返回值是void，就会返回null
                System.out.println(staticMethod2.invoke(cls, props));
                System.out.println("---------");


                Field field = cls.getDeclaredField("hello");
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                System.out.println(field.get(cls.newInstance()));
// 调用静态不带参数方法
                Method staticMethod = cls.getDeclaredMethod("sayStaticHello", null);
                if (!staticMethod.isAccessible()) {
                    staticMethod.setAccessible(true);
                }
// 如果函数的返回值是void，就会返回null
                System.out.println(staticMethod.invoke(cls, null));
// 实例带参数方法方法
                Method method = cls.getDeclaredMethod("say", String.class);
                if (!method.isAccessible()) {
                    method.setAccessible(true);
                }
                Object ret = method.invoke(cls.newInstance(), "hello world");
                System.out.println(ret);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }
}

