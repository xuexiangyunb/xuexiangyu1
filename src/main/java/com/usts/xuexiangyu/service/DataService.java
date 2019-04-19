package com.usts.xuexiangyu.service;

import com.usts.xuexiangyu.pojo.Cabinets;
import com.usts.xuexiangyu.pojo.Data;

import java.util.List;

public interface DataService {
    public void addData(Data u);
    public void delData(Data u);
    public void updateData(Data u);
    public List<Data> listData();


}
