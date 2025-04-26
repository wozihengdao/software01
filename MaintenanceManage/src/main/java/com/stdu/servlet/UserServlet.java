package com.stdu.servlet;

import com.alibaba.fastjson.JSON;
import com.stdu.pojo.User;
import com.stdu.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet{

    public void logIn(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
        System.out.println("????");
                UserService userService=new UserService();
        System.out.println("??????");
        resp.setContentType("text/html;charset=utf-8");

        BufferedReader reader = req.getReader();

        String json= reader.readLine();
        System.out.println(json);
        User user= JSON.parseObject(json,User.class);
        System.out.println(user);
       User user1=userService.selectUserByUsername(user.getUsername());
       if(user1!=null&&user1.getPassword().equals(user.getPassword())){

           resp.getWriter().write("success");
           return;
       }
       else {

           resp.getWriter().write("fail");

       }



    }

}
