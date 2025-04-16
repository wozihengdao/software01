package com.stdu.pojo;

// 设备表
public class Equipment {
    private Long id;
    private String stop;
    private String name;
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStop() {
        return stop;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Equipment(Long id, String stop, String name, String type) {
        this.id = id;
        this.stop = stop;
        this.name = name;
        this.type = type;
    }
}