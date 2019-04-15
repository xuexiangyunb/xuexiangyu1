package com.usts.xuexiangyu.pojo;


import javax.persistence.*;

@Entity
@Table(name = "cabinets")
public class Cabinets {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "c_id")
    private int cId ;
    @Column(name = "c_name")
    private String cName;
    @Column(name = "c_site")
    private int cSite;
    @Column(name = "u_id")
    private int uId;



    public Cabinets() {
    }

    public Cabinets(int cId, String cName, int cSite, int uId) {
        this.cId = cId;
        this.cName = cName;
        this.cSite = cSite;
        this.uId = uId;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public int getcSite() {
        return cSite;
    }

    public void setcSite(int cSite) {
        this.cSite = cSite;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    @Override
    public String toString() {
        return "Cabinets{" +
                "cId='" + cId + '\'' +
                ", cName='" + cName + '\'' +
                ", cSite=" + cSite +
                ", uId='" + uId + '\'' +
                '}';
    }




    public void setcid(int id) {this.cId =id;
    }

    public void setuid(int uid) {this.uId =uid;
    }
}
