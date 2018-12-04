package com.bwx.test;


import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MysqlTest {

    Connection conn = null;

    public void init() {
        // 不同的数据库有不同的驱动
        String driverName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test2?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&rewriteBatchedStatements=true";
        String user = "root";
        String password = "123456";

        try {
            // 加载驱动
            Class.forName(driverName);
            // 设置 配置数据
            // 1.url(数据看服务器的ip地址 数据库服务端口号 数据库实例)
            // 2.user
            // 3.password
            conn = DriverManager.getConnection(url, user, password);
            // 开始连接数据库
            System.out.println("数据库连接成功..");
        } catch (ClassNotFoundException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }

    }

    @Test
    public void testdb() throws SQLException {

        init();

        List<String> list = new ArrayList<>();
        // *查询
        // 创建sql语句
        String sqlString = "select * from station_user_copy limit 0,1000";
        // 编译sql语句
        // 执行查询
        PreparedStatement pst = conn.prepareStatement(sqlString);
        ResultSet rSet = pst.executeQuery();
        // 遍历结果
        while (rSet.next()) {
            System.out.print(rSet.getString(1) + "\t");
            System.out.print(rSet.getString(2) + "\t");
            System.out.print("\n");
        }
    }

    @Test
    public void testinsert_db() throws SQLException {


        long start = System.currentTimeMillis();
        init();
        List<String> list = new ArrayList<>();
        // *查询
        // 创建sql语句
        String sqlString = "select * from station_user_copy limit 0,1000";
        // 编译sql语句
        // 执行查询
        PreparedStatement pst = conn.prepareStatement(sqlString);
        ResultSet rSet = pst.executeQuery();
        // 遍历结果
        conn.setAutoCommit(false); //设置手动提交
        //预编译sql对象,只编译一回

        PreparedStatement ps = (PreparedStatement) conn.prepareStatement("INSERT INTO `test2`.`station_user_copy` (`stationid`, `userid`) VALUES (?, ?)");
        for (int i = 0; i < 1000000; i++) {
            ps.setString(1, i + "id");
            ps.setString(2, i + "name");

            ps.addBatch();//添加到批次
        }
        ps.executeBatch();//提交批处理
        conn.commit();//执行
        conn.close();


        System.out.println(System.currentTimeMillis() - start);
    }

    @Test
    public void testinsert_db2() throws SQLException {
        init();
        long starttime = System.currentTimeMillis();
        conn.setAutoCommit(false); //设置手动提交
        //预编译sql对象,只编译一回
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement("INSERT INTO `test2`.`station_user_copy` (`stationid`, `userid`) VALUES (?, ?)");
        int a = 2000;
        int count = 0;
        for (int i = 0; i < 100000; i++) {
            String uuid = UUID.randomUUID().toString();
            System.out.println(uuid);
            ps.setString(1, uuid);
            ps.setString(2, i + "name");
            ps.addBatch();//添加到批次
            if (i != 0 && i % a == 0) {
                ps.executeBatch();//提交批处理
//                conn.commit();//执行
                System.out.println(i);
                ps.clearBatch();
            }
        }
        ps.executeBatch();//提交批处理
        conn.commit();//执行
        conn.rollback();
        System.out.println("");
        System.out.println(System.currentTimeMillis() - starttime);
        ps.close();
        conn.close();

    }

    @Test
    public void testinsert_db3() throws SQLException {
        init();
        long start = System.currentTimeMillis();
        //预编译sql对象,只编译一回
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement("INSERT INTO `test2`.`station_user_copy` (`stationid`, `userid`) VALUES (?, ?)");
        for (int i = 0; i < 1000000; i++) {
            ps.setString(1, i + "id");
            ps.setString(2, i + "name");
            ps.addBatch();//添加到同一个批处理中
            if (i != 0 && i % 10 == 0) {
                //int[] aa = ps.executeBatch();//提交批处理
                System.out.println(i);
            }
        }
        ps.executeBatch();
        System.out.println("");
        System.out.println(System.currentTimeMillis() - start);
        ps.close();
        conn.close();

    }


}
