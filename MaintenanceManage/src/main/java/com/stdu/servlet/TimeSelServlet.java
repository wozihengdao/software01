package com.stdu.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.stdu.pojo.TimeSel;
import com.stdu.service.TimeSelService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/timeSel/*")
public class TimeSelServlet extends BaseServlet{
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if ("/update".equals(pathInfo)) {
            this.update(req, resp);
        } else if ("/selectAll".equals(pathInfo)) {
            this.selectAll(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
    public void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            JSONObject json = JSON.parseObject(readJsonRequest(req));

            TimeSel config = new TimeSel();
            int Ot = json.getInteger("orderType");

            config.setOrderType(String.valueOf(Ot)); // 直接使用字符串值
            config.setDay(json.getIntValue("day"));
            config.setTemplateOrderId(json.getString("templateOrderId"));
            config.setLastGenerateTime(new Date());


            TimeSelService service = new TimeSelService();
            if (service.selectByOrderType(config.getOrderType()) != null) {
                service.update(config);
                sendSuccess(resp, "配置更新成功");
            } else {
                service.init(config);
                sendSuccess(resp, "配置初始化成功");
            }
    }

    // 统一响应方法
    private void sendSuccess(HttpServletResponse resp, String message) throws IOException {
        JSONObject result = new JSONObject();
        result.put("code", 200);
        result.put("msg", message);
        resp.getWriter().write(result.toJSONString());
    }

    private void sendError(HttpServletResponse resp) throws IOException {
        resp.setStatus(400);
        JSONObject result = new JSONObject();
        result.put("code", 400);
        result.put("msg", "缺少必要参数");
        resp.getWriter().write(result.toJSONString());
    }

    private String readJsonRequest(HttpServletRequest req) throws IOException {
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) sb.append(line);
        return sb.toString();
    }
    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        TimeSelService timeSelService = new TimeSelService();
        List<TimeSel> list=timeSelService.selectAll();

        String json= JSON.toJSONString(list);
        resp.getWriter().write(json);
    }

}
