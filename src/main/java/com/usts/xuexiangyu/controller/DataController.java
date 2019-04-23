package com.usts.xuexiangyu.controller;

import com.usts.xuexiangyu.pojo.*;
import javafx.scene.chart.PieChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.usts.xuexiangyu.service.DataService;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class DataController {
    @Autowired
    DataService dataService;
    //增加用户，从前端获取用户姓名和密码
    //前端测试地址为：http://localhost:8080/updateUser?name=zhouha&pwd=145236

    @RequestMapping("/addData")
    public String addUser(HttpServletRequest request){
        //获取用户姓名和密码，通过HttpServletRequest实现
        HttpSession session = request.getSession();
        int cid = Integer.parseInt(request.getParameter("cid"));
        String tem = request.getParameter("tem");
        String hum = request.getParameter("hum");
        String time = request.getParameter("time");
        //将获取的用户姓名和密码封装在user对象里，然后传入service层
        Data d = new Data();
        d.setcid(cid);
        d.setdTem(tem);
        d.setdHum(hum);
        d.setdTime(time);
        dataService.addData(d);
        return "success.html";
    }

    //删除用户，从前端获取用户的id
    //前端测试地址为：http://localhost:8080/delUser?id=11
    @RequestMapping("/delData")
    public String delUser(HttpServletRequest request){
        //获取用户id，通过HttpServletRequest实现
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        //将获取的用户id封装在user对象里，然后传入service层
        Data d = new Data();
        d.setdId(id);
        dataService.delData(d);
        return "success.html";
    }

    //修改用户，从前端获取用户的id，姓名，密码
    //前端测试地址为：http://localhost:8080/updateUser?id=11&name=zhouha&pwd=145236
    //注意：没有加密码为空的判断
    @RequestMapping("/updateData")
    public String updateUser(HttpServletRequest request){
        //获取用户id，用户名和密码，通过HttpServletRequest实现
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        int cid = Integer.parseInt(request.getParameter("cid"));
        String tem = request.getParameter("tem");
        String hum = request.getParameter("hum");
        String time = request.getParameter("time");
        //将获取的用户id，用户名和密码封装在user对象里，然后传入service层
        Data d = new Data();
        d.setdId(id);
        d.setcid(cid);
        d.setdTem(tem);
        d.setdHum(hum);
        d.setdTime(time);
        dataService.updateData(d);
        return "success.html";
    }

    //显示所有用户
    //前端测试地址为：http://localhost:8080/listUser
    //注意：此功能先不要写，需要登录,但是可以先测试一下看看效果
    @RequestMapping("/listData")
    public @ResponseBody
    Map<String, Object> listData(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println(request.getCookies().length);
        //如果没登录，提示没登录
       if (request.getCookies().length < 2) {
            map.put("respCode", 1);
            map.put("respDesc", "您尚未登录，请先登录！");
            return map;
        } else {
            String id = request.getParameter("cId");
            List<Data> list = dataService.listData();
            List<DataVO> dataVoList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getcId() == Integer.parseInt(id)) {
                    DataVO dk = new DataVO();
                    Data d = list.get(i);
                    dk.setId(d.getcId());
                    dk.setTime(d.getdTime());
                    dk.setHum(d.getdHum());
                    dk.setTem(d.getdTem());
                    dataVoList.add(dk);
                }
            }
            map.put("code", 0);
            map.put("msg", "WWW");
            map.put("count", dataVoList.size());
            map.put("data", dataVoList);


            return map;
        }
    }

}






