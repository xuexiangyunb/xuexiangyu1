package com.usts.xuexiangyu.pojo;

import javax.persistence.*;

@Entity
@Table(name = "data")
public class Data {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "d_id")
    private int dId;
    @Column(name = "d_hum")
    private String dHum ;
    @Column(name = "d_tem")
    private String dTem ;
    @Column(name = "d_time")
    private String dTime ;
    @Column(name = "c_id")
    private int cId;

    public Data() {
    }

    public Data(String dHum, String dTem, String dTime, int cId ,int dId) {
        this.dHum = dHum;
        this.dTem = dTem;
        this.dTime = dTime;
        this.cId = cId;
        this.dId = dId;
    }

    public String getdHum() {
        return dHum;
    }

    public void setdHum(String dHum) {
        this.dHum = dHum;
    }

    public String getdTem() {
        return dTem;
    }

    public void setdTem(String dTem) {
        this.dTem = dTem;
    }

    public String getdTime() {
        return dTime;
    }

    public void setdTime(String dTime) {
        this.dTime = dTime;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) { this.cId = cId; }

    public void setdId(int dId) { this.dId = dId; }

    @Override
    public String toString() {
        return "Data{" +
                "dId='" + dId + '\'' +
                "dHum='" + dHum + '\'' +
                ", dTem=" + dTem +
                ", dTime='" + dTime + '\'' +
                ", cId='" + cId + '\'' +
                '}';
    }

    public void setcid(int cid) {this.cId = cid ;
    }
}
