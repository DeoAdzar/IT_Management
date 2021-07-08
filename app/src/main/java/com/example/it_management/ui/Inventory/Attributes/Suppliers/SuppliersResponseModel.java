package com.example.it_management.ui.Inventory.Attributes.Suppliers;


import java.util.List;

public class SuppliersResponseModel {
    private String message;
    private List<SuppliersModel> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<SuppliersModel> getData() {
        return data;
    }

    public void setData(List<SuppliersModel> SuppliersModelList) {
        this.data = SuppliersModelList;
    }
}
