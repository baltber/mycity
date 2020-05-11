package ru.mycity.core.service.dao.model;

import java.sql.Timestamp;

public class DateTimeModel {
    private Timestamp startDate;
    private Timestamp endDate;

    public DateTimeModel(Timestamp startDate, Timestamp endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public DateTimeModel() {
    }

    public Timestamp getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }
}