package com.example.it_management.ui.Clients.FragmentDetail.ClientCredential;

import com.example.it_management.ui.Clients.FragmentDetail.ClientAssets.ClientsAssetModel;

import java.util.List;

public class ClientCredentialResponseModel {
     private String message;
    private List<ClientCredentialModel> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ClientCredentialModel> getData() {
        return data;
    }

    public void setData(List<ClientCredentialModel> data) {
        this.data = data;
    }
}
