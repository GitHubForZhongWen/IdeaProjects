package com.zzw.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 用户表，用来存放所有用户的基本信息
 * Created by ZZW on 2017/3/29.
 */

public class UserInfo implements Serializable {
    private String userName;//用户名
    private String userPswd;//用户密码
    private String sex;//用户性别
    private String userPhone;//用户手机号码
    private String uuidName;//用户空间唯一的名称
    private Timestamp userRegtime;//用户注册的时间

    public UserInfo(String userName, String userPswd, String sex, String userPhone, Timestamp userRegtime,String uuidName) {
        this.userName = userName;
        this.userPswd = userPswd;
        this.sex = sex;
        this.userPhone = userPhone;
        this.userRegtime = userRegtime;
        this.uuidName = uuidName;
    }

    public String getUuidName() {
        return uuidName;
    }

    public void setUuidName(String uuidName) {
        this.uuidName = uuidName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPswd() {
        return userPswd;
    }

    public void setUserPswd(String userPswd) {
        this.userPswd = userPswd;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Timestamp getUserRegtime() {
        return userRegtime;
    }

    public void setUserRegtime(Timestamp userRegtime) {
        this.userRegtime = userRegtime;
    }
}
