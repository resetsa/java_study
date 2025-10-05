package com.resetsa.homeworks.model;

public class OrderStatus {
    private Integer id;
    private String status;
    
    public OrderStatus(Integer id, String status) {
        this.id = id;
        this.status = status;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return "OrderStatus [id=" + id + ", status=" + status + "]";
    }
}
