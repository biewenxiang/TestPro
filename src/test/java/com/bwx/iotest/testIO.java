package com.bwx.iotest;

import org.junit.Test;

import java.io.*;

public class testIO {


    @Test
    public void testIo() throws IOException {
        //读取文件(字节流)
        InputStream in = new FileInputStream("d:\\test\\test.txt");
        //写入相应的文件
        OutputStream out = new FileOutputStream("d:\\test\\2.txt");
        //读取数据
        //一次性取多少字节
        byte[] bytes = new byte[2048];
        //接受读取的内容(n就代表的相关数据，只不过是数字的形式)
        int n = -1;
        //循环取出数据
        while ((n = in.read(bytes,0,bytes.length)) != -1) {
            //转换成字符串
            String str = new String(bytes,0,n,"GBK");//这里可以实现字节到字符串的转换，比较实用
            System.out.println(str);
            //写入相关文件
            out.write(bytes, 0, n);
        }
        //关闭流
        in.close();
        out.close();
    }
    //缓存字符流
    @Test
    public void buffTest() throws IOException {
        //读取文件(缓存字节流)
        BufferedInputStream in = new BufferedInputStream(new FileInputStream("d:\\test\\test.txt"));
        //写入相应的文件
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("d:\\test\\2.txt"));
        //读取数据
        //一次性取多少字节
        byte[] bytes = new byte[2048];
        //接受读取的内容(n就代表的相关数据，只不过是数字的形式)
        int n = -1;
        //循环取出数据
        while ((n = in.read(bytes,0,bytes.length)) != -1) {
            //转换成字符串
            String str = new String(bytes,0,n,"GBK");
            System.out.println(str);
            //写入相关文件
            out.write(bytes, 0, n);
        }
        //清楚缓存
        out.flush();
        //关闭流
        in.close();
        out.close();
    }

    @Test
    public void testReader() throws IOException {
        //读取文件(字符流)
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("d:\\test\\test.txt"),"GBK"));
        //#这里主要是涉及中文
//        BufferedReader in = new BufferedReader(new FileReader("d:\\test\\test.txt"));
        //写入相应的文件
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("d:\\test\\2.txt"),"GBK"));
        //BufferedWriter out = new BufferedWriter(new FileWriter("d:\\2.txt"))；
        //读取数据
        //循环取出数据
        String str = null;
        while ((str = in.readLine()) != null) {
            System.out.println(str);
            //写入相关文件
            out.write(str);
            out.newLine();
        }

        //清楚缓存
        out.flush();
        //关闭流
        in.close();
        out.close();

    }

    @Test
    public void testio() throws IOException {
        File file = new File("D:\\Test\\test12\\test1.txt");
        //如果没有文件就创建
        System.out.println(file.getParent());
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        if (!file.isFile()) {
            file.createNewFile();
        }
        file.listFiles();
        System.out.println();
    }



}
