package com.example.it_management.ui.Inventory.Attributes.Locations;


import java.util.List;

public class LocationsResponseModel {
    private String message;
    private List<LocationsModel> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<LocationsModel> getData() {
        return data;
    }

    public void setData(List<LocationsModel> locationsModelList) {
        this.data = locationsModelList;
    }
}
