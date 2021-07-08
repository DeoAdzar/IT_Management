package com.example.it_management.ui.People.Roles;

import java.util.List;

public class RolesResponseModel {
    private String message;
    private List<RolesModel> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        message = message;
    }

    public List<RolesModel> getData() {
        return data;
    }

    public void setData(List<RolesModel> data) {
        this.data = data;
    }
}
