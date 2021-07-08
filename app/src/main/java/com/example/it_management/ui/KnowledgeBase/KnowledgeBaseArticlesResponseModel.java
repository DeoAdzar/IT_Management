package com.example.it_management.ui.KnowledgeBase;

import com.example.it_management.ui.Clients.ClientsModel;

import java.util.List;

public class KnowledgeBaseArticlesResponseModel {
    private String message;
    private List<KnowledgeBaseArticlesModel> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<KnowledgeBaseArticlesModel> getData() {
        return data;
    }

    public void setData(List<KnowledgeBaseArticlesModel> data) {
        this.data = data;
    }
}
