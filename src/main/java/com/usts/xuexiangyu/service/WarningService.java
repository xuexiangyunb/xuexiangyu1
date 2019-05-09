package com.usts.xuexiangyu.service;
import com.usts.xuexiangyu.pojo.Data;
import com.usts.xuexiangyu.pojo.Warning;

import java.util.List;


public interface WarningService {
    public void addWarning(Warning u);
    public List<Warning> listWarning();
}
