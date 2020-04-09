package ru.mycity.core.service.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Fields {
    private Issuetype issuetype;
    private Project project;
    @JsonProperty("customfield_10057")
    private Comment comment;
    private Description description;
    @JsonProperty("customfield_10049")
    private String customerName;
    private String summary;
    @JsonProperty("customfield_10054")
    private String address;
    @JsonProperty("customfield_10055")
    private String email;
    @JsonProperty("customfield_10056")
    private String phone;
    @JsonProperty("customfield_10048")
    private String flat;

    public Fields() {
    }

    public Issuetype getIssuetype() {
        return issuetype;
    }

    public void setIssuetype(Issuetype issuetype) {
        this.issuetype = issuetype;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }
}
