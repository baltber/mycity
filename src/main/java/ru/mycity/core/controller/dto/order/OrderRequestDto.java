package ru.mycity.core.controller.dto.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(description = "Запрос добавления заказа")
public class OrderRequestDto {

    @ApiModelProperty(value = "ФИО клиента")
    private String name;
    @ApiModelProperty(value = "Адрес доставки")
    private String address;
    @ApiModelProperty(value = "Квартира доставки")
    private String flat;
    @ApiModelProperty(value = "Телефон клиента")
    private String phone;
    @ApiModelProperty(value = "email клиента")
    private String email;
    @ApiModelProperty(value = "Данные заказа")
    private List<OrderDto> order;

    public OrderRequestDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<OrderDto> getOrder() {
        return order;
    }

    public void setOrder(List<OrderDto> order) {
        this.order = order;
    }
}
