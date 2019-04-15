package com.usts.xuexiangyu.dao;

import com.usts.xuexiangyu.pojo.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public interface DataRepository extends JpaRepository<Data,Integer> {

}
