package com.usts.xuexiangyu.controller;

import com.usts.xuexiangyu.pojo.*;
import com.usts.xuexiangyu.service.CabinetsService;
import com.usts.xuexiangyu.service.DataService;
import com.usts.xuexiangyu.service.UserService;
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
    @Autowired
    UserService userService;
    boolean isLogin = false;

    //增加用户，从前端获取用户姓名和密码
    //前端测试地址为：http://localhost:8080/updateUser?name=zhouha&pwd=145236

    @RequestMapping("/addCabinets")
    public @ResponseBody
    Map<String, Object> addCabinets(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
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
        map.put("result", "success");
        map.put("respDisc", "添加成功");
        return map;
    }

    //删除用户，从前端获取用户的id
    //前端测试地址为：http://localhost:8080/delUser?id=11
    @RequestMapping("/delCabinets")
    public @ResponseBody
    Map<String, Object> delCabinets(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        int id = Integer.parseInt(request.getParameter("id"));
        //将获取的用户id封装在user对象里，然后传入service层
        Cabinets c = new Cabinets();
        c.setcId(id);
        cabinetsService.delCabinets(c);
        map.put("result", "success");
        map.put("respDisc", "删除成功");
        return map;
    }

    //修改用户，从前端获取用户的id，姓名，密码
    //前端测试地址为：http://localhost:8080/updateUser?id=11&name=zhouha&pwd=145236
    //注意：没有加密码为空的判断
    @RequestMapping("/updateCabinets")
    public @ResponseBody
    Map<String, Object> updateCabinets(HttpServletRequest request) {
        //获取用户id，用户名和密码，通过HttpServletRequest实现
        Map<String, Object> map = new HashMap<String, Object>();
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
        map.put("result", "success");
        map.put("respDisc", "修改成功");
        return map;
    }
    //显示所有柜子
    //前端测试地址为：http://localhost:8080/listUser
    //注意：此功能先不要写，需要登录,但是可以先测试一下看看效果

    @RequestMapping("/listCab")
    public @ResponseBody
    Map<String, Object> listCab(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

            List<Cabinets> list = cabinetsService.listCabinets();
            List<CabVO> cabVoList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++)
            {

                CabVO ck = new CabVO();
                Cabinets c = list.get(i);
                ck.setId(c.getcId());
                ck.setName(c.getcName());
                ck.setSite(c.getcSite());
                ck.setUid(c.getuId());
                cabVoList.add(ck);
            }

        map.put("code", 0);
        map.put("msg", "WWW");
        map.put("count", cabVoList.size());
        map.put("data", cabVoList);


        return map;
    }

}
    @RequestMapping("/listCabinets")
    public @ResponseBody
    Map<String, Object> listCabinets(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        Cookie[] cks = request.getCookies();
        for(Cookie ck : cks){
            if(ck.getValue().equals("1")){
                this.isLogin = true;
                break;
            }
        }
        //如果没登录，提示没登录
        if (!this.isLogin) {
            map.put("respCode", 1);
            map.put("respDesc", "您尚未登录，请先登录！");
            return map;
        } else {
            List<Cabinets> list = cabinetsService.listCabinets();
            List<CabinetsVO> cabinetsVoList = new ArrayList<>();

            for (int i = 0; i < list.size(); i++) {
                CabinetsVO cv = new CabinetsVO();
                Cabinets c = list.get(i);
                cv.setId(c.getcId());
                cv.setName(c.getcName());
                cv.setSite(c.getcSite());
                cv.setUid(c.getuId());
                //data,湿度 温度 时间的设置  需要dataService添加一个方法
                //这个方法主要用来传入柜子的id，然后获得这个柜子的所有data
                //获取这个柜子的所有data后，比较时间，找到最新时间点的数据
                //通过调用cv.set。。。方法，设置到视图层对象里
                List<Data> dataList = dataService.listData();
                int size = dataList.size();
                for (int k = size - 1; k >= 0; k--) {
                    Data kkkk = dataList.get(k);
                    if (!(kkkk.getcId() == c.getcId())) {
                        dataList.remove(k);
                    }
                }
                if (dataList.size() == 0) {
                    continue;
                }
                Data data = this.compareTime(dataList);
                cv.setHum(data.getdHum());
                cv.setTem(data.getdTem());
                cv.setTime(data.getdTime());
//下面是获取柜子所属的用户名
                List<Users> usersList = userService.listUsers();
                for (int m = 0; m < usersList.size(); m++) {
                    if (usersList.get(m).getuId() == c.getuId()) {
                        cv.setAdmin(usersList.get(m).getuName());
                    }
                }
                cabinetsVoList.add(cv);
            }
            map.put("code", 0);
            map.put("msg", "shabi");
            map.put("count", cabinetsVoList.size());
            map.put("data", cabinetsVoList);
            return map;
        }
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

























