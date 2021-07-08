package com.example.it_management.ui.Inventory.Credentials;

import java.util.List;

public class CredentialsResponseModel {
    private String message;
    private List<CredentialsModel> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<CredentialsModel> getData() {
        return data;
    }

    public void setData(List<CredentialsModel> data) {
        this.data = data;
    }
}
