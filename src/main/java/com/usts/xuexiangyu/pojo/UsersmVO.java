package com.usts.xuexiangyu.pojo;

public class UsersmVO {
    int id;
    String name;
    String pwd;
    int role;
    String age;
    String sex;

    public UsersmVO() {
    }

    public UsersmVO(int id,  String name, String pwd,int role,String age,String sex) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.role = role;
        this.age = age;
        this.sex = sex;



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

    public String getAge() {
        return age;
    }

    public void setAge(String Age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String Sex) {
        this.sex = sex;
    }


}
