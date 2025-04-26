package com.stdu.servlet;

import com.alibaba.fastjson.JSON;
import com.stdu.pojo.PatrolOrder;
import com.stdu.service.PatrolOrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/patrolOrder/*")
public class PatrolOrderServlet extends BaseServlet {
    PatrolOrderService patrolOrderService = new PatrolOrderService();
    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");
        List<PatrolOrder> list=patrolOrderService.selectAll();

        String json= JSON.toJSONString(list);
        System.out.println(json);
        resp.getWriter().write(json);
    }

}
