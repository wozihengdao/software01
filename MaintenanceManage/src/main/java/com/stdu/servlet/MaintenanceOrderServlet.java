package com.stdu.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/maintenanceOrder/*")
public class MaintenanceOrderServlet extends BaseServlet {
    MaintenanceOrderService maintenanceOrderService = new MaintenanceOrderService();

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

        MaintenanceOrder maintenance= JSON.parseObject(json, MaintenanceOrder.class);
        System.out.println(maintenance.getEngineerId());
        maintenanceOrderService.updateOrder(maintenance);

    }

    public void selectById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");
        System.out.println("/maintenanceOrder/selectById");
        BufferedReader reader = req.getReader();
        String json= reader.readLine();
        if(json==null)json="20000000";
//
        Maintenance maintenance=maintenanceOrderService.selectAllMaintenanceById(json);
        System.out.println(maintenance);
        resp.getWriter().write(JSON.toJSONString(maintenance));

    }

    public void assign(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        // 读取JSON请求体
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        JSONObject json = JSON.parseObject(sb.toString());

        String idParam = json.getString("orderId");
        String engineerId = json.getString("engineerId");


        System.out.println(idParam);
        System.out.println(engineerId);

        try {
            Long id = Long.parseLong(idParam);
            MaintenanceOrder maintenanceOrder = maintenanceOrderService.selectById(id);
            if (maintenanceOrder != null) {
                maintenanceOrder.setEngineerId(engineerId);
                maintenanceOrderService.updateOrder(maintenanceOrder);
                resp.setContentType("text/plain;charset=utf-8"); // 明确指定文本类型
                resp.getWriter().write("派单成功");
            } else {
                resp.setContentType("text/plain;charset=utf-8"); // 明确指定文本类型
                resp.getWriter().write("工单不存在");
            }
        } catch (NumberFormatException e) {
            resp.setContentType("text/plain;charset=utf-8"); // 明确指定文本类型
            resp.getWriter().write("工单 ID 格式错误");
        } catch (Exception e) {
            resp.setContentType("text/plain;charset=utf-8"); // 明确指定文本类型
            resp.getWriter().write("派单失败");
            e.printStackTrace();
        }
    }


}
