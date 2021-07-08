package com.example.it_management.ui.Tickets.ActiveTickets;

import com.example.it_management.ui.Tickets.AwaitingReply.AwaitingReplyModel;

import java.util.List;

public class ActiveTicketsResponseModel {
    private String message;
    private List<ActiveTicketsModel> data;
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ActiveTicketsModel> getData() {
        return data;
    }

    public void setData(List<ActiveTicketsModel> data) {
        this.data = data;
    }


}
