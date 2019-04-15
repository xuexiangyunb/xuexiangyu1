package com.usts.xuexiangyu.controller;

import com.usts.xuexiangyu.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.usts.xuexiangyu.service.UserService;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;


@Controller
public class UserController {
    @Autowired
    UserService userService;


    /*登录
    * 从前端获取用户名和密码
    * 根据返回柜子数据，其中管理员返回所有柜子，普通用户返回所属的柜子
    * 测试地址为：http://localhost:8080/login?name=李广广&pwd=1245
    * */

    public
    String login(HttpServletRequest request ,HttpServletResponse response){
        Map<String,Object> map = new HashMap();
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        Users u = new Users();
        u.setuName(name);
        u.setuPwd(pwd);
        map = userService.login(u);
        return  "admin";
    }


    //增加用户，从前端获取用户姓名和密码
    //前端测试地址为：http://localhost:8080/updateUser?name=zhouha&pwd=145236

    @RequestMapping("/addUser")
    public String addUser(HttpServletRequest request){
        //获取用户姓名和密码，通过HttpServletRequest实现
        HttpSession session = request.getSession();
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        //将获取的用户姓名和密码封装在user对象里，然后传入service层
        Users u = new Users();
        u.setuName(name);
        u.setuPwd(pwd);
        u.setuRole(1);
        userService.addUser(u);
        return "success.html";
    }

    //删除用户，从前端获取用户的id
    //前端测试地址为：http://localhost:8080/updateUser?id=11
    @RequestMapping("/delUser")
    public String delUser(HttpServletRequest request){
        //获取用户id，通过HttpServletRequest实现
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        //将获取的用户id封装在user对象里，然后传入service层
        Users u = new Users();
        u.setuId(id);
        userService.delUser(u);
        return "success.html";
    }

    //修改用户，从前端获取用户的id，姓名，密码
    //前端测试地址为：http://localhost:8080/updateUser?id=11&name=zhouha&pwd=145236
    //注意：没有加密码为空的判断
    @RequestMapping("/updateUser")
    public String updateUser(HttpServletRequest request){
        //获取用户id，用户名和密码，通过HttpServletRequest实现
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        //将获取的用户id，用户名和密码封装在user对象里，然后传入service层
        Users u = new Users();
        u.setuId(id);
        u.setuName(name);
        u.setuPwd(pwd);
        u.setuRole(1);
        userService.updateUser(u);
        return "success.html";
    }

    //显示所有用户
    //前端测试地址为：http://localhost:8080/listUser
    //注意：此功能先不要写，需要登录,但是可以先测试一下看看效果
    @RequestMapping("/listUser")
    public @ResponseBody
    Map<String, Object> listUser(){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Users> list = userService.listUsers();
        map.put("userList",list);
        return map;
    }






}
