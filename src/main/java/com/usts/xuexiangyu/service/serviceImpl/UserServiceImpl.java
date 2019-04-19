package com.usts.xuexiangyu.service.serviceImpl;

import com.usts.xuexiangyu.dao.CabinetsRepository;
import com.usts.xuexiangyu.dao.DataRepository;
import com.usts.xuexiangyu.dao.UserRepository;
import com.usts.xuexiangyu.pojo.Cabinets;
import com.usts.xuexiangyu.pojo.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import com.usts.xuexiangyu.pojo.Users;
import com.usts.xuexiangyu.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CabinetsRepository cabinetsRepository;
    @Autowired
    DataRepository dataRepository;


    @Override
    public void addUser(Users u) {
        this.userRepository.save(u);
    }

    @Override
    public void delUser(Users u) {
        this.userRepository.delete(u);
    }

    @Override
    public void updateUser(Users u) {
        this.userRepository.save(u);
    }

    @Override
    public List<Users> listUsers() {
        List<Users> list = this.userRepository.findAll();
        return list;
    }

    @Override
    public Map<String, Integer> login(Users u) {
        Map<String,Integer> map = new HashMap<>();
        map.put("role",3);

        List<Users> list = userRepository.findAll();
        List<Cabinets> cabinetsList = cabinetsRepository.findAll();
        List<Data> dataList = dataRepository.findAll();
        for(int i = 0;i<list.size();i++){
            Users user = list.get(i);
            System.out.println(user);
            int cid = 0;
            if(user.getuName().equals(u.getuName()) && user.getuPwd().equals(u.getuPwd())){
                //普通用户，返回管理的柜子的数据
                if(user.getuRole() == 2){
                    /*//获取这个用户的柜子的id
                    for(int k = 0;k<cabinetsList.size();k++){
                        if(cabinetsList.get(k).getuId() == user.getuId()){
                            cid = cabinetsList.get(k).getcId();
                        }
                    }
                    //返回这个柜子的所有数据
                    for(int j = 0 ;j<dataList.size();j++){
                        if(dataList.get(j).getcId() != cid){
                            dataList.remove(j);
                        }
                    }*/
                    map.put("role",2);

                }else{
                    //管理员，返回所有柜子
                    map.put("role",1);
                }
            }
        }
        //查找不到，返回的是空值，前端做判断
        return map;
    }
}
