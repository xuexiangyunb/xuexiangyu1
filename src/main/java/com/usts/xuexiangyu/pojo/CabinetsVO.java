package com.usts.xuexiangyu.pojo;

public class CabinetsVO {
    int id;
    String admin;
    int site;
    String name;
    String time;
    String tem;
    String hum;

    public CabinetsVO() {
    }

    public CabinetsVO(int id, String admin, int site, String name, String time, String tem, String hum) {
        this.id = id;
        this.admin = admin;
        this.site = site;
        this.name = name;
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

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public int getSite() {
        return site;
    }

    public void setSite(int site) {
        this.site = site;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
