package com.example.it_management.ui.Tickets.AwaitingReply;

import com.example.it_management.ui.Clients.FragmentDetail.ClientTickets.ClientsTicketsModel;

import java.util.List;

public class AwaitingReplyResponseModel {
    private String message;
    private List<AwaitingReplyModel> data;
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<AwaitingReplyModel> getData() {
        return data;
    }

    public void setData(List<AwaitingReplyModel> data) {
        this.data = data;
    }


}
