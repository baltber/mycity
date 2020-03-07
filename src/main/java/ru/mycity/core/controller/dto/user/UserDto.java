package ru.mycity.core.controller.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.mycity.core.service.dao.model.User;

public class UserDto {

    @JsonProperty("name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("address")
    private String address;
    @JsonProperty("apartment")
    private int flat;
    @JsonProperty("email")
    private String login;
    @JsonProperty("password")
    private String password;
    @JsonProperty("location")
    private String location;
    @JsonProperty("role")
    private String role;
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("auth")
    private int auth;

    public User toEntity(){
        return new User(
                firstName,
                lastName,
                address,
                flat,
                login,
                password,
                location,
                role,
                userId
        );
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getFlat() {
        return flat;
    }

    public void setFlat(int flat) {
        this.flat = flat;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getAuth() {
        return auth;
    }

    public void setAuth(int auth) {
        this.auth = auth;
    }
}
