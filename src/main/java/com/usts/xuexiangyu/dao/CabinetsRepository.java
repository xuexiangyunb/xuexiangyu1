package com.usts.xuexiangyu.dao;

import com.usts.xuexiangyu.pojo.Cabinets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface CabinetsRepository extends JpaRepository<Cabinets,Integer> {
}
