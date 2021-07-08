package com.example.it_management.ui.Inventory.Attributes.AssetsModels;

import java.util.List;

public class AssetsModelResponseModel {
    private String message;
    private List<AssetsModelModel> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<AssetsModelModel> getData() {
        return data;
    }

    public void setData(List<AssetsModelModel> data) {
        this.data = data;
    }
}
