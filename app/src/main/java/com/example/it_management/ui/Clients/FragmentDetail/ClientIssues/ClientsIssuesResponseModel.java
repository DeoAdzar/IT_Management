package com.example.it_management.ui.Clients.FragmentDetail.ClientIssues;

import com.example.it_management.ui.Clients.FragmentDetail.ClientFiles.ClientsFilesModel;

import java.util.List;

public class ClientsIssuesResponseModel {
    private String message;
    private List<ClientsIssuesModel> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ClientsIssuesModel> getData() {
        return data;
    }

    public void setData(List<ClientsIssuesModel> data) {
        this.data = data;
    }
}
