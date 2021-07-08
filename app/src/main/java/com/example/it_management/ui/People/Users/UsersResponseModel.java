package com.example.it_management.ui.People.Users;

import com.example.it_management.ui.People.Staff.StaffModel;

import java.util.List;

public class UsersResponseModel {
    private String message;
    private List<UsersModel> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<UsersModel> getData() {
        return data;
    }

    public void setData(List<UsersModel> data) {
        this.data = data;
    }
}
