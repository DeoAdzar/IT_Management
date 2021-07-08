package com.example.it_management.ui.Clients.FragmentDetail.ClientTimeLog;

import com.example.it_management.ui.Clients.FragmentDetail.ClientTickets.ClientsTicketsModel;

import java.util.List;

public class ClientsTimeLogResponseModel {
    private String message;
    private List<ClientsTimeLogModel> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ClientsTimeLogModel> getData() {
        return data;
    }

    public void setData(List<ClientsTimeLogModel> data) {
        this.data = data;
    }
}
