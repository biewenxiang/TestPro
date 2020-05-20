package com.bwx.concurrent;

import org.apache.commons.math3.analysis.function.Add;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class JavaCollectiontest {



    public static void main(String[] agrs){
        testUUid();
    }

//    @Test
    public static void testUUid() {
        CountDownLatch countDownLatch = new CountDownLatch(100);
        Set<String> strings = new HashSet<>();
        Hashtable<String,String> table = new Hashtable<>();
        Vector<String> vector = new Vector<>();
        ArrayList<String> strings1 = new ArrayList<>();
        Map map = new ConcurrentHashMap();
        Map map2 = new HashMap();

        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
//                    Connection connection = DBUtils.getConn();
                    List<String> aa = new ArrayList<>();
                    for (int i1 = 0; i1 < 10000; i1++) {
                        String uuid = UUID.randomUUID().toString();
                        System.out.println(uuid);
                        aa.add(uuid);
                        table.put(uuid,uuid);
                        strings.add(uuid);
                        vector.add(uuid);
//                        strings1.add(uuid);
                        add(strings1,uuid);
                        map.put(uuid,uuid);
                        map2.put(uuid,uuid);
                    }
                    // 编译sql语句
                    // 执行查询
//                    try {
//                        // 遍历结果
//                        try {
//                            connection.setAutoCommit(false); //设置手动提交
//                        } catch (SQLException e1) {
//                            e1.printStackTrace();
//                        }
//                        //预编译sql对象,只编译一回
//                        PreparedStatement ps = (PreparedStatement) connection.prepareStatement("INSERT INTO `test2`.`station_user_copy` (`stationid`, `userid`) VALUES (?, ?)");
//                        for (int i = 0; i < aa.size(); i++) {
//                            ps.setString(1, aa.get(i));
//                            ps.setString(2, i + "name");
//
//                            ps.addBatch();//添加到批次
//                        }
//                        ps.executeBatch();//提交批处理
//                        connection.commit();//执行
//                        connection.close();
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
                    countDownLatch.countDown();
                }
            }).start();

        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        strings.size();
        System.out.println("end");
    }

    public static synchronized void add(List aa,String aas){
        aa.add(aas);

    }
}