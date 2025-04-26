package com.stdu.servlet;

import com.alibaba.fastjson.JSON;
import com.stdu.pojo.Stop;
import com.stdu.pojo.UpkeepOrder;
import com.stdu.service.UpkeepOrderService;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@WebServlet("/upkeepOrder/*")
public class UpkeepOrderServlet extends BaseServlet {
    UpkeepOrderService upkeepOrderService = new UpkeepOrderService();
    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");
        List<UpkeepOrder> list=upkeepOrderService.selectAll();

        String json= JSON.toJSONString(list);
        resp.getWriter().write(json);
    }

    public void selectById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        resp.setContentType("text/html;charset=utf-8");
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String line=br.readLine();

        Long id=Long.parseLong(line);


        String json= JSON.toJSONString(upkeepOrderService.selectById(id));
        resp.getWriter().write(json);
    }



}
