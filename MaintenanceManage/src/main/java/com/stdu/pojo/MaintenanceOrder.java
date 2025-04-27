package com.stdu.pojo;

// 维修单
public class MaintenanceOrder {
    private Long id;
    private String repairId;
    private String engineerId;
    private String picture;
    private String type;
    private String data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRepairId() {
        return repairId;
    }

    public void setRepairId(String repairId) {
        this.repairId = repairId;
    }

    public String getEngineerId() {
        return engineerId;
    }

    public void setEngineerId(String engineerId) {
        this.engineerId = engineerId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public MaintenanceOrder(Long id, String repairId, String engineerId, String picture, String type, String data) {
        this.id = id;
        this.repairId = repairId;
        this.engineerId = engineerId;
        this.picture = picture;
        this.type = type;
        this.data = data;
    }

    @Override
    public String toString() {
        return "MaintenanceOrder{" +
                "id=" + id +
                ", repairId='" + repairId + '\'' +
                ", engineerId='" + engineerId + '\'' +
                ", picture='" + picture + '\'' +
                ", type='" + type + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
