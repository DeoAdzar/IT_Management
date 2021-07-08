package com.example.it_management.ui.Clients.FragmentDetail.ClientFiles;


import java.util.List;

public class ClientsFilesResponseModel {
    private String message;
    private List<ClientsFilesModel> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ClientsFilesModel> getData() {
        return data;
    }

    public void setData(List<ClientsFilesModel> data) {
        this.data = data;
    }
}
