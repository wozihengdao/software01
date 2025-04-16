package com.stdu.pojo;

// 报修单
public class RepairOrder {
    private Long id;
    private String stop;
    private String faultyEquipmentId;
    private String faultyType;
    private String faultyDescription;
    private String faultyGrade;
    private String picture;
    private String type;
    private String data;

    public RepairOrder(Long id, String stop, String faultyEquipmentId, String faultyType, String faultyDescription, String faultyGrade, String picture, String type, String data) {
        this.id = id;
        this.stop = stop;
        this.faultyEquipmentId = faultyEquipmentId;
        this.faultyType = faultyType;
        this.faultyDescription = faultyDescription;
        this.faultyGrade = faultyGrade;
        this.picture = picture;
        this.type = type;
        this.data = data;
    }

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

    public String getFaultyEquipmentId() {
        return faultyEquipmentId;
    }

    public void setFaultyEquipmentId(String faultyEquipmentId) {
        this.faultyEquipmentId = faultyEquipmentId;
    }

    public String getFaultyType() {
        return faultyType;
    }

    public void setFaultyType(String faultyType) {
        this.faultyType = faultyType;
    }

    public String getFaultyDescription() {
        return faultyDescription;
    }

    public void setFaultyDescription(String faultyDescription) {
        this.faultyDescription = faultyDescription;
    }

    public String getFaultyGrade() {
        return faultyGrade;
    }

    public void setFaultyGrade(String faultyGrade) {
        this.faultyGrade = faultyGrade;
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
}
