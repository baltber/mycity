package ru.mycity.core.controller.dto.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Данные заказа")
public class OrderDto {

    @ApiModelProperty(value = "Наименование блюда")
    private String name;
    @ApiModelProperty(value = "Количество порций")
    private String amount;
    @ApiModelProperty(value = "Стоимость одной порции")
    private String price;
    @ApiModelProperty(value = "Цена")
    private String cost;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}