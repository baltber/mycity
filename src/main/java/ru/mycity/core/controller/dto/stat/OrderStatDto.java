package ru.mycity.core.controller.dto.stat;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OrderStatDto {
    @JsonProperty("order_price")
    private int orderPrice;
    @JsonProperty("delivery_price")
    private int deliveryPrice;
    @JsonProperty("total_price")
    private int totalPrice;
    @JsonProperty("avg_order_price")
    private Double avgOrderPrice;
    @JsonProperty("daily_order_list")
    private List<DailyOrderStatDto> dailyOrderList;

    public OrderStatDto(int orderPrice, int deliveryPrice, int totalPrice, Double avgOrderPrice, List<DailyOrderStatDto> dailyOrderList) {
        this.orderPrice = orderPrice;
        this.deliveryPrice = deliveryPrice;
        this.totalPrice = totalPrice;
        this.avgOrderPrice = avgOrderPrice;
        this.dailyOrderList = dailyOrderList;
    }

    public OrderStatDto() {
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

    public Double getAvgOrderPrice() {
        return avgOrderPrice;
    }

    public void setAvgOrderPrice(Double avgOrderPrice) {
        this.avgOrderPrice = avgOrderPrice;
    }

    public List<DailyOrderStatDto> getDailyOrderList() {
        return dailyOrderList;
    }

    public void setDailyOrderList(List<DailyOrderStatDto> dailyOrderList) {
        this.dailyOrderList = dailyOrderList;
    }
}
