package com.usts.xuexiangyu.pojo;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "u_id")
    private int uId ;
    @Column(name = "u_name")
    private String uName ;
    @Column(name = "u_pwd")
    private String uPwd ;
    @Column(name = "u_role")
    private int uRole;
    @Column(name = "u_Time")
    private String uTime;
    @Column(name = "u_Age")
    private String uAge;
    @Column(name = "u_Sex")
    private String uSex;

    public Users() {
    }

    public Users(int uId, String uName, String uPwd, int uRole, String uTime,String uAge,String uSex) {
        this.uId = uId;
        this.uName = uName;
        this.uPwd = uPwd;
        this.uRole = uRole;
        this.uTime = uTime;
        this.uAge = uAge;
        this.uSex = uSex;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuPwd() {
        return uPwd;
    }

    public void setuPwd(String uPwd) {
        this.uPwd = uPwd;
    }

    public int getuRole() {
        return uRole;
    }

    public void setuRole(int uRole) {
        this.uRole = uRole;
    }

    public String getuTime() {
        return uTime;
    }

    public void setuTime(String uTime) {
        this.uTime = uTime;
    }

    public String getuAge() {
        return uAge;
    }

    public void setuAge(String uAge) {
        this.uAge = uAge;
    }

    public String getuSex() {
        return uSex;
    }

    public void setuSex(String uSex) {
        this.uSex = uSex;
    }

    @Override
    public String toString() {
        return "Users{" +
                "uId=" + uId +
                ", uName='" + uName + '\'' +
                ", uPwd='" + uPwd + '\'' +
                ", uRole=" + uRole +
                ", uTime='" + uTime + '\'' +
                ", uAge='" + uAge + '\'' +
                ", uSex='" + uSex + '\'' +
                '}';
    }
}
