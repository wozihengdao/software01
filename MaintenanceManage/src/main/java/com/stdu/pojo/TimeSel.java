package com.stdu.pojo;

public class TimeSel {
    int id;
    int day;
    int orderType;


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

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    @Override
    public String toString() {
        return "TimeSel{" +
                "id=" + id +
                ", day=" + day +
                ", orderType=" + orderType +
                '}';
    }
}
