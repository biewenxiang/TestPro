package com.bwx.iotest;

import org.apache.commons.io.IOUtils;
import org.apache.kafka.common.protocol.types.Field;
import org.junit.Test;

import java.io.*;
import java.sql.PreparedStatement;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class testIO {


    /**
     * 字节流测试
     *
     * @throws IOException
     */
    @Test
    public void testIo() throws IOException {
        //读取文件(字节流)
        InputStream in = new FileInputStream("d:\\test\\test.txt");
        //写入相应的文件
        OutputStream out = new FileOutputStream("d:\\test\\2.txt");
        //读取数据
        //一次性取多少字节
        byte[] bytes = new byte[2];
        //接受读取的内容(n就代表的相关数据，只不过是数字的形式)
        int n = -1;
        //循环取出数据
        while ((n = in.read(bytes, 0, bytes.length)) != -1) {
            //转换成字符串
            String str = new String(bytes, 0, n, "Utf-8");//这里可以实现字节到字符串的转换，比较实用
            System.out.println(str);
            //写入相关文件
            out.write(bytes, 0, n);
        }
        //关闭流
        in.close();
        out.close();
    }

    //缓存字节流
    @Test
    public void buffTest() throws IOException {
        //读取文件(缓存字节流)
        BufferedInputStream in = new BufferedInputStream(new FileInputStream("d:\\test\\test.txt"));
        //写入相应的文件
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("d:\\test\\2.txt"));
        //读取数据
        //一次性取多少字节
//        byte[] bytes = new byte[2048];
        byte[] bytes = new byte[2];

        //接受读取的内容(n就代表的相关数据，只不过是数字的形式)
        int n = -1;
        //循环取出数据
        while ((n = in.read(bytes, 0, bytes.length)) != -1) {
            //转换成字符串
            String str = new String(bytes, 0, n, "UTF-8");
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
//        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("d:\\test\\test.txt"), "UTF-8"));
        //#这里主要是涉及中文
        BufferedReader in = new BufferedReader(new FileReader("d:\\test\\test.txt"));
        //写入相应的文件
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("d:\\test\\2.txt"), "UTF-8"));
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
//        new InputStreamReader(new FileInputStream(""))s;
        File file = new File("D:\\Test\\test12\\test1.txt");
        //如果没有文件就创建
        System.out.println(file.getParent());
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.isFile()) {
            file.createNewFile();
        }
        file.listFiles();
        System.out.println();
    }

    @Test
    public void testio2() throws IOException {
        StringBuilder aaa = new StringBuilder();
        String string = "";
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\admin\\Desktop\\station.txt"), "UTF-8"));
        Set list = new HashSet();
        while ((string = in.readLine()) != null) {
//            if (string.contains("{\"") && string.contains("2019041708")) {
                aaa.append(string);
//                string.split(",");
//                list.add(string.split(",")[0]);
//            }
            //写入相关文件
        }
//        writeFile("C:\\Users\\admin\\Desktop\\aa2.txt", aaa.toString());

        long begin = System.currentTimeMillis();
        for (int i = 1; i < 10000; i++) {
            writeFile4("E:\\Test\\test1111\\test" + i + ".htm", aaa.toString());
        }
//        7002; 5926  113,301 66,068
        System.out.println(System.currentTimeMillis() - begin);

    }
    //21483字节流写入

    public static boolean writeFile(String filePath, String content) {

        File file = new File(filePath);
        FileOutputStream output = null;
        OutputStreamWriter osw = null;
        boolean writeFlag = false;
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            String contentStr = content.replaceAll("(?<=>)\\s+|\\s+(?=<)", "\n").trim();
//            177065 50968 52308

//            String contentStr = content.replaceAll("\\s+<", "\n<").replaceAll(">\\s+", ">\n").trim();
            if (contentStr != null && !contentStr.isEmpty()) {
                output = new FileOutputStream(file);
                osw = new OutputStreamWriter(new BufferedOutputStream(output), "utf-8");
                osw.write(contentStr);
                osw.flush();
            }
            writeFlag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (osw != null) {
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return writeFlag;
    }
//9431 字符流
    private boolean writeFile2(String filePath, String content) {
        File file = new File(filePath);
        boolean writeFlag = false;
        FileWriter writer = null;

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }


        try {
            writer = new FileWriter(filePath);
//            String contentStr = content.replaceAll("(?<=>)\\s+|\\s+(?=<)", "\n").trim();
            //41595 42017
            String contentStr = content.replaceAll("\\s+<", "\n<").replaceAll(">\\s+", ">\n").trim();
            //39527

            if (contentStr != null && !contentStr.isEmpty()) {
//                IOUtils.write(content, writer);
                writer.write(content);
                writer.flush();
            }
            writeFlag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return writeFlag;
    }

//16837缓存字节流
    private void writeFile3(String filePath, String content) {
        File file = new File(filePath);

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        try {
            fos = new FileOutputStream(filePath);
            bos = new BufferedOutputStream(fos);
//            String contentStr = content.replaceAll("\\s+<", "\n<").replaceAll(">\\s+", ">\n").trim();
            String contentStr = content.replaceAll("(?<=>)\\s+|\\s+(?=<)", "\n").trim();

            byte[] tempBytes = contentStr.getBytes("UTF-8");
            bos.write(tempBytes);
            bos.flush();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }

    //17708缓存字符流
    private void writeFile4(String filePath, String content) {
        File file = new File(filePath);

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        FileWriter fos = null;
        BufferedWriter bos = null;
        try {
            fos = new FileWriter(filePath);
            bos = new BufferedWriter(fos);
//            String contentStr = content.replaceAll("\\s+<", "\n<").replaceAll(">\\s+", ">\n").trim();
            String contentStr = content.replaceAll("(?<=>)\\s+|\\s+(?=<)", "\n").trim();

//            byte[] tempBytes = contentStr.getBytes("UTF-8");
            bos.write(contentStr);
            bos.flush();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }
    @Test
    public void testadd(){

        String s = null;
        if (s==null||s.length()==0){

        }
        int i = 12;
        System.out.println(i+=i-=i*=i);
    }
}
