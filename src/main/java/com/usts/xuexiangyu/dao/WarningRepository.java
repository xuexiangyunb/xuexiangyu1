package com.usts.xuexiangyu.dao;

import com.usts.xuexiangyu.pojo.Warning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface WarningRepository extends JpaRepository<Warning,String> {
}
