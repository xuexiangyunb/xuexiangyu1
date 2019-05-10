package com.usts.xuexiangyu.controller;

import com.usts.xuexiangyu.pojo.*;
import com.usts.xuexiangyu.service.CabinetsService;
import com.usts.xuexiangyu.service.DataService;
import com.usts.xuexiangyu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.usts.xuexiangyu.service.CabinetsService;
import com.usts.xuexiangyu.service.WarningService;
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
public class WarningController {
    boolean isLogin = false;
    @Autowired
    DataService dataService;
    @Autowired
    CabinetsService cabinetsService;
    @Autowired
    UserService userService;
    @Autowired
    WarningService warningService;

    @RequestMapping("/addWarning")
    public String addWarning(HttpServletRequest request) {
        System.out.println("增加异常信息");
        //获取用户姓名和密码，通过HttpServletRequest实现
        HttpSession session = request.getSession();


        /*下边这个cid是通过cookie获取用户名，然后通过用户名获取对应柜子的id
         * cid的值确定之后，就为什么问题了
         * */
        //int cid = Integer.parseInt(request.getParameter("cid"));


        String tem = request.getParameter("tem");
        String hum = request.getParameter("hum");
        String time = request.getParameter("time");
        //将获取的用户姓名和密码封装在user对象里，然后传入service层
        Warning w = new Warning();
        w.setwTem(tem);
        w.setwHum(hum);
        w.setwTime(time);
        warningService.addWarning(w);
        return "success.html";
    }

    @RequestMapping("/listWarning")
    public @ResponseBody
    Map<String, Object> listWarning(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
            String ckName = "";
            Cookie[] c = request.getCookies();
            for (Cookie ck : c) {
                if (ck.getName().equals("name")) {
                    ckName = ck.getValue();
                }
            }
            List<Users> usersList = userService.listUsers();
            List<Cabinets> cabinetslist = cabinetsService.listCabinets();
            List<Warning> list = warningService.listWarning();
            List<WarningVO> warningVoList = new ArrayList<>();
            List<String> humList = new ArrayList<>();//湿度容器
            List<String> temList = new ArrayList<>();//温度容器
            List<String> timeList = new ArrayList<>();
            int uId = -1;
            int cId = -1;
            String hum = "";
            String tem = "";
            String time = "";
            List<Warning> warningList = new ArrayList<>();
            for (int m = 0; m < list.size(); m++)
            {

                    WarningVO wk = new WarningVO();
                    Warning w = list.get(m);
                    wk.setId(w.getwId());
                    wk.setTem(w.getwTem());
                    wk.setHum(w.getwHum());
                    wk.setTime(w.getwTime());


                for (int t = 0; t < usersList.size(); t++) {
                    if (usersList.get(t).getuName().equals(ckName)) {
                        uId = usersList.get(t).getuId();

                    }
                }
                for (int k = 0; k < cabinetslist.size(); k++) {
                    if (cabinetslist.get(k).getuId() == uId) {
                        wk.setSite(cabinetslist.get(k).getcSite());
                        warningVoList.add(wk);
                    }

                }

            }

            map.put("code", 0);
            map.put("msg", "WWW");
            map.put("count", warningVoList.size());
            map.put("data", warningVoList);


            return map;
        }
    }


}
