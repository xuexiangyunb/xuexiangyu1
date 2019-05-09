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
        int cid = Integer.parseInt(request.getParameter("cid"));
        String tem = request.getParameter("tem");
        String hum = request.getParameter("hum");
        String time = request.getParameter("time");
        //将获取的用户姓名和密码封装在user对象里，然后传入service层
        Warning w = new Warning();
        w.setcId(cid);
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
            String id = request.getParameter("cId");
            System.out.println(id);
            List<Warning> list = warningService.listWarning();
            List<WarningVO> warningVoList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
          //      if (list.get(i).getcId() == Integer.parseInt(id))
                {
                    WarningVO wk = new WarningVO();
                    Warning w = list.get(i);
                    wk.setId(w.getcId());
                    wk.setTime(w.getwTime());
                    wk.setHum(w.getwHum());
                    wk.setTem(w.getwTem());
                   warningVoList.add(wk);
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
