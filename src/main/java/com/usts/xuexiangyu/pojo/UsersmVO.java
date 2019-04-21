package com.usts.xuexiangyu.pojo;

public class UsersmVO {
    int id;
    String name;
    String pwd;
    int role;

    public UsersmVO() {
    }

    public UsersmVO(int id,  String name, String pwd,int role) {
        this.id = id;
        this.name = name;
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
