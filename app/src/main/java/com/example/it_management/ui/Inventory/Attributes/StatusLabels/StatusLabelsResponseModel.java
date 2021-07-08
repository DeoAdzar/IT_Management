package com.example.it_management.ui.Inventory.Attributes.StatusLabels;


import java.util.List;

public class StatusLabelsResponseModel {
    private String message;
    private List<StatusLabelsModel> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<StatusLabelsModel> getData() {
        return data;
    }

    public void setData(List<StatusLabelsModel> StatusLabelsModelList) {
        this.data = StatusLabelsModelList;
    }
}
