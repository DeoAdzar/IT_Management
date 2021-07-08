package com.example.it_management.ui.Issues.ActiveIssues;


import java.util.List;

public class ActiveIssuesResponseModel {
    private String message;
    private List<ActiveIssuesModel> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ActiveIssuesModel> getData() {
        return data;
    }

    public void setData(List<ActiveIssuesModel> data) {
        this.data = data;
    }
}
