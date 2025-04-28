package com.stdu.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.stdu.pojo.SpareOrder;
import com.stdu.pojo.SpareOrder;
import com.stdu.service.SpareOrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/spareOrder/*")
public class SpareOrderServlet extends BaseServlet{
    SpareOrderService spareOrderService = new SpareOrderService();
    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");
        List<SpareOrder> list=spareOrderService.selectAll();

        String json= JSON.toJSONString(list);
        resp.getWriter().write(json);
    }
    public void selectById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");
        String idParam = req.getParameter("id");
        Long id = Long.parseLong(idParam);

        String json= JSON.toJSONString(spareOrderService.selectById(id));

        System.out.println(json);

        resp.getWriter().write(json);
    }
    public void assign(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

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
            SpareOrder spareOrder = spareOrderService.selectById(orderId);
            if (spareOrder != null) {
                spareOrder.setEngineerId(engineerId);
                spareOrderService.updateSpareOrder(spareOrder);
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

        SpareOrder spareOrder = JSON.parseObject(line,SpareOrder.class);
        spareOrder.setType("1");
        spareOrderService.updateSpareOrder(spareOrder);

    }

    public void handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");
        // 读取JSON请求体
        BufferedReader reader = req.getReader();

        String line=reader.readLine();

        SpareOrder spareOrder = JSON.parseObject(line,SpareOrder.class);
        spareOrder.setType("2");
        spareOrderService.updateSpareOrder(spareOrder);

    }


}
