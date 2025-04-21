package com.stdu.servlet;

import com.alibaba.fastjson.JSON;
import com.stdu.pojo.MaintenanceOrder;
import com.stdu.pojo.RepairOrder;
import com.stdu.service.MaintenanceOrderService;
import com.stdu.service.RepairOrderService;
import com.stdu.util.DateUtil;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/repairOrder/*")
public class RepairOrderServlet extends BaseServlet {

    public void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");

        BufferedReader reader = req.getReader();

        String json= reader.readLine();

        RepairOrder repairOrder = JSON.parseObject(json, RepairOrder.class);

        repairOrder.setData(DateUtil.getCurrentDate());
        repairOrder.setType("0");

      RepairOrderService service = new RepairOrderService();
      service.addRepairOrder(repairOrder);
        repairOrder=service.selectLast();
        MaintenanceOrder maintenanceOrder=new MaintenanceOrder(null,repairOrder.getId().toString(),
                null,repairOrder.getPicture(),"0",repairOrder.getData());
         MaintenanceOrderService maintenanceOrderService = new MaintenanceOrderService();
         maintenanceOrderService.addOrder(maintenanceOrder);
        System.out.println("hahahahaha");
        resp.getWriter().write("success");
    }

    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");

        BufferedReader reader = req.getReader();

        String json= reader.readLine();

        RepairOrderService service = new RepairOrderService();
        List<RepairOrder> list= service.selectAll();
        String str=JSON.toJSONString(list);
        resp.getWriter().write(str);
        System.out.println("hahahahaha");
    }

    public RepairOrder selectLast(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


       return null;
    }


}
