package com.zzw.utils;

import java.sql.*;

/**
 * Created by ZZW on 2017/3/26.
 */
public class DBManager {
    // 数据库连接常量
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String USER = "root";
    public static final String PASS = "zzw123456";
    public static final String URL = "jdbc:mysql://localhost:3306/cloudserver";
    // 静态成员，支持单态模式
    private static DBManager per = null;
    private Connection conn = null;
    private Statement stmt = null;
// 单态模式-懒汉模式

    private DBManager() {
    }

    public static DBManager  createInstance() {
        if (per == null) {
            per = new DBManager();
            per.initDB();
        }
        return per;
    }

    // 加载驱动
    public void initDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("驱动加载成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 连接数据库，获取句柄+对象
    public void connectDB() {
        System.out.println("连接数据库...");
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("SqlManager:链接数据库成功...");
    }

    // 关闭数据库 关闭对象，释放句柄
    public void closeDB() {
        System.out.println("关闭数据库连接..");
        try {
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("成功关闭数据库连接");
    }

    // 查询
    public ResultSet executeQuery(String sql) {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    // 增添/删除/修改
    public int executeUpdate(String sql) {
        int ret = 0;
        try {
            ret = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    //注册
    public PreparedStatement executeRegister(String sql) throws SQLException {
            return  conn.prepareStatement(sql);
    }
}