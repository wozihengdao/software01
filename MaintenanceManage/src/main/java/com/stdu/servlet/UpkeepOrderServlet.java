package com.stdu.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
        System.out.println(json);
        resp.getWriter().write(json);
    }


    public void selectById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");
        String idParam = req.getParameter("id");
        Long id = Long.parseLong(idParam);

        String json= JSON.toJSONString(upkeepOrderService.selectById(id));

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
            UpkeepOrder upkeepOrder = upkeepOrderService.selectById(orderId);
            if (upkeepOrder != null) {
                upkeepOrder.setEngineerId(engineerId);
                upkeepOrderService.updateUpkeepOrder(upkeepOrder);
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
}
