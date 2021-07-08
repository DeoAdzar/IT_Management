package com.example.it_management.ui.Clients.FragmentDetail.ClientTickets;

import com.example.it_management.ui.Clients.FragmentDetail.ClientProjects.ClientsProjectsModel;

import java.util.List;

public class ClientsTicketsResponseModel {
    private String message;
    private List<ClientsTicketsModel> data;
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ClientsTicketsModel> getData() {
        return data;
    }

    public void setData(List<ClientsTicketsModel> data) {
        this.data = data;
    }


}
