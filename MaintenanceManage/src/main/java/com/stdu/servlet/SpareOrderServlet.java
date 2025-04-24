package com.stdu.servlet;

import com.alibaba.fastjson.JSON;
import com.stdu.pojo.SpareOrder;
import com.stdu.service.SpareOrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/spare/*")
public class SpareOrderServlet extends BaseServlet{
    SpareOrderService spareOrderService = new SpareOrderService();
    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");
        List<SpareOrder> list=spareOrderService.selectAll();

        String json= JSON.toJSONString(list);
        resp.getWriter().write(json);
    }

}
