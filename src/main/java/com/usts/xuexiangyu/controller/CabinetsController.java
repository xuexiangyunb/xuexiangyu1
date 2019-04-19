package com.usts.xuexiangyu.controller;

import com.usts.xuexiangyu.pojo.Cabinets;
import com.usts.xuexiangyu.pojo.CabinetsVO;
import com.usts.xuexiangyu.pojo.Data;
import com.usts.xuexiangyu.pojo.Users;
import com.usts.xuexiangyu.service.CabinetsService;
import com.usts.xuexiangyu.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.usts.xuexiangyu.service.CabinetsService;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class CabinetsController {
    @Autowired
    CabinetsService cabinetsService;
    @Autowired
    DataService dataService;
    //增加用户，从前端获取用户姓名和密码
    //前端测试地址为：http://localhost:8080/updateUser?name=zhouha&pwd=145236

    @RequestMapping("/addCabinets")
    public String addCabinets(HttpServletRequest request){
        //获取用户姓名和密码，通过HttpServletRequest实现
        HttpSession session = request.getSession();
        String name = request.getParameter("name");
        int uid = Integer.parseInt(request.getParameter("uid"));
        int site = Integer.parseInt(request.getParameter("site"));
        //将获取的用户姓名和密码封装在user对象里，然后传入service层
        Cabinets c = new Cabinets();
        c.setuid(uid);
        c.setcName(name);
        c.setcSite(site);
        cabinetsService.addCabinets(c);
        return "success.html";
    }

    //删除用户，从前端获取用户的id
    //前端测试地址为：http://localhost:8080/delUser?id=11
    @RequestMapping("/delCabinets")
    public String delUser(HttpServletRequest request){
        //获取用户id，通过HttpServletRequest实现
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        //将获取的用户id封装在user对象里，然后传入service层
        Cabinets c = new Cabinets();
        c.setcId(id);
        cabinetsService.delCabinets(c);
        return "success.html";
    }

    //修改用户，从前端获取用户的id，姓名，密码
    //前端测试地址为：http://localhost:8080/updateUser?id=11&name=zhouha&pwd=145236
    //注意：没有加密码为空的判断
    @RequestMapping("/updateCabinets")
    public String updateUser(HttpServletRequest request){
        //获取用户id，用户名和密码，通过HttpServletRequest实现
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        int uid = Integer.parseInt(request.getParameter("uid"));
        String name = request.getParameter("name");
        int site = Integer.parseInt(request.getParameter("site"));
        //将获取的用户id，用户名和密码封装在user对象里，然后传入service层
        Cabinets c = new Cabinets();
        c.setcid(id);
        c.setuid(uid);
        c.setcName(name);
        c.setcSite(site);
        cabinetsService.updateCabinets(c);
        return "success.html";
    }

    //显示所有柜子
    //前端测试地址为：http://localhost:8080/listUser
    //注意：此功能先不要写，需要登录,但是可以先测试一下看看效果
    @RequestMapping("/listCabinets")
    public @ResponseBody
    Map<String, Object> listCabinets(HttpServletRequest request , HttpServletResponse response) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        //如果没登录，提示没登录
        if(request.getCookies().length < 2){
            map.put("respCode",1);
            map.put("respDesc","您尚未登录，请先登录！");
            return map;
        }else{
            List<Cabinets> list = cabinetsService.listCabinets();
            List<CabinetsVO> cabinetsVoList = new ArrayList<>();

            for(int i = 0;i<list.size();i++){
                CabinetsVO cv = new CabinetsVO();
                Cabinets c = list.get(i);
                cv.setId(c.getcId());
                cv.setName(c.getcName());
                cv.setSite(c.getcSite());
                //data,湿度 温度 时间的设置  需要dataService添加一个方法
                //这个方法主要用来传入柜子的id，然后获得这个柜子的所有data
                //获取这个柜子的所有data后，比较时间，找到最新时间点的数据
                //通过调用cv.set。。。方法，设置到视图层对象里
                List<Data> dataList = dataService.listData(这里就是那个方法);


                cabinetsVoList.add(cv);


            }
            map.put("data",cabinetsVoList);
            return map;
        }
    }







}
