package com.stdu.servlet;

import com.alibaba.fastjson.JSON;
import com.stdu.pojo.Equipment;
import com.stdu.pojo.MaintenanceOrder;
import com.stdu.pojo.RepairOrder;
import com.stdu.service.EngineerService;
import com.stdu.service.EquipmentService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/repairOrder/*")
public class RepairOrderServlet extends BaseServlet {
     RepairOrderService repairOrderService = new RepairOrderService();

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
    }

    public void selectById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=utf-8");

        try {
            String idParam = req.getParameter("id");
            Long id = Long.parseLong(idParam);
            RepairOrder order = repairOrderService.selectById(id);
            System.out.println(order);
            EquipmentService equipmentService = new EquipmentService();

            Equipment equipment = equipmentService.selectById(Long.parseLong(order.getFaultyEquipmentId())); // 需要实现EquipmentService
            String deviceName = (equipment != null) ? equipment.getName() : "未知设备";

            if(order == null) {
                resp.sendError(404, "工单不存在");
                return;
            }
            // 转换日期格式
            Map<String,Object> result = new HashMap<>();
            result.put("id", order.getId());
            result.put("stop", order.getStop());
            result.put("faultyEquipmentId", order.getFaultyEquipmentId());
            result.put("faultyType", order.getFaultyType());
            result.put("faultyDescription", order.getFaultyDescription());
            result.put("faultyGrade", order.getFaultyGrade());
            result.put("picture", order.getPicture());
            result.put("data", order.getData());  // 使用格式化后的字符串
            result.put("equipmentName", deviceName); // 添加设备名称字段
            resp.getWriter().write(JSON.toJSONString(result));

        } catch (NumberFormatException e) {
            resp.sendError(400, "ID格式错误");
        } catch (Exception e) {
            resp.sendError(500, "服务器错误");
        }
    }


    public RepairOrder selectLast(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


       return null;
    }





}
