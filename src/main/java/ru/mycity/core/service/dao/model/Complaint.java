package ru.mycity.core.service.dao.model;


import ru.mycity.core.controller.dto.ComplaintDto;

import java.sql.Timestamp;

public class Complaint {

    private long complaintId;
    private String message;
    private String address;
    private String category;
    private Timestamp creationTime;
    private String status;

    public ComplaintDto toDto(){
        ComplaintDto dto = new ComplaintDto();
        dto.setComplaintId(complaintId);
        dto.setMessage(message);
        dto.setAddress(address);
        dto.setCategory(category);
        dto.setCreationTime(creationTime);
        dto.setStatus(status);
        return dto;
    }

    public Complaint() {
    }

    public Complaint(long complaintId, String message, String address, String category, Timestamp creationTime, String status) {
        this.complaintId = complaintId;
        this.message = message;
        this.address = address;
        this.category = category;
        this.creationTime = creationTime;
        this.status = status;
    }

    public long getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(long complaintId) {
        this.complaintId = complaintId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
