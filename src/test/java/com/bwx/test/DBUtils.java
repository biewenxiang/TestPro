package com.bwx.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
    private  static Connection conn = null;
    static {
        init();
    }
    public static synchronized void init() {
        // 不同的数据库有不同的驱动
        String driverName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test2?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&rewriteBatchedStatements=true";
        String user = "root";
        String password = "123456";
        try {
            // 加载驱动
            Class.forName( driverName);
            conn = DriverManager.getConnection( url, user, password);
            // 开始连接数据库
            System.out.println("--数据库连接成功--");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConn(){
        if (conn==null){
            init();
        }
        return conn;
    }

}
