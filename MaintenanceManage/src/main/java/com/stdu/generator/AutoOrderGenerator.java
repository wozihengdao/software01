package com.stdu.generator;

import com.stdu.pojo.*;
import com.stdu.service.*;
import com.stdu.util.DateUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AutoOrderGenerator {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final TimeSelService timeSelService = new TimeSelService();
    private final UpkeepOrderService upkeepService = new UpkeepOrderService();
    private final PatrolOrderService patrolService = new PatrolOrderService();
    private final CheckOrderService checkService = new CheckOrderService();
    private final SpareOrderService spareService = new SpareOrderService();

    public void start() {
        scheduler.scheduleAtFixedRate(this::checkAndGenerateOrders, 0, 24, TimeUnit.HOURS);
    }

    private void checkAndGenerateOrders() {
        List<TimeSel> configs = timeSelService.selectAll();
        Date now = new Date();

        for (TimeSel config : configs) {
            if (config.getDay() > 0 && shouldGenerate(config, now)) {
                generateOrder(config);
                updateLastGenerateTime(config, now);
            }
        }
    }

    private boolean shouldGenerate(TimeSel config, Date now) {
        if (config.getLastGenerateTime() == null) return true;

        long diff = now.getTime() - config.getLastGenerateTime().getTime();
        return (diff / (1000 * 60 * 60 * 24)) >= config.getDay();
    }

    private void generateOrder(TimeSel config) {
        try {
            switch (config.getOrderType()) {
                case "1": // 保养工单
                    UpkeepOrder upkeepTemplate = upkeepService.selectById(Long.parseLong(config.getTemplateOrderId()));
                    createNewUpkeepOrder(upkeepTemplate);
                    break;
                case "2": // 巡检工单
                    PatrolOrder patrolTemplate = patrolService.selectById(Long.parseLong(config.getTemplateOrderId()));
                    createNewPatrolOrder(patrolTemplate);
                    break;
                case "3": // 监测工单
                    CheckOrder checkTemplate = checkService.getCheckOrderById(Long.parseLong(config.getTemplateOrderId()));
                    createNewCheckOrder(checkTemplate);
                    break;
                case "4": // 备件工单
                    SpareOrder spareTemplate = spareService.selectById(Long.parseLong(config.getTemplateOrderId()));
                    createNewSpareOrder(spareTemplate);
                    break;
            }
        } catch (Exception e) {
            System.err.println("自动生成工单失败: " + e.getMessage());
        }
    }

    private void createNewUpkeepOrder(UpkeepOrder template) {
        UpkeepOrder newOrder = new UpkeepOrder();
        newOrder.setEquipmentId(template.getEquipmentId());
        newOrder.setStop(template.getStop());
        newOrder.setData(DateUtil.getCurrentDate());
        newOrder.setType("0");
        upkeepService.addUpkeepOrder(newOrder);
    }

    private void createNewPatrolOrder(PatrolOrder template) {
        PatrolOrder newOrder = new PatrolOrder();
        newOrder.setEquipmentId(template.getEquipmentId());
        newOrder.setStop(template.getStop());
        newOrder.setData(DateUtil.getCurrentDate());
        newOrder.setType("0");
        patrolService.addPatrolOrder(newOrder);
    }

    private void createNewCheckOrder(CheckOrder template) {
        CheckOrder newOrder = new CheckOrder();
        newOrder.setEquipmentId(template.getEquipmentId());
        newOrder.setStop(template.getStop());
        newOrder.setData(DateUtil.getCurrentDate());
        newOrder.setType("0");
        checkService.createCheckOrder(newOrder);
    }

    private void createNewSpareOrder(SpareOrder template) {
        SpareOrder newOrder = new SpareOrder();
        newOrder.setEquipmentId(template.getEquipmentId());
        newOrder.setStop(template.getStop());
        newOrder.setData(DateUtil.getCurrentDate());
        newOrder.setType("0");
        spareService.addSpareOrder(newOrder);
    }

    private void updateLastGenerateTime(TimeSel config, Date time) {
        config.setLastGenerateTime(time);
        timeSelService.update(config);
    }

    public void shutdown() {
        scheduler.shutdown();
    }
}