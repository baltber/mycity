package ru.mycity.core.controller.dto.stat;


public class DailyOrderStatDto {
    private int orderPrice;
    private int deliveryPrice;
    private int totalPrice;

    public DailyOrderStatDto() {
    }

    public DailyOrderStatDto(int orderPrice, int deliveryPrice, int totalPrice) {
        this.orderPrice = orderPrice;
        this.deliveryPrice = deliveryPrice;
        this.totalPrice = totalPrice;
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
