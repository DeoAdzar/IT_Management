package com.example.it_management.ui.Inventory.Attributes.Manufacturers;


import java.util.List;

public class ManufacturersResponseModel {
    private String message;
    private List<ManufacturersModel> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ManufacturersModel> getData() {
        return data;
    }

    public void setData(List<ManufacturersModel> ManufacturersModelList) {
        this.data = ManufacturersModelList;
    }
}
