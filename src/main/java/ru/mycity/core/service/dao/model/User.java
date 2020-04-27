package ru.mycity.core.service.dao.model;

import ru.mycity.core.controller.dto.user.UserDto;

public class User {

    private long userId;
    private String firstName;
    private String lastName;
    private String address;
    private int flat;
    private String login;
    private String password;
    private String location;
    private String role;
    private String userGuid;
    private String organisationGuid;
    private long organisationId;

    public User(long userId,String firstName, String lastName, String address, int flat, String login, String password, String location, String role, String userGuid,
                String organisationGuid) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.flat = flat;
        this.login = login;
        this.password = password;
        this.location = location;
        this.role = role;
        this.userGuid = userGuid;
        this.organisationGuid = organisationGuid;
    }

    public User(String firstName, String lastName, String address, int flat, String login, String password, String location, String role, String userGuid) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.flat = flat;
        this.login = login;
        this.password = password;
        this.location = location;
        this.role = role;
        this.userGuid = userGuid;
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
        dto.setUserId(userGuid);
        dto.setOrganisationGuid(organisationGuid);
        return dto;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public String getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }

    public String getOrganisationGuid() {
        return organisationGuid;
    }

    public void setOrganisationGuid(String organisationGuid) {
        this.organisationGuid = organisationGuid;
    }

    public long getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(long organisationId) {
        this.organisationId = organisationId;
    }
}
