package ru.mycity.core.service.dao.model;

import java.sql.Timestamp;

public class Order {

    private String innerId;
    private String clientOrderId;
    private DishStat dishStat;
    private OrderStat orderStat;
    private Timestamp orderDate;

    public Order() {
    }

    public Order(String innerId, String clientOrderId, DishStat dishStat, OrderStat orderStat, Timestamp orderDate) {
        this.innerId = innerId;
        this.clientOrderId = clientOrderId;
        this.dishStat = dishStat;
        this.orderStat = orderStat;
        this.orderDate = orderDate;
    }

    public String getInnerId() {
        return innerId;
    }

    public void setInnerId(String innerId) {
        this.innerId = innerId;
    }

    public String getClientOrderId() {
        return clientOrderId;
    }

    public void setClientOrderId(String clientOrderId) {
        this.clientOrderId = clientOrderId;
    }

    public DishStat getDishStat() {
        return dishStat;
    }

    public void setDishStat(DishStat dishStat) {
        this.dishStat = dishStat;
    }

    public OrderStat getOrderStat() {
        return orderStat;
    }

    public void setOrderStat(OrderStat orderStat) {
        this.orderStat = orderStat;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }
}
