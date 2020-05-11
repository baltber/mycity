package ru.mycity.core.service.dao.model;


import ru.mycity.core.controller.dto.stat.DailyOrderStatDto;

public class OrderStat {
    private int orderPrice;
    private int deliveryPrice;
    private int totalPrice;

    public OrderStat(int orderPrice, int deliveryPrice, int totalPrice) {
        this.orderPrice = orderPrice;
        this.deliveryPrice = deliveryPrice;
        this.totalPrice = totalPrice;
    }

    public DailyOrderStatDto toDto(){
        return new DailyOrderStatDto(orderPrice, deliveryPrice, totalPrice);
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
}
