package com.usts.xuexiangyu.controller;

import com.usts.xuexiangyu.pojo.Cabinets;
import com.usts.xuexiangyu.pojo.Users;
import com.usts.xuexiangyu.service.CabinetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.usts.xuexiangyu.service.CabinetsService;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class CabinetsController {
    @Autowired
    CabinetsService cabinetsService;
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

    //显示所有用户
    //前端测试地址为：http://localhost:8080/listUser
    //注意：此功能先不要写，需要登录,但是可以先测试一下看看效果
    @RequestMapping("/listCabinets")
    public @ResponseBody
    Map<String, Object> listCabinets(){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Cabinets> list = cabinetsService.listCabinets();
        map.put("cabinetsList",list);
        return map;
    }






}
