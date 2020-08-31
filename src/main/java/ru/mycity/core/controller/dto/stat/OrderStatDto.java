package ru.mycity.core.controller.dto.stat;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class OrderStatDto {
    private int orderPrice;
    private int deliveryPrice;
    private int totalPrice;
    private Double avgOrderPrice;
    private Map<LocalDateTime, List<DailyOrderStatDto>> dailyOrderList;

    public OrderStatDto(int orderPrice, int deliveryPrice, int totalPrice, Double avgOrderPrice, Map<LocalDateTime, List<DailyOrderStatDto>> dailyOrderList) {
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

    public Map<LocalDateTime, List<DailyOrderStatDto>> getDailyOrderList() {
        return dailyOrderList;
    }

    public void setDailyOrderList(Map<LocalDateTime, List<DailyOrderStatDto>> dailyOrderList) {
        this.dailyOrderList = dailyOrderList;
    }
}
