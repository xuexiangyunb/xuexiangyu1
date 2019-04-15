package com.usts.xuexiangyu.service;

import com.usts.xuexiangyu.pojo.Cabinets;

import java.util.List;

public interface CabinetsService {
    public void addCabinets(Cabinets u);
    public void delCabinets(Cabinets u);
    public void updateCabinets(Cabinets u);
    public List<Cabinets> listCabinets();
}
