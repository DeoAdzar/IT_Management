package com.example.it_management.ui.Clients.FragmentDetail.ClientLicense;


import java.util.List;

public class ClientsLicenseResponseModel {
    private String message;
    private List<ClientsLicenseModel> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ClientsLicenseModel> getData() {
        return data;
    }

    public void setData(List<ClientsLicenseModel> data) {
        this.data = data;
    }
}
