package ru.mycity.core.controller.dto.complaint;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class ComplaintDto {
    @JsonProperty(value = "complaintId")
    private long complaintId;
    private String message;
    private String address;
    private String category;
    @JsonProperty(value = "creation_time")
    private Timestamp creationTime;
    private String status;

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
