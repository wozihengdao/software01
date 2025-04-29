package com.stdu.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.stdu.pojo.PatrolOrder;
import com.stdu.pojo.SpareOrder;
import com.stdu.pojo.Stop;
import com.stdu.pojo.TimeSel;
import com.stdu.service.PatrolOrderService;
import com.stdu.service.StopService;
import com.stdu.service.TimeSelService;
import com.stdu.util.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/patrolOrder/*")
public class PatrolOrderServlet extends BaseServlet {
    PatrolOrderService patrolOrderService = new PatrolOrderService();
    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");
        List<PatrolOrder> list=patrolOrderService.selectAll();

        String json= JSON.toJSONString(list);
        System.out.println(json);
        resp.getWriter().write(json);
    }
    public void selectById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");
        String idParam = req.getParameter("id");
        Long id = Long.parseLong(idParam);

        String json= JSON.toJSONString(patrolOrderService.selectById(id));

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
            PatrolOrder patrolOrder = patrolOrderService.selectById(orderId);
            if (patrolOrder != null) {
                patrolOrder.setEngineerId(engineerId);
                patrolOrder.setType("1");
                patrolOrderService.updatePatrolOrder(patrolOrder);
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

    public void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        JSONObject json = JSON.parseObject(sb.toString());
        // 工单创建
        PatrolOrder order = new PatrolOrder();
        String stopId = json.getString("stop");
        StopService stopService = new StopService();
        Stop stop =stopService.selectStopById(Long.parseLong(stopId));
        order.setStop(stop.getName());
        order.setEquipmentId(json.getString("equipmentId"));
        order.setEquipmentType(json.getString("equipmentType"));
        order.setType("0");
        order.setData(DateUtil.getCurrentDate());

        patrolOrderService.addPatrolOrder(order);

        // 模板配置处理
        if(json.getBooleanValue("isTemplate")){
            TimeSelService timeSelService = new TimeSelService();
            TimeSel config = new TimeSel();
            config.setOrderType("2");
            config.setDay(7); // 设置默认周期
            config.setTemplateOrderId(String.valueOf(order.getId()));
            config.setLastGenerateTime(new Date());
            if(timeSelService.selectByOrderType("2")!=null){
                timeSelService.update(config);
            }else{
                timeSelService.init(config);
            }
        }
        JSONObject result = new JSONObject();
        result.put("code", 200);
        result.put("msg", "工单创建成功");
        resp.getWriter().write(result.toJSONString());
    }

    public void getLatestTemplate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        JSONObject result = new JSONObject();

        try {
            TimeSelService timeSelService = new TimeSelService();
            // 1. 查询保养工单类型（orderType=2）的配置
            TimeSel timeSel = timeSelService.selectByOrderType("2");

            // 2. 检查配置是否存在
            if (timeSel == null || timeSel.getTemplateOrderId() == null) {
                result.put("code", 404);
                result.put("msg", "尚未设置模板，请先创建并标记模板工单");
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write(result.toJSONString());
                return;
            }

            // 3. 获取模板工单ID
            Long templateId = Long.parseLong(timeSel.getTemplateOrderId());

            // 4. 查询工单详细信息
            PatrolOrder templateOrder = patrolOrderService.selectById(templateId);

            if (templateOrder == null) {
                result.put("code", 404);
                result.put("msg", "模板工单不存在，可能已被删除");
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            } else {
                // 5. 返回模板工单数据
                result.put("code", 200);
                result.put("msg", "success");
                result.put("data", JSON.toJSON(templateOrder));
                resp.setStatus(HttpServletResponse.SC_OK);
            }
        } catch (NumberFormatException e) {
            result.put("code", 500);
            result.put("msg", "模板ID格式错误");
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            result.put("code", 500);
            result.put("msg", "系统错误：" + e.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }

        resp.getWriter().write(result.toJSONString());
    }


    public void accept(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");
        // 读取JSON请求体
        BufferedReader reader = req.getReader();

        String line=reader.readLine();

        PatrolOrder patrolOrder = JSON.parseObject(line,PatrolOrder.class);
        patrolOrder.setType("1");
        patrolOrderService.updatePatrolOrder(patrolOrder);

    }

    public void handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");
        // 读取JSON请求体
        BufferedReader reader = req.getReader();

        String line=reader.readLine();

        PatrolOrder patrolOrder = JSON.parseObject(line,PatrolOrder.class);
        patrolOrder.setType("2");
        patrolOrderService.updatePatrolOrder(patrolOrder);

    }



}
