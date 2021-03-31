package com.openclassroom.paymybuddy.DTO;

import com.openclassroom.paymybuddy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

public class UserInfo {
    private Integer id;
    private String firstName;
    private String lastName;
    @Email
    @NotNull
    private String email;

    // constructors

    public UserInfo() {
    }

    public UserInfo(Integer id, String firstName, String lastName, @Email @NotNull String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public UserInfo(String firstName, String lastName, @Email @NotNull String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }



    // getter & setter

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



//    public UserInfo findByEmail(String email) extends JpaRepository<User, String> {
//        UserInfo tempUser = new UserInfo();
//        tempUser.findByEmail(email);
//
//
//
//    }
}
