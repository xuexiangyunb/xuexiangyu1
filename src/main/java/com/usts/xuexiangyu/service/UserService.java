package com.usts.xuexiangyu.service;

import com.usts.xuexiangyu.pojo.Users;

import java.util.List;
import java.util.Map;

public interface UserService {
    public void addUser(Users u);
    public void delUser(Users u);
    public void updateUser(Users u);
    public List<Users> listUsers();
    public Map<String,Integer> login(Users u);
}
