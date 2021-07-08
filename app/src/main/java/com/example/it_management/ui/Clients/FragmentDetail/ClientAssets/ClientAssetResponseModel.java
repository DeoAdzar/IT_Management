package com.example.it_management.ui.Clients.FragmentDetail.ClientAssets;

import java.util.List;

public class ClientAssetResponseModel {
     private String message;
    private List<ClientsAssetModel> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ClientsAssetModel> getData() {
        return data;
    }

    public void setData(List<ClientsAssetModel> data) {
        this.data = data;
    }
}
