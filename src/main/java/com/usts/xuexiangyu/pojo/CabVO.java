package com.usts.xuexiangyu.pojo;

public class CabVO {
    int id;
    int site;
    String name;
    int uid;

    public CabVO() {
    }

    public CabVO(int id, int uid, int site, String name) {
        this.id = id;
        this.uid = uid;
        this.site = site;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

}
