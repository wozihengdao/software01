package com.stdu.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.stdu.pojo.CheckOrder;
import com.stdu.pojo.Engineer;
import com.stdu.pojo.CheckOrder;
import com.stdu.service.CheckOrderService;
import com.stdu.service.EngineerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
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

    public void selectById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");
        String idParam = req.getParameter("id");
        Long id = Long.parseLong(idParam);

        String json= JSON.toJSONString(checkOrderService.getCheckOrderById(id));

        System.out.println(json);

        resp.getWriter().write(json);
    }

    public void assign(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
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

        try {
            Long orderId = Long.parseLong(idParam);
            CheckOrder checkOrder = checkOrderService.getCheckOrderById(orderId);
            if (checkOrder != null) {
                checkOrder.setEngineerId(engineerId);
                checkOrderService.updateCheckOrder(checkOrder);
                resp.getWriter().write("派单成功");
            } else {
                resp.getWriter().write("工单不存在");
            }
        } catch (NumberFormatException e) {
            resp.getWriter().write("工单 ID 格式错误");
        } catch (Exception e) {
            resp.getWriter().write("派单失败");
            e.printStackTrace();
        }
    }

    public void accept(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");
        // 读取JSON请求体
        BufferedReader reader = req.getReader();

        String line=reader.readLine();

        CheckOrder checkOrder = JSON.parseObject(line,CheckOrder.class);
        checkOrder.setType("1");
        checkOrderService.updateCheckOrder(checkOrder);

    }

    public void handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");
        // 读取JSON请求体
        BufferedReader reader = req.getReader();

        String line=reader.readLine();

        CheckOrder checkOrder = JSON.parseObject(line,CheckOrder.class);
        checkOrder.setType("2");
        checkOrderService.updateCheckOrder(checkOrder);

    }

}
