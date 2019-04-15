package com.usts.xuexiangyu.service.serviceImpl;

import com.usts.xuexiangyu.dao.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.usts.xuexiangyu.pojo.Data;
import com.usts.xuexiangyu.service.DataService;

import java.util.List;

@Service("dataService")
public class DataServiceImpl implements DataService {
    @Autowired
    DataRepository dataRepository;


    @Override
    public void addData(Data d) {
        this.dataRepository.save(d);
    }

    @Override
    public void delData(Data d) {
        this.dataRepository.delete(d);
    }

    @Override
    public void updateData(Data d) {
        this.dataRepository.save(d);
    }

    @Override
    public List<Data> listData() {
        List<Data> list = this.dataRepository.findAll();
        return list;
    }
}