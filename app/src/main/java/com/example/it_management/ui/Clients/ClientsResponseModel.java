package com.example.it_management.ui.Clients;

import java.util.List;

public class ClientsResponseModel {
    private String message;
    private List<ClientsModel> data;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ClientsModel> getdata() {
        return data;
    }

    public void setdata(List<ClientsModel> data) {
        this.data = data;
    }


}
