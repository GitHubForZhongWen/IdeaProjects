package com.zzw.bean;

import java.sql.Timestamp;

/**
 * Created by ZZW on 2017/3/31.
 */
public class PrivateUserInfo {
    String dataID;//文件数据路径id
    String realName;//文件真名
    String uuidName;//文件UUID名
    String savePath;//文件保存路径
    Timestamp uploadTime;//上传时间

    public PrivateUserInfo(String dataID, String realName, String uuidName, String savePath, Timestamp uploadTime) {
        this.dataID = dataID;
        this.realName = realName;
        this.uuidName = uuidName;
        this.savePath = savePath;
        this.uploadTime = uploadTime;
    }

    public String getDataID() {
        return dataID;
    }

    public void setDataID(String dataID) {
        this.dataID = dataID;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUuidName() {
        return uuidName;
    }

    public void setUuidName(String uuidName) {
        this.uuidName = uuidName;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public Timestamp getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Timestamp uploadTime) {
        this.uploadTime = uploadTime;
    }
}
