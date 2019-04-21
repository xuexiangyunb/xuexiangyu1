package com.usts.xuexiangyu.pojo;

public class UsersVO {
    int id;
    String name;
    String time;
    String pwd;
    int role;

    public UsersVO() {
    }

    public UsersVO(int id,  String name, String time,String pwd,int role) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.pwd = pwd;
        this.role = role;


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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

}
