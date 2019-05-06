package com.usts.xuexiangyu.controller;

import com.usts.xuexiangyu.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.usts.xuexiangyu.service.UserService;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;


@Controller
public class UserController {
    @Autowired
    UserService userService;
    boolean isLogin = false;


    /*登录
     * 从前端获取用户名和密码
     * 根据返回柜子数据，其中管理员返回所有柜子，普通用户返回所属的柜子
     * 测试地址为：http://localhost:8080/login
     * */
    @RequestMapping("/login1")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Integer> map = new HashMap();
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        Users u = new Users();
        u.setuName(name);
        u.setuPwd(pwd);
        map = userService.login(u);
        //map为空，说明用户名或者密码错误，跳转到登录页面重新登录
        if (map.get("login")==2) {
            Cookie ckIsLogin = new Cookie("isLogin","0");
            ckIsLogin.setMaxAge(-1);
            response.addCookie(ckIsLogin);
            response.sendRedirect("login.html");
        }
        //普通用户，返回index.html
        else if (map.get("role") == 2) {
            Cookie ckName = new Cookie("name", name);
            Cookie ckIsLogin = new Cookie("isLogin","1");
            ckName.setMaxAge(-1);
            ckIsLogin.setMaxAge(-1);
            response.addCookie(ckName);
            response.addCookie(ckIsLogin);
            response.sendRedirect("index2.html");
        } else {
            //管理员，跳转到admin.html
            Cookie ckName = new Cookie("name", name);
            Cookie ckIsLogin = new Cookie("isLogin","1");
            ckName.setMaxAge(-1);
            ckIsLogin.setMaxAge(-1);
            response.addCookie(ckName);
            response.addCookie(ckIsLogin);
            response.sendRedirect("index.html");
        }

    }


    //增加用户，从前端获取用户姓名和密码
    //前端测试地址为：http://localhost:8080/updateUser?name=zhouha&pwd=145236

    @RequestMapping("/addUser")
    public @ResponseBody Map<String, Object> addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        //获取用户姓名和密码，通过HttpServletRequest实现
        HttpSession session = request.getSession();
        String name = request.getParameter("name");
        String pwd = "admin";
        String time = request.getParameter("time");
        String age = request.getParameter("age");
        String sex = request.getParameter("sex");
        //将获取的用户姓名和密码封装在user对象里，然后传入service层
        Users u = new Users();
        u.setuName(name);
        u.setuPwd(pwd);
        u.setuRole(2);
        u.setuTime(time);
        u.setuSex(sex);
        u.setuAge(age);
        userService.addUser(u);
        map.put("result","success");
        map.put("respDisc","添加成功");
        return map;

    }

    //删除用户，从前端获取用户的id
    //前端测试地址为：http://localhost:8080/updateUser?id=11
    @RequestMapping("/delUser")
    public @ResponseBody Map<String, Object> delUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        //获取用户姓名和密码，通过HttpServletRequest实现
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        //将获取的用户id封装在user对象里，然后传入service层
        Users u = new Users();
        u.setuId(id);
        userService.delUser(u);
        map.put("result","success");
        map.put("respDisc","删除成功");
        return map;
    }

    //修改用户，从前端获取用户的id，姓名，密码
    //前端测试地址为：http://localhost:8080/updateUser?id=11&name=zhouha&pwd=145236
    //注意：没有加密码为空的判断
    @RequestMapping("/updateUser")
    public
    @ResponseBody Map<String, Object> updateUser(HttpServletRequest request) {
        //获取用户id，用户名和密码，通过HttpServletRequest实现
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String time = request.getParameter("time");
        String age = request.getParameter("age");
        String pwd = request.getParameter("pwd");
        String sex = request.getParameter("sex");
        //将获取的用户id，用户名和密码封装在user对象里，然后传入service层
        Users u = new Users();
        u.setuId(id);
        u.setuName(name);
        u.setuRole(2);
        u.setuTime(time);
        u.setuPwd(this.userService.findOne(id).getuPwd());
        u.setuAge(age);
        u.setuSex(sex);
        u.setuPwd(pwd);
        userService.updateUser(u);
        map.put("result","success");
        map.put("respDisc","修改成功");
        return map;
    }
    @RequestMapping("/updatePwd")
    public
    @ResponseBody Map<String, Object> updatePwd(HttpServletRequest request) {
        //获取用户id，用户名和密码，通过HttpServletRequest实现
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        int role = Integer.parseInt(request.getParameter("role"));
        //将获取的用户id，用户名和密码封装在user对象里，然后传入service层
        Users u = new Users();
        u.setuId(id);//前端传过来
        u.setuName(name);
        u.setuRole(role);
        u.setuPwd(pwd);
        u.setuAge(this.userService.findOne(id).getuAge());
        u.setuSex(this.userService.findOne(id).getuSex());
        u.setuTime(this.userService.findOne(id).getuTime());
        userService.updateUser(u);
        map.put("result","success");
        map.put("respDisc","修改成功");
        return map;
    }


    //显示所有用户
    //前端测试地址为：http://localhost:8080/listUser
    //注意：此功能先不要写，需要登录,但是可以先测试一下看看效果
    @RequestMapping("/listUser")
    public @ResponseBody
    Map<String, Object> listUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
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
            List<Users> list = userService.listUsers();
            List<UsersVO> usersVoList = new ArrayList<>();


            for (int i = 0; i < list.size(); i++) {
                UsersVO uv = new UsersVO();
                Users u = list.get(i);
                uv.setId(u.getuId());
                uv.setName(u.getuName());
                uv.setTime(u.getuTime());
                uv.setPwd(u.getuPwd());
                uv.setRole(u.getuRole());
                uv.setAge(u.getuAge());
                uv.setSex(u.getuSex());


                usersVoList.add(uv);
            }
            map.put("code", 0);
            map.put("msg", "WWW");
            map.put("count", usersVoList.size());
            map.put("data", usersVoList);


            //UsermVO未使用
          /* List<UsersmVO> usersmVoList = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    UsersmVO uk = new UsersmVO();
                    Users u = list.get(i);
                    uk.setId(u.getuId());
                    uk.setName(u.getuName());
                    uk.setPwd(u.getuPwd());
                    uk.setRole(u.getuRole());
                    usersmVoList.add(uk);
                }
                map.put("code", 0);
                map.put("msg", "WWW");
                map.put("count", usersmVoList.size());
                map.put("data", usersmVoList);*/


            return map;
        }
    }

    @RequestMapping("/listmUser")
    public @ResponseBody
    Map<String, Object> listmUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println(request.getCookies().length);
        //如果没登录，提示没登录
        if (request.getCookies().length < 3) {
            map.put("respCode", 1);
            map.put("respDesc", "您尚未登录，请先登录！");
            return map;
        } else {
            List<Users> list = userService.listUsers();
            List<UsersmVO> usersmVoList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                UsersmVO uk = new UsersmVO();
                Users u = list.get(i);
                uk.setId(u.getuId());
                uk.setName(u.getuName());
                uk.setPwd(u.getuPwd());
                uk.setRole(u.getuRole());
                uk.setAge(u.getuAge());
                uk.setSex(u.getuSex());
                usersmVoList.add(uk);
            }
            map.put("code", 0);
            map.put("msg", "WWW");
            map.put("count", usersmVoList.size());
            map.put("data", usersmVoList);


            return map;
        }
    }

}








