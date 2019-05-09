package com.usts.xuexiangyu.service.serviceImpl;


import com.usts.xuexiangyu.dao.DataRepository;
import com.usts.xuexiangyu.dao.WarningRepository;
import com.usts.xuexiangyu.pojo.Users;
import com.usts.xuexiangyu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.usts.xuexiangyu.pojo.Warning;
import com.usts.xuexiangyu.service.WarningService;

import java.util.List;

@Service("warningService")
public class WarningServiceImpl implements WarningService {
    @Autowired
    WarningRepository warningRepository;
    @Autowired
    DataRepository dataRepository;




    @Override
    public void addWarning(Warning w) {
        this.warningRepository.save(w);
    }
    @Override
    public List<Warning> listWarning() {
        List<Warning> list = this.warningRepository.findAll();

        return list;
    }
}
