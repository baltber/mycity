package ru.mycity.core.service.dao.model;


import ru.mycity.core.controller.dto.stat.DailyOrderStatDto;

import java.sql.Timestamp;

public class OrderStat {
    private int orderPrice;
    private int deliveryPrice;
    private int totalPrice;
    private Timestamp orderDate;

    public OrderStat(int orderPrice, int deliveryPrice, int totalPrice) {
        this.orderPrice = orderPrice;
        this.deliveryPrice = deliveryPrice;
        this.totalPrice = totalPrice;
    }

    public OrderStat(int orderPrice, int deliveryPrice, int totalPrice, Timestamp orderDate) {
        this.orderPrice = orderPrice;
        this.deliveryPrice = deliveryPrice;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }

    public DailyOrderStatDto toDto(){
        return new DailyOrderStatDto(orderPrice, deliveryPrice, totalPrice, orderDate);
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(int deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }
}
