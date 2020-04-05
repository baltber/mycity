package ru.mycity.core.controller.dto.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Данные заказа")
public class OrderDto {

    @ApiModelProperty(value = "Наименование блюда")
    private String name;
    @ApiModelProperty(value = "Количество порций")
    private String quantity;
    @ApiModelProperty(value = "Стоимость одной порции")
    private String price;
    @ApiModelProperty(value = "Цена")
    private String amount;

    public String getName() {
        return name;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getPrice() {
        return price;
    }

    public String getAmount() {
        return amount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}