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
import java.io.IOException;
import java.util.List;

@WebServlet("/upkeep/*")
public class UpkeepOrderServlet extends BaseServlet {
    UpkeepOrderService upkeepOrderService = new UpkeepOrderService();
    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");
        List<UpkeepOrder> list=upkeepOrderService.selectAll();

        String json= JSON.toJSONString(list);
        resp.getWriter().write(json);
    }



}
