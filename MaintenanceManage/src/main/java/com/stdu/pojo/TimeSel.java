package com.stdu.pojo;

import java.util.Date;

public class TimeSel {
    int id;
    int day;
    String orderType;
    String templateOrderId;
    private Date lastGenerateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getTemplateOrderId() {
        return templateOrderId;
    }

    public void setTemplateOrderId(String templateOrderId) {
        this.templateOrderId = templateOrderId;
    }
    public Date getLastGenerateTime() {
        return lastGenerateTime;
    }

    public void setLastGenerateTime(Date lastGenerateTime) {
        this.lastGenerateTime = lastGenerateTime;
    }

    @Override
    public String toString() {
        return "TimeSel{" +
                "id=" + id +
                ", day=" + day +
                ", orderType=" + orderType +
                ", templateOrderId='" + templateOrderId + '\'' +
                ", lastGenerateTime=" + lastGenerateTime +
                '}';
    }
}
