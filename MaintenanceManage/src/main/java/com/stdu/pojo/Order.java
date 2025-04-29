package com.stdu.pojo;

public class Order {

   private Long id;
   private String kind;
   private String data;

    public Order(Long id, String kind, String data) {
        this.id = id;
        this.kind = kind;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", kind='" + kind + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
