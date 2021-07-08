package com.example.it_management.ui.KnowledgeBase;

import com.example.it_management.ui.Clients.ClientsModel;

import java.util.List;

public class KnowledgeBaseCategoryResponseModel {
    private String message;
    private List<KnowledgeBaseCategoryModel> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<KnowledgeBaseCategoryModel> getData() {
        return data;
    }

    public void setData(List<KnowledgeBaseCategoryModel> data) {
        this.data = data;
    }
}
