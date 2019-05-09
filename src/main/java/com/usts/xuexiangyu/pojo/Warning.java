package com.usts.xuexiangyu.pojo;
import javax.persistence.*;

@Entity
@Table(name = "warning")
public class Warning {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "w_id")
    private int wId;
    @Column(name = "w_hum")
    private String wHum ;
    @Column(name = "w_tem")
    private String wTem ;
    @Column(name = "w_time")
    private String wTime ;
    @Column(name = "c_id")
    private int cId;

    public Warning() {
    }

    public Warning(String wHum, String wTem, String wTime, int wId ,int cId) {
        this.wHum = wHum;
        this.wTem = wTem;
        this.wTime = wTime;
        this.wId = wId;
        this.cId = cId;
    }

    public int getwId() {
        return wId;
    }

    public void setwId(int wId) {
        this.wId = wId;
    }

    public String getwHum() {
        return wHum;
    }

    public void setwHum(String wHum) {
        this.wHum = wHum;
    }

    public String getwTem() {
        return wTem;
    }

    public void setwTem(String wTem) {
        this.wTem = wTem;
    }

    public String getwTime() {
        return wTime;
    }

    public void setwTime(String wTime) {
        this.wTime = wTime;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    @Override
    public String toString() {
        return "Warning{" +
                "wId='" + wId + '\'' +
                "wHum='" + wHum + '\'' +
                ", wTem=" + wTem +
                ", wTime='" + wTime + '\'' +
                ", cId='" + cId + '\'' +
                '}';
    }


}

