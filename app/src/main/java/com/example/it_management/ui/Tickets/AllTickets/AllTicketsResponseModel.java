package com.example.it_management.ui.Tickets.AllTickets;

import com.example.it_management.ui.Tickets.AllTickets.AllTicketsModel;

import java.util.List;

public class AllTicketsResponseModel {
    private String message;
    private List<AllTicketsModel> data;
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<AllTicketsModel> getData() {
        return data;
    }

    public void setData(List<AllTicketsModel> data) {
        this.data = data;
    }


}
