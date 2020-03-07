package ru.mycity.core.service.dao.model;

import ru.mycity.core.controller.dto.user.UserDto;

public class User {

    private String firstName;
    private String lastName;
    private String address;
    private int flat;
    private String login;
    private String password;
    private String location;
    private String role;
    private String userId;


    public User(String firstName, String lastName, String address, int flat, String login, String password, String location, String role, String userId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.flat = flat;
        this.login = login;
        this.password = password;
        this.location = location;
        this.role = role;
        this.userId = userId;
    }

    public UserDto toDto(){
        UserDto dto = new UserDto();
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setAddress(address);
        dto.setFlat(flat);
        dto.setLogin(login);
        dto.setPassword(password);
        dto.setLocation(location);
        dto.setRole(role);
        dto.setUserId(userId);
        return dto;
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
}
