package com.stdu.pojo;

// 备件单
public class SpareOrder {
    private Long id;
    private String stop;
    private String equipmentId;
    private String equipmentType;
    private String engineerId;
    private String oldPicture;
    private String newPicture;
    private String type;
    private String data;

    public SpareOrder(Long id, String stop, String equipmentId, String equipmentType, String engineerId, String oldPicture, String newPicture, String type, String data) {
        this.id = id;
        this.stop = stop;
        this.equipmentId = equipmentId;
        this.equipmentType = equipmentType;
        this.engineerId = engineerId;
        this.oldPicture = oldPicture;
        this.newPicture = newPicture;
        this.type = type;
        this.data = data;
    }

    public SpareOrder() {

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

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getEngineerId() {
        return engineerId;
    }

    public void setEngineerId(String engineerId) {
        this.engineerId = engineerId;
    }

    public String getOldPicture() {
        return oldPicture;
    }

    public void setOldPicture(String oldPicture) {
        this.oldPicture = oldPicture;
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

    @Override
    public String toString() {
        return "SpareOrder{" +
                "id=" + id +
                ", stop='" + stop + '\'' +
                ", equipmentId='" + equipmentId + '\'' +
                ", equipmentType='" + equipmentType + '\'' +
                ", engineerId='" + engineerId + '\'' +
                ", oldPicture='" + oldPicture + '\'' +
                ", newPicture='" + newPicture + '\'' +
                ", type='" + type + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}