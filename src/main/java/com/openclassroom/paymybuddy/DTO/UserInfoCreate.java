package com.openclassroom.paymybuddy.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UserInfoCreate {

    // fields

    private String firstName;
    private String lastName;
    @Email
    @NotNull
    private String email;
    private String password;

    // construtor

    public UserInfoCreate() {
    }

    public UserInfoCreate(String firstName, String lastName, @Email @NotNull String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    // getters & setters


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // to string


    @Override
    public String toString() {
        return "UserInfoCreate{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
