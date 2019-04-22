package com.usts.xuexiangyu.pojo;

public class DataVO {
    int id;
    String time;
    String tem;
    String hum;

    public DataVO() {
    }

    public DataVO(int id, String time, String tem, String hum) {
        this.id = id;
        this.time = time;
        this.tem = tem;
        this.hum = hum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
}
