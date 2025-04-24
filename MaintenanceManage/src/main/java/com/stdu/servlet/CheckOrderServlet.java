package com.stdu.servlet;

import com.alibaba.fastjson.JSON;
import com.stdu.pojo.CheckOrder;
import com.stdu.pojo.Engineer;
import com.stdu.service.CheckOrderService;
import com.stdu.service.EngineerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/checkOrder/*")
public class CheckOrderServlet extends BaseServlet {
    CheckOrderService checkOrderService = new CheckOrderService();
    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");
        List<CheckOrder> list= checkOrderService.getAllCheckOrders(); ;

        String json= JSON.toJSONString(list);
        resp.getWriter().write(json);
    }


}
