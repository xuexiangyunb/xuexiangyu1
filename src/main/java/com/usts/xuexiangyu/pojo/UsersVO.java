package com.usts.xuexiangyu.pojo;

public class UsersVO {
    int id;
    String name;
    String time;

    public UsersVO() {
    }

    public UsersVO(int id,  String name, String time) {
        this.id = id;
        this.name = name;
        this.time = time;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

}
