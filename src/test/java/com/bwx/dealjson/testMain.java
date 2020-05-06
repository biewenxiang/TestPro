package com.bwx.dealjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import sun.rmi.runtime.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.*;

public class testMain {

    private static HashMap<String, Object> userInfo = new HashMap<>();

    static {
        userInfo.put("别文祥", "17611608621,17611608621@163.com");
        userInfo.put("赵凯", "18310437341,390313069@qq.com");
        userInfo.put("黄欣", "18513934338,18513934338@163.com");
        userInfo.put("鲁成", "15011250565,15011250565@163.com");
        userInfo.put("韩亚东", "15010223002,hanyd@weather.com.cn");
        userInfo.put("李伟峰", "15303993220,zhichen08@163.com");
    }

    public static String getString(String path) throws IOException {


        String str = "";
        StringBuilder stringBuilder = new StringBuilder();
        List<String> ss = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String strings = null;
        while ((strings = reader.readLine()) != null) {
            stringBuilder.append(strings);
//            ss.add(strings);
        }
        reader.close();
        str = stringBuilder.toString();
        return str;
    }


//    "业务名称":"天气网PC预报发布（IDC）",
//            "服务功能":"OCF接入模块",
//            "部署IP":"220.243.129.138",
//            "内网IP":"172.16.185.138",
//            "运行账户":"program",
//            "负责人":"李伟峰",
//            "监控进程":"无",
//            "监控端口":"无",
//            "监控API":"无",
//            "重启命令":null,
//            "备注":"文件监控调用，不定时运行"
//    [
//    {
//        "name":"业务名称",
//            "user":"运行用户",
//            "keyword":"进程匹配关键词",
//            "cmd":"启动脚本",
//            "port":"端口号",
//            "ip":"运行节点eth1 IP",
//            "watchmen":[{"name":"韩亚东","tel":"电话","email":"邮箱","dinggroup":"钉钉组"}],
//    }
//]

    @Test
    public void main2() throws IOException {
        String json = getString("C:\\Users\\admin\\Desktop\\sheet1.json");
        JSONObject jsonObject = JSON.parseObject(json);

        JSONArray jsonArray = jsonObject.getJSONArray("RECORDS");
        JSONArray jsonArray1 = new JSONArray();

        for (Object jo : jsonArray) {
            JSONObject one = (JSONObject) jo;
            JSONObject newone = new JSONObject();

            String name = one.getString("业务名称") + one.getString("服务功能");
            String user = one.getString("运行账户");
            String keyword = one.getString("监控进程");
            String cmd = one.getString("重启命令");
            String port = one.getString("监控端口");
            String ip = one.getString("内网IP");
            String yfname = one.getString("负责人");
            String watchmen = "";
            if (userInfo.get(yfname) != null) {
                String string = (String) userInfo.get(yfname);
                String string2[] = string.split(",");
                watchmen = "[{\"name\":\"" + yfname + "\",\"tel\":\"" + string2[0] + "\",\"email\":\"" + string2[1] + "\",\"dinggroup\":\"YF\"}]";
            }
            JSONArray watchobj = JSONArray.parseArray(watchmen);
            newone.put("name", name + "");
            newone.put("user", user + "");
            newone.put("keyword", keyword + "");
            newone.put("cmd", cmd + "");
            newone.put("port", port + "");
            newone.put("ip", ip + "");
            newone.put("watchmen", watchobj);
            jsonArray1.add(newone);
        }
        System.out.println(jsonArray1.toJSONString());


        System.out.println("sssssss");
    }

    @Test
    public void test1() {
        System.out.println(System.getProperty("user.dir"));
        long a = Long.MAX_VALUE - 9223370487875575807l;
        System.out.println(new Date(a));
        InetAddress inetAddress = null;


        System.out.println(getLocalIp());

        NetworkInterface networkInterface = null;
        try {
            networkInterface = NetworkInterface.getByName("eth1");
        } catch (SocketException e) {
            e.printStackTrace();
        }
        inetAddress = (InetAddress) networkInterface.getInetAddresses().nextElement();

    }

    private static String getLocalIp() {
        String sip = null;
        InetAddress ip = null;
        boolean bFindIP = false;
        Enumeration<NetworkInterface> netInterfaces = null;
        try {
            netInterfaces = (Enumeration<NetworkInterface>) NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        while (netInterfaces.hasMoreElements()) {
            if (bFindIP) {
                break;
            }
            NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
            // ----------特定情况，可以考虑用ni.getName判断
            // 遍历所有ip
            Enumeration<InetAddress> ips = ni.getInetAddresses();
            while (ips.hasMoreElements()) {
                ip = (InetAddress) ips.nextElement();
                if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() // 127.开头的都是lookback地址
                        && ip.getHostAddress().indexOf(":") == -1) {
                    bFindIP = true;
                    break;
                }
            }
        }
        if (ip != null) {
            sip = ip.getHostAddress();
        }
        return sip;

    }
}
