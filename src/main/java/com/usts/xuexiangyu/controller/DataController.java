package com.usts.xuexiangyu.controller;

import com.usts.xuexiangyu.pojo.*;
import com.usts.xuexiangyu.service.CabinetsService;
import com.usts.xuexiangyu.service.UserService;
import javafx.scene.chart.PieChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.usts.xuexiangyu.service.DataService;
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
public class DataController {
    boolean isLogin = false;
    @Autowired
    DataService dataService;
    @Autowired
    CabinetsService cabinetsService;
    @Autowired
    UserService userService;
    //增加用户，从前端获取用户姓名和密码
    //前端测试地址为：http://localhost:8080/updateUser?name=zhouha&pwd=145236

    @RequestMapping("/addData")
    public String addUser(HttpServletRequest request) {
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
    public String delUser(HttpServletRequest request) {
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
    public String updateUser(HttpServletRequest request) {
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
        //如果没登录，提示没登录
        Cookie[] cks = request.getCookies();
        for (Cookie ck : cks) {
            if (ck.getValue().equals("1")) {
                this.isLogin = true;
                break;
            }
        }
        if (!this.isLogin) {
            map.put("respCode", 1);
            map.put("respDesc", "您尚未登录，请先登录！");
            return map;
        } else {
            String id = request.getParameter("cId");
            System.out.println(id);
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

    @RequestMapping("/listHumTem")
    public @ResponseBody
    Map<String, Object> listHumTem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        //如果没登录，提示没登录
        Cookie[] cks = request.getCookies();
        for (Cookie ck : cks) {
            if (ck.getValue().equals("1")) {
                this.isLogin = true;
                break;
            }
        }
        if (!this.isLogin) {
            map.put("respCode", 1);
            map.put("respDesc", "您尚未登录，请先登录！");
            return map;
        } else {
            String id = request.getParameter("cId");
            List<Data> list = dataService.listData();
            List<String> humList = new ArrayList<>();//湿度容器
            List<String> temList = new ArrayList<>();//温度容器
            List<String> timeList = new ArrayList<>();//时间容器
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getcId() == Integer.parseInt(id)) {
                    humList.add(list.get(i).getdHum());
                    temList.add(list.get(i).getdTem());
                    timeList.add(list.get(i).getdTime());
                }
            }
            map.put("humData", humList);
            map.put("temData", temList);
            map.put("timeData", timeList);
        }
        return map;
    }



    @RequestMapping("/listHT")
    public @ResponseBody
    Map<String, Object> listHT(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        //如果没登录，提示没登录
        Cookie[] cks = request.getCookies();
        for(Cookie ck : cks){
            if(ck.getValue().equals("1")){
                this.isLogin = true;
                break;
            }
        }
        if (!this.isLogin) {
            map.put("respCode", 1);
            map.put("respDesc", "您尚未登录，请先登录！");
            return map;
        } else {
            String ckName = "";
            Cookie[] c = request.getCookies();
            for(Cookie ck : c){
                if(ck.getName().equals("name")){
                    ckName = ck.getValue();
                }
            }
            List<Data> list = dataService.listData();
            List<Users> usersList = userService.listUsers();
            List<Cabinets> cabinetslist = cabinetsService.listCabinets();
            List<String> humList = new ArrayList<>();//湿度容器
            List<String> temList = new ArrayList<>();//温度容器
            List<String> timeList = new ArrayList<>();
            int uId=-1;
            int cId=-1;
            String hum="";
            String tem="";
            String time="";
            for (int t = 0; t < usersList.size(); t++) {
                if(usersList.get(t).getuName().equals(ckName)){
                   uId = usersList.get(t).getuId();
                   break;
                }
            }
            for(int k=0;k<cabinetslist.size();k++){
                if (cabinetslist.get(k).getuId()==uId){
                  cId=cabinetslist.get(k).getcId();
                  break;
                }

            }
            List<Data> dataList = new ArrayList<>();
            for (int m=0;m<list.size();m++){
                if (list.get(m).getcId()== cId){
                    dataList.add(list.get(m));
                }
            }
            System.out.println(dataList.size());
            Data data = compareTime(dataList);
            map.put("humData", data.getdHum());
            map.put("temData",data.getdTem());
            map.put("timeData",data.getdTime());
        }
        return map;
    }

    private Data compareTime(List<Data> dataList)
    {
        int year1;
        int year2;
        int month1;
        int month2;
        int day1;
        int day2;
        int hour1;
        int hour2;
        int minute1;
        int minute2;

        Data d = dataList.get(0);
        for (int i = 0; i < dataList.size() - 1; i++) {
            Data d1 = dataList.get(i);
            Data d2 = dataList.get(i + 1);
            year1 = Integer.parseInt(d1.getdTime().split(" ")[0].split("-")[0]);
            year2 = Integer.parseInt(d2.getdTime().split(" ")[0].split("-")[0]);
            month1 = Integer.parseInt(d1.getdTime().split(" ")[0].split("-")[1]);
            month2 = Integer.parseInt(d2.getdTime().split(" ")[0].split("-")[1]);
            day1 = Integer.parseInt(d1.getdTime().split(" ")[0].split("-")[2]);
            day2 = Integer.parseInt(d2.getdTime().split(" ")[0].split("-")[2]);
            hour1 = Integer.parseInt(d1.getdTime().split(" ")[1].split(":")[0]);
            hour2 = Integer.parseInt(d2.getdTime().split(" ")[1].split(":")[0]);
            minute1 = Integer.parseInt(d1.getdTime().split(" ")[1].split(":")[1]);
            minute2 = Integer.parseInt(d2.getdTime().split(" ")[1].split(":")[1]);

            if (year1 == year2) {
                if (month1 == month2) {
                    if (day1 == day2) {
                        if (hour1 == hour2) {
                            if (minute1 > minute2) {
                                d = dataList.get(i);
                            } else {
                                d = dataList.get(i + 1);
                            }

                        } else if (hour1 > hour2) {
                            d = dataList.get(i);
                        } else {
                            d = dataList.get(i + 1);
                        }
                    } else if (day1 > day2) {
                        d = dataList.get(i);
                    } else {
                        d = dataList.get(i + 1);
                    }
                } else if (month1 > month2) {
                    d = dataList.get(i);
                } else {
                    d = dataList.get(i + 1);
                }
            } else if (year1 > year2) {
                d = dataList.get(i);
            } else {
                d = dataList.get(i + 1);
            }
        }

        return d;
    }

}






