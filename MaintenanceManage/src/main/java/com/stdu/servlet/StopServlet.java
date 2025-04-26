package com.stdu.servlet;

import com.alibaba.fastjson.JSON;
import com.stdu.pojo.Stop;
import com.stdu.service.StopService;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/stop/*")
public class StopServlet extends BaseServlet {
       StopService stopService = new StopService();
    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");
        List<Stop> list=stopService.selectAllStops();

        String json= JSON.toJSONString(list);
        resp.getWriter().write(json);
    }
    public void selectById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");
        BufferedReader reader=req.getReader();
        String line=reader.readLine();
        int id=Integer.parseInt(line);
       Stop stop=stopService.selectStopById(Long.parseLong(line));
        resp.getWriter().write(JSON.toJSONString(stop));

    }

}
