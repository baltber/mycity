package ru.mycity.core.controller.dto.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Запрос добавления заказа")
public class OrderRequestDto {

    @ApiModelProperty(value = "Наименование заказа")
    private String summary;
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
    @ApiModelProperty(value = "Комментарий к заказу")
    private String comment;
    @ApiModelProperty(value = "Данные заказа")
    private OrderList orderList;
    @ApiModelProperty(value = "GUID оргаизации")
    private String organisationGuid;

    public OrderRequestDto() {
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public OrderList getOrderList() {
        return orderList;
    }

    public void setOrderList(OrderList orderList) {
        this.orderList = orderList;
    }

    public String getOrganisationGuid() {
        return organisationGuid;
    }

    public void setOrganisationGuid(String organisationGuid) {
        this.organisationGuid = organisationGuid;
    }
}
