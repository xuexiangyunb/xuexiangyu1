package com.usts.xuexiangyu.pojo;

public class WarningVO {
    int id;
    String tem;
    String hum;
    String time;

    public WarningVO() {
    }

    public WarningVO(int id, String time, String tem, String hum) {
        this.id = id;
        this.tem = tem;
        this.hum = hum;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTem() {
        return tem;
    }

    public void setTem(String tem) {
        this.tem = tem;
    }

    public String getHum() {
        return hum;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
