package com.zzw.dao;

import com.zzw.bean.UserInfo;
import com.zzw.utils.DBManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 用来连接和操作数据库中的数据
 * Created by ZZW on 2017/3/26.
 */
public class ServiceDao {

    //登录数据判断
    public Boolean login(String username, String password) {
        // 获取Sql查询语句
        String logSql = "select * from user where user_name ='" + username
                + "' and user_pass ='" + password + "'";
        // 获取DB对象
        DBManager sql = DBManager.createInstance();
        sql.connectDB();
        // 操作DB对象
        try {
            ResultSet rs = sql.executeQuery(logSql);
            if (rs.next()) {
                sql.closeDB();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql.closeDB();
        return false;
    }

    public Boolean register(UserInfo userInfo) {
        // 获取Sql查询语句
//        String regSql = "insert into user values('" + username + "','" + password + "') ";
        String regSql = "insert into user(user_name,user_pass,user_sex,user_phone,user_regtime,user_uuid) values(?,?,?,?,?,?)";

        // 获取DB对象
        DBManager sql = DBManager.createInstance();
        sql.connectDB();
        try {
            PreparedStatement prep = sql.executeRegister(regSql);
            prep.setString(1, userInfo.getUserName());
            prep.setString(2, userInfo.getUserPswd());
            prep.setString(3, userInfo.getSex());
            prep.setString(4, userInfo.getUserPhone());
            prep.setTimestamp(5, userInfo.getUserRegtime());
            prep.setString(6, userInfo.getUuidName());
            System.out.println("sexx"+userInfo.getSex());
            prep.executeUpdate();//执行插入语句
            System.out.println("插入完成");
            return true;//执行完成
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*int ret = sql.executeUpdate(regSql);
        if (ret != 0) {
            sql.closeDB();
            return true;
        }*/
        sql.closeDB();
        return false;
    }
}
