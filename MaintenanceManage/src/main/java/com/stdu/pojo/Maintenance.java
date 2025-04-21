package com.stdu.pojo;

public class Maintenance {

    private Long id;
    private String stop;
    private String faultyEquipmentId;
    private String faultyType;
    private String faultyDescription;
    private String faultyGrade;
    private String olderPicture;
    private String newPicture;
    private String type;
    private String data;
    private String engineerId;

    public Maintenance(Long id, String stop, String faultyEquipmentId, String faultyType, String faultyDescription, String faultyGrade, String olderPicture, String newPicture, String type, String data, String engineerId) {
        this.id = id;
        this.stop = stop;
        this.faultyEquipmentId = faultyEquipmentId;
        this.faultyType = faultyType;
        this.faultyDescription = faultyDescription;
        this.faultyGrade = faultyGrade;
        this.olderPicture = olderPicture;
        this.newPicture = newPicture;
        this.type = type;
        this.data = data;
        this.engineerId = engineerId;
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

    public String getOlderPicture() {
        return olderPicture;
    }

    public void setOlderPicture(String olderPicture) {
        this.olderPicture = olderPicture;
    }

    public String getNewPicture() {
        return newPicture;
    }

    public void setNewPicture(String newPicture) {
        this.newPicture = newPicture;
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

    public String getEngineerId() {
        return engineerId;
    }

    public void setEngineerId(String engineerId) {
        this.engineerId = engineerId;
    }
}
