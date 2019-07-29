package com.bwx.test;

import com.bwx.cache.SunDataCache;
import org.apache.commons.io.IOUtils;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.junit.Test;
import sun.text.resources.fr.FormatData_fr;

import java.io.*;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class TestSome {
    @Test
    public void testString() {
        String aa = "123456741";
        char[] chars = aa.toCharArray();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {

//            System.out.println(chars[i]);
            list.add(Integer.parseInt(String.valueOf(chars[i])));
        }
        Arrays.sort(chars);
        Collections.sort(list);
        for (char i : chars) {
            System.out.println(i);
        }
        System.out.println(chars);
    }

    @Test
    public void testSome() {


        String sss = "101010100,15,,预计未来15天白天平均气温为4℃,,较冷,2018年11月29日-2018年12月13日\n";
        String[] ssp = sss.split(",");
        String[] aa = {"stationid", "type", "level", "msg1", "msg2", "msg_label", "time"};
        for (int i = 0; i < aa.length; i++) {
            System.out.println("tips." + aa[i] + "=" + "00" + i);
        }

    }

    @Test
    public void testSome2() {
        String value = "[{\"sunrise\":\"01:46\",\"date\":\"20181219\",\"daynum\":1,\"sunset\":\"15:16\"},{\"sunrise\":\"01:46\",\"date\":\"20181220\",\"daynum\":2,\"sunset\":\"15:17\"},{\"sunrise\":\"01:47\",\"date\":\"20181221\",\"daynum\":3,\"sunset\":\"15:17\"},{\"sunrise\":\"01:47\",\"date\":\"20181222\",\"daynum\":4,\"sunset\":\"15:18\"},{\"sunrise\":\"01:48\",\"date\":\"20181223\",\"daynum\":5,\"sunset\":\"15:18\"},{\"sunrise\":\"01:48\",\"date\":\"20181224\",\"daynum\":6,\"sunset\":\"15:19\"},{\"sunrise\":\"01:49\",\"date\":\"20181225\",\"daynum\":7,\"sunset\":\"15:19\"}]";
        String value2 = "[{\"sunrise\":\"07:34\",\"date\":\"20181226\",\"daynum\":1,\"sunset\":\"16:55\"},{\"sunrise\":\"07:34\",\"date\":\"20181227\",\"daynum\":2,\"sunset\":\"16:55\"},{\"sunrise\":\"07:34\",\"date\":\"20181228\",\"daynum\":3,\"sunset\":\"16:56\"},{\"sunrise\":\"07:35\",\"date\":\"20181229\",\"daynum\":4,\"sunset\":\"16:57\"},{\"sunrise\":\"07:35\",\"date\":\"20181230\",\"daynum\":5,\"sunset\":\"16:57\"},{\"sunrise\":\"07:35\",\"date\":\"20181231\",\"daynum\":6,\"sunset\":\"16:58\"},{\"sunrise\":\"07:35\",\"date\":\"20190101\",\"daynum\":7,\"sunset\":\"16:59\"}]";
        SunDataCache.setMap("101010100", value2);
        SunDataCache.setMap("101010200", value);
        System.out.println(SunDataCache.getSun("101010100").size());

        System.out.println(value.length());

    }

    @Test
    public void testSome3() {
        "/ser/www/dasdasd/dasda".replace("www", "www_test");


        System.out.println("/ser/www/dasdasd/dasda".replace("www", "www_test"));

    }

    @Test
    public void download2() {
        for (int i = 1; i <= 343; i++) {
            String name = i + ".jpg";
            String url = "https://book.yunzhan365.com/mdng/iszo/files/mobile/" + name;
            String path = "d:/test/pic/" + name;

            downloadPicture(url, path);
        }
    }

    //链接url下载图片
    private static void downloadPicture(String urlList, String path) {
        URL url = null;
        try {
            url = new URL(urlList);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());

            FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test() throws FileNotFoundException {

        String[] rowkeys = "15231212 54545".trim().split(",");
        rowkeys[0].length();
        method();

//        try {
//            method();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public void method() throws FileNotFoundException {
        //一个会抛出异常的方法
        method2();
    }

    //这里 方法后是throws
    public void method2() throws FileNotFoundException {
        //这里是throw
        throw new FileNotFoundException();
    }

    @Test
    public void testss() {
        String aa = "released/M00/00/01/wKjvgVqftLSACaIBAVGSL-dpgpQ774.jar";

        String group = aa.substring(0, aa.indexOf("/"));
        String name = aa.substring(aa.indexOf("/") + 1);

        System.out.println(group + "   " + name);
    }

    @Test
    public void download() {
        try {
            // 初始化全局配置。加载一个配置文件
            ClientGlobal.init("fdfs_client.conf");

            String aa = "dasdasdasdasda";
//            String tt = uploadFile(aa.getBytes(),"aa","txt");
//group1/M00/00/00/wKh6gVzVIgOARwSTAAAADraBhus458.txt
//            System.out.println(tt);


            // 创建一个TrackerClient对象
            TrackerClient tracker = new TrackerClient();
            // 创建一个TrackerServer对象。
            TrackerServer trackerServer = tracker.getConnection();
            // 声明一个StorageServer对象，
            StorageServer storageServer = null;
            // 获得StorageClient对象
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);

            String group = "group1";
            String name = "M00/00/00/wKh6gVzVIgOARwSTAAAADraBhus458.txt";
            NameValuePair[] metadata = storageClient.get_metadata(group, name);
            System.out.println(metadata[1].getValue());
            byte[] b = storageClient.download_file(group, name);
            System.out.println(b);
            // 将下载的文件流保存
            IOUtils.write(b, new FileOutputStream("D:/" + UUID.randomUUID().toString() + ".jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String uploadFile(byte[] content, String fileName, String fileExtName) {
        String result = null;
        TrackerClient client = new TrackerClient();
        TrackerServer server = null;
        try {
            server = client.getConnection();
        } catch (IOException e) {
            e.printStackTrace();
            return result;
        }
        StorageServer storageServer = null;
        StorageClient storageClient = new StorageClient(server, storageServer);

        NameValuePair[] metaList = new NameValuePair[3];
        metaList[0] = new NameValuePair("fileName", fileName);
        metaList[1] = new NameValuePair("fileExtName", fileExtName);
        metaList[2] = new NameValuePair("fileSize", String.valueOf(content.length));

        try {
            String[] upload_file = storageClient.upload_file(content, fileExtName, metaList);
            result = upload_file[0] + "/" + upload_file[1];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Test
    public void teststr() {
        String aa = "{\"1d\":[\"27日08时,d00,晴,-13℃,东北风,<3级\",\"27日11时,d00,晴,-9℃,东北风,<3级\",\"27日14时,d02,阴,-6℃,东北风,<3级\",\"27日17时,d02,阴,-9℃,东北风,<3级\",\"27日20时,n02,阴,-13℃,东北风,<3级\",\"27日23时,n01,多云,-13℃,东北风,<3级\",\"28日02时,n01,多云,-14℃,东北风,<3级\",\"28日05时,n01,多云,-14℃,东北风,<3级\",\"28日08时,d00,晴,-11℃,东北风,<3级\",\"28日11时,d01,多云,-7℃,东北风,<3级\",\"28日14时,d00,晴,-6℃,东南风,<3级\",\"28日17时,d02,阴,-8℃,东北风,<3级\",\"28日20时,n00,晴,-12℃,北风,<3级\"],\"23d\":[";
        int bb = aa.length() - 1;
        aa = aa.substring(0, bb);
        System.out.println(aa);

    }

    @Test
    public void testaa() {

        String aa = "2019041720";
        String aa2 = "2019041806";

        System.out.println(Integer.parseInt(aa2) > Integer.parseInt(aa));
        Integer integer = new Integer(101010);
        int a2 = Integer.parseInt("1111");
        System.out.println(a2);
    }

}