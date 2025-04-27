package com.stdu.servlet;

import com.alibaba.fastjson.JSON;
import com.stdu.pojo.Engineer;
import com.stdu.service.EngineerService;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/engineer/*")
public class EngineerServlet  extends BaseServlet {
    EngineerService engineerService = new EngineerService();
    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");
        List<Engineer> list=engineerService.selectAll(); ;

        String json= JSON.toJSONString(list);
        System.out.println(json);
        resp.getWriter().write(json);
    }

    public void selectById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("/selectById");
        resp.setContentType("text/html;charset=utf-8");
        BufferedReader reader=req.getReader();
        String line=reader.readLine();
        if(line==null){

            line="70000001";

        }

        Long id=Long.valueOf(line);
        System.out.println(id);
        Engineer engineer=engineerService.selectById(id);
        String json= JSON.toJSONString(engineer);
        System.out.printf(json);
        resp.getWriter().write(json);

    }




}
