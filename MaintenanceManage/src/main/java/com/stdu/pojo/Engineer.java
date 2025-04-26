package com.stdu.pojo;

// 工程师

import javax.servlet.annotation.WebServlet;


public class Engineer {
    private Long id;
    private Integer orderNum;
    private String state;
    private String type;
    private String name;

    public Engineer(Long id, Integer orderNum, String state, String type, String name) {
        this.id = id;
        this.orderNum = orderNum;
        this.state = state;
        this.type = type;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public Engineer(Long id, Integer orderNum, String state, String type) {
        this.id = id;
        this.orderNum = orderNum;
        this.state = state;
        this.type = type;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}