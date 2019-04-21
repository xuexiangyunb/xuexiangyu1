package com.usts.xuexiangyu.service.serviceImpl;

import com.usts.xuexiangyu.dao.CabinetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.usts.xuexiangyu.pojo.Cabinets;
import com.usts.xuexiangyu.service.CabinetsService;

import java.util.List;

@Service("cabinetsService")
public class CabinetsServiceImpl implements CabinetsService {
    @Autowired
    CabinetsRepository cabinetsRepository;


    @Override
    public void addCabinets(Cabinets c) {
        this.cabinetsRepository.save(c);
    }

    @Override
    public void delCabinets(Cabinets c) {
        this.cabinetsRepository.delete(c);
    }

    @Override
    public void updateCabinets(Cabinets c) {
        this.cabinetsRepository.save(c);
    }

    @Override
    public List<Cabinets> listCabinets() {
        List<Cabinets> list = this.cabinetsRepository.findAll();
        return list;
    }

}