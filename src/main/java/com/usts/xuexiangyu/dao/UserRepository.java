package com.usts.xuexiangyu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import com.usts.xuexiangyu.pojo.Users;

@Component
public interface UserRepository extends JpaRepository<Users,String> {
}
