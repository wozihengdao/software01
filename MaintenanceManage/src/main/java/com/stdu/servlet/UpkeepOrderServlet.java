package com.stdu.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.stdu.pojo.Stop;
import com.stdu.pojo.TimeSel;
import com.stdu.pojo.UpkeepOrder;
import com.stdu.service.StopService;
import com.stdu.service.TimeSelService;
import com.stdu.service.UpkeepOrderService;
import com.stdu.util.DateUtil;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@WebServlet("/upkeepOrder/*")
public class UpkeepOrderServlet extends BaseServlet {
    UpkeepOrderService upkeepOrderService = new UpkeepOrderService();
    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");
        List<UpkeepOrder> list=upkeepOrderService.selectAll();

        String json= JSON.toJSONString(list);
        resp.getWriter().write(json);
    }


    public void selectById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");
        String idParam = req.getParameter("id");
        Long id = Long.parseLong(idParam);

        String json= JSON.toJSONString(upkeepOrderService.selectById(id));

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
                upkeepOrder.setType("1");
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

<<<<<<< HEAD
    public void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        JSONObject json = JSON.parseObject(sb.toString());
        // 工单创建
        UpkeepOrder order = new UpkeepOrder();
        String stopId = json.getString("stop");
        StopService stopService = new StopService();
        Stop stop =stopService.selectStopById(Long.parseLong(stopId));
        order.setStop(stop.getName());
        order.setEquipmentId(json.getString("equipmentId"));
        order.setEquipmentType(json.getString("equipmentType"));
        order.setType("0");
        order.setData(DateUtil.getCurrentDate());

        UpkeepOrder createdOrder = upkeepOrderService.addUpkeepOrder2(order);


        // 模板配置处理
        if(json.getString("isTemplate").equals("1")){
            TimeSelService timeSelService = new TimeSelService();
            TimeSel config = new TimeSel();
            config.setOrderType("1");
            config.setDay(7); // 设置默认周期
            config.setTemplateOrderId(createdOrder.getId().toString()); // 正确获取ID
            config.setLastGenerateTime(new Date());
            if(timeSelService.selectByOrderType("1")!=null){
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
            // 1. 查询保养工单类型（orderType=1）的配置
            TimeSel timeSel = timeSelService.selectByOrderType("1");

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
            UpkeepOrder templateOrder = upkeepOrderService.selectById(templateId);

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


=======
>>>>>>> 453dc911a51acfdef70cc13ca2ae187aec0d0705
    public void accept(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");
        // 读取JSON请求体
        BufferedReader reader = req.getReader();

        String line=reader.readLine();

        UpkeepOrder upkeepOrder = JSON.parseObject(line,UpkeepOrder.class);
<<<<<<< HEAD
        upkeepOrder.setType("1");
=======
                    upkeepOrder.setType("1");
>>>>>>> 453dc911a51acfdef70cc13ca2ae187aec0d0705
        upkeepOrderService.updateUpkeepOrder(upkeepOrder);

    }

    public void handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");
        // 读取JSON请求体
        BufferedReader reader = req.getReader();

        String line=reader.readLine();

        UpkeepOrder upkeepOrder = JSON.parseObject(line,UpkeepOrder.class);
        upkeepOrder.setType("2");
        upkeepOrderService.updateUpkeepOrder(upkeepOrder);

    }
<<<<<<< HEAD

=======
>>>>>>> 453dc911a51acfdef70cc13ca2ae187aec0d0705
}
