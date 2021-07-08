package com.example.it_management.ui.People.Staff;

import com.example.it_management.ui.People.Roles.RolesModel;

import java.util.List;

public class StaffResponseModel {
    private String message;
    private List<StaffModel> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<StaffModel> getData() {
        return data;
    }

    public void setData(List<StaffModel> data) {
        this.data = data;
    }
}
