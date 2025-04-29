package com.stdu.servlet;

import com.alibaba.fastjson.JSON;
import com.stdu.pojo.Equipment;
import com.stdu.service.EquipmentService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/equipment/*")
public class EquipmentServlet extends BaseServlet {
    private EquipmentService equipmentService = new EquipmentService();

    // 设备列表查询接口
    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=utf-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");

        List<Equipment> equipments = equipmentService.selectAll();
        String json = JSON.toJSONString(equipments);
        resp.getWriter().write(json);
    }

    // 设备详情查询接口
    public void selectById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setHeader("Access-Control-Allow-Origin", "*");

        try {
            Equipment equipment = equipmentService.selectById(Long.parseLong(req.getParameter("id")));
            if(equipment != null) {
                resp.getWriter().write(JSON.toJSONString(equipment));
            } else {
                resp.getWriter().write("{}");
            }
        } catch (Exception e) {
            resp.getWriter().write("{}");
        }
    }
}