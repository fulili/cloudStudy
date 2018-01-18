package com.example.a.cloudstudy.entity;

import java.util.Date;

/**
 * Created by jfl on 2018/1/16.
 */

public class UserRequest {
    private Integer id;
    private String username;
    private String userpassword;
    private int usertype;
    private int usersex;
    private String useremail;
    private String userphone;
    private String userhead;
    private Date userbirth;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public int getUsertype() {
        return usertype;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }

    public int getUsersex() {
        return usersex;
    }

    public void setUsersex(int usersex) {
        this.usersex = usersex;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getUserhead() {
        return userhead;
    }

    public void setUserhead(String userhead) {
        this.userhead = userhead;
    }

    public Date getUserbirth() {
        return userbirth;
    }

    public void setUserbirth(Date userbirth) {
        this.userbirth = userbirth;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", userpassword='" + userpassword + '\'' +
                ", usertype=" + usertype +
                '}';
    }
}
