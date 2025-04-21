package com.stdu.servlet;

import com.alibaba.fastjson.JSON;
import com.stdu.pojo.Maintenance;
import com.stdu.pojo.MaintenanceOrder;
import com.stdu.pojo.RepairOrder;
import com.stdu.service.MaintenanceOrderService;
import com.stdu.service.RepairOrderService;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/maintenanceOrder/*")
public class MaintenanceOrderServlet extends BaseServlet {

    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");

        BufferedReader reader = req.getReader();

        String json= reader.readLine();

       MaintenanceOrderService service = new MaintenanceOrderService();
        List<MaintenanceOrder> list= service.selectAll();
        String str= JSON.toJSONString(list);
        resp.getWriter().write(str);
        System.out.println("hahahahaha");
    }

    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

              resp.setContentType("text/html;charset=utf-8");
              BufferedReader reader = req.getReader();
              String json= reader.readLine();

              MaintenanceOrderService service = new MaintenanceOrderService();
              MaintenanceOrder maintenance= JSON.parseObject(json, MaintenanceOrder.class);
        System.out.println(maintenance.getEngineerId());
              service.updateOrder(maintenance);

    }



    }
