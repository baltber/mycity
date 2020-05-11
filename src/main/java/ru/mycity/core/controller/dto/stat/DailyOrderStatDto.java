package ru.mycity.core.controller.dto.stat;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DailyOrderStatDto {
    @JsonProperty("order_price")
    private int orderPrice;
    @JsonProperty("delivery_price")
    private int deliveryPrice;
    @JsonProperty("total_price")
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
