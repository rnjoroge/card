package com.logicea.card.entity;

import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "user_id", length = 50)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name = "user_email", length = 50)
    @Size(max = 20, min = 3, message = "{user.email.invalid}")
    @NotEmpty(message = "Email is required")
    private String email;

    @Column(name = "username", length = 50)
    @Size(max = 20, min = 3, message = "{username.invalid}")
    @NotEmpty(message = "Username is required")
    private String username;

    @Column(name = "password", length = 100)
    @Size(max = 100, min = 3, message = "{user.password.invalid}")
    @NotEmpty(message = "Password is required")
    private String password;

    
    @Column(name = "user_role", length = 20)
    private String userRole;


    @Column(name = "created_date", length = 50)
    private Date userCreatedDate;
    
    public User() {}

    public User (int userId,String email,String username ,String password,Date userCreatedDate,String userRole) {
        this.userId=userId;
        this.email=email;
        this.username=username;
        this.password=password;
        this.userCreatedDate=userCreatedDate;
        this.userRole=userRole;

    }

    public int getUserId () {
        return this.userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail () {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername () {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword () {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    } 
    
    public Date getUserCreatedDate () {
        return this.userCreatedDate;
    }
    public void setUserCreatedDate(Date userCreatedDate) {
        this.userCreatedDate = userCreatedDate;
    }  
    
    public String getUserRole () {
        return this.userRole;
    }
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

        
    @Override
    public String toString() {
        return "Task{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password=" + password +               
                ", userRole=" + userRole +
                ", userCreatedDate=" + userCreatedDate.toString() +
                '}';
    }
}
