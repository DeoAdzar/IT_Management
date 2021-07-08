package com.example.it_management.ui.Inventory.Attributes.LicenseCategories;


import java.util.List;

public class LicenseCategoryResponseModel {
    private String message;
    private List<LicenseCategoryModel> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<LicenseCategoryModel> getData() {
        return data;
    }

    public void setData(List<LicenseCategoryModel> data) {
        this.data = data;
    }
}
