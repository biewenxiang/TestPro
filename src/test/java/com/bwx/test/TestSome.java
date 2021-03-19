package com.bwx.test;

import cn.itcast.mobile.MobileCodeWSSoap;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bwx.cache.SunDataCache;
import com.bwx.utils.HttpUtil;
import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.storm.shade.org.joda.time.DateTime;
import org.apache.storm.shade.org.joda.time.Weeks;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import sun.text.resources.fr.FormatData_fr;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.io.*;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

public class TestSome {
    private static String stime = "20190106000000";
    private static final DateTime start_time = new DateTime(Integer.valueOf(stime.substring(0, 4)),
            Integer.valueOf(stime.substring(4, 6)), Integer.valueOf(stime.substring(6, 8)),
            Integer.valueOf(stime.substring(8, 10)), Integer.valueOf(stime.substring(10, 12)),
            Integer.valueOf(stime.substring(12, 14)));
    private static List<String> car_array = new ArrayList<String>();

    static {
        car_array.add("1和6");
        car_array.add("2和7");
        car_array.add("3和8");
        car_array.add("4和9");
        car_array.add("5和0");
        car_array.add("不限行");
    }

    // 北京限行（公休/节假日不限行）无补休
    private static int getLimitCar_Bj(DateTime dt) {
        // String str = "1和6,2和7,3和8,4和9,5和0";
        int curweek = dt.getDayOfWeek();
        if (curweek == 6 || curweek == 7) {
            return 5;
        }
        int weekCha = Weeks.weeksBetween(start_time, dt).getWeeks() + 1;
        int curnPos = ((int) Math.ceil(weekCha / 13.0) - 1);
        curnPos = curnPos < 0 ? 0 : curnPos;
        curnPos = curnPos % 5;
        int arrkey = (curweek - (1 + curnPos));
        arrkey = arrkey > 0 ? arrkey : arrkey + 5;
        if (arrkey > 4) {
            arrkey = arrkey % 5;
        }
        return arrkey;
    }

    @Test
    public void testcar() {
        for (int i = 3; i < 120; i++) {
            DateTime dt = new DateTime().plusDays(i);
            System.out.println(dt + " : " + car_array.get(getLimitCar_Bj(dt)));
        }

    }


    @Test
    public void test122() throws MalformedURLException {
        URL wsdlDocumentLocation = new URL("http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl");
        //创建服务名称
        //1.namespaceURI - 命名空间地址
        //2.localPart - 服务视图名
        QName serviceName = new QName("http://WebXml.com.cn/", "MobileCodeWS");
        Service service = Service.create(wsdlDocumentLocation, serviceName);

        //获取服务实现类
        MobileCodeWSSoap mobileCodeWSSoap = service.getPort(MobileCodeWSSoap.class);
        //调用方法
        String message = mobileCodeWSSoap.getMobileCodeInfo("17611608621", null);
        System.out.println(message);
    }

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
    public void testaa() throws IOException {

        /**
         * @param args
         */

        PDFMergerUtility mergePdf = new PDFMergerUtility();

        String folder = "C:\\Users\\admin\\Desktop\\打印";
        String folder2 = "C:\\Users\\admin\\Desktop\\打印2";

        String destinationFileName = "hbTest.pdf";

        String[] filesInFolder = getFiles(folder);

        for (int i = 0; i < filesInFolder.length; i++) {
            cutPdf(folder + File.separator + filesInFolder[i], folder2 + File.separator + filesInFolder[i]);
            mergePdf.addSource(folder2 + File.separator + filesInFolder[i]);
        }


        mergePdf.setDestinationFileName(folder2 + File.separator + destinationFileName);
        mergePdf.mergeDocuments();

        System.out.print("done");


    }

    @Test
    public void testpdf() {
        try (PDDocument document = PDDocument.load(new File("C:\\Users\\admin\\Desktop\\打印\\hbTest.pdf"))) {

            if (!document.isEncrypted()) {
                PDFTextStripper tStripper = new PDFTextStripper();
                String pdfFileInText = tStripper.getText(document);
                String lines[] = pdfFileInText.split("\\r?\\n");
                for (String line : lines) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Exception while trying to read pdf document - " + e);
        }
    }

    private void cutPdf(String pdfPath, String newpdfPath) {
        File file = new File(pdfPath);
        PDDocument document = new PDDocument();
        try {
            document = PDDocument.load(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int noOfPages = document.getNumberOfPages();
        PDPageTree pdPages = document.getPages();
        System.out.println("start" + noOfPages);

        int count = pdPages.getCount();
        if (count > 0) {
            PDPage pdPage1 = new PDPage();
//            PDStream content = new PDStream(document);
//            pdPage1.setContents(content);
//            pdPage1.getMatrix()
            pdPages.insertAfter(pdPage1, pdPages.get(0));
        }

        System.out.println(document.getNumberOfPages());
        document.removePage(document.getNumberOfPages() - 1);

        if (document.getNumberOfPages() % 2 != 0) {
            PDPage pdPage = new PDPage();
            document.addPage(pdPage);
        }

        try {
            document.save(newpdfPath);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String[] getFiles(String folder) throws IOException {
        File _folder = new File(folder);
        String[] filesInFolder;

        if (_folder.isDirectory()) {
            filesInFolder = _folder.list();
            return filesInFolder;
        } else {
            throw new IOException("Path is not a directory");
        }
    }

    @Test
    public void testlat() {
        //39.95933&lon=116.29845
        getAreaId("39.95933", "116.29845");
    }

    public static int getAreaId(String lat, String lon) {
        String url = "http://d4.weather.com.cn/geong/v1/api?params=";
        String param = "{\"method\":\"stationinfo\",\"lat\":" + lat + ",\"lng\":" + lon + "}";
        int areaId = 0;
        try {
            String encode = URLEncoder.encode(param, "UTF-8");
            String string = HttpUtil.getMethod(url + encode, "UTF-8", false);
            JSONObject json = (JSONObject) JSONObject.parse(string);
            JSONObject data = json.getJSONObject("data");
            JSONObject station = data.getJSONObject("station");
            areaId = station.getIntValue("areaid");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return areaId;

    }

    @Test
    public void testvariance() {

        String content = HttpUtil.getMethod("http://goschool.weather.com.cn/schoolforecast/json?lat=39.48&lon=116.28", "UTF-8", false);
        JSONObject jsonObject = JSONObject.parseObject(content);
        JSONArray jsonArray = jsonObject.getJSONObject("result").getJSONArray("forecast24h");
//        System.out.println(jsonArray);
        ArrayList list = new ArrayList();
        for (Object object : jsonArray) {
            JSONObject jsonObject2 = (JSONObject) object;
            String high = jsonObject2.getString("004");
            list.add(Integer.parseInt(high));
        }
//        003 high 004low
        System.out.println(list.size());
        Object[] aa1 = new Object[list.size() - 1];
        aa1 = list.toArray();
        System.out.println(aa1.length);
//        int aa[] = new int[aa1.length];
        for (int i = 0; i < aa1.length; i++) {
//            aa[i] = (int) aa1[i];
        }
        int aa[] = {23, 9, 6, 8, 7, 4, 6, 4, -2, -4, -3, -1, -4, -5, -5, -5};


        dealvariance(aa);

    }

    public static Object dealvariance(int nums[]) {
        int aa[] = Arrays.copyOf(nums, 8);
//        int aa[] = nums;
        System.out.println(aa.length + "" + JSONObject.toJSONString(aa));
        double average = 0;
        int sum = 0;
        for (int i : aa) {
            sum += i;
        }
        average = sum / aa.length;
        System.out.println(average);
        double variance = 0;
        double sum2 = 0;
        for (int i : aa) {
            sum2 += Math.pow((i - average), 2);
        }
        variance = sum2 / aa.length;
        System.out.println(variance);
        return variance;
    }

    @Test
    public void testdate() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        for (int i = 1; i < 90; i++) {
            c.add(Calendar.DAY_OF_MONTH, 1);
            Date newDate = c.getTime();
            System.out.println(newDate);
        }
    }
}