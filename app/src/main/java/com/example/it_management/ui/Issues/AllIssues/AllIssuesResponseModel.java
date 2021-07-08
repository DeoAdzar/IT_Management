package com.example.it_management.ui.Issues.AllIssues;


import java.util.List;

public class AllIssuesResponseModel {
    private String message;
    private List<AllIssuesModel> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<AllIssuesModel> getData() {
        return data;
    }

    public void setData(List<AllIssuesModel> data) {
        this.data = data;
    }
}
