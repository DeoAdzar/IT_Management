package com.example.it_management.ui.Clients.FragmentDetail.ClientUser;

import com.example.it_management.ui.Clients.FragmentDetail.ClientTimeLog.ClientsTimeLogModel;

import java.util.List;

public class ClientsUserResponseModel {
    private String message;
    private List<ClientsUserModel> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ClientsUserModel> getData() {
        return data;
    }

    public void setData(List<ClientsUserModel> data) {
        this.data = data;
    }
}
