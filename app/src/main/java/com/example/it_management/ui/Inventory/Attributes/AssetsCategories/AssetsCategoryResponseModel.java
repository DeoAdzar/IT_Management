package com.example.it_management.ui.Inventory.Attributes.AssetsCategories;

import java.util.List;

public class AssetsCategoryResponseModel {
    private String message;
    private List<AssetsCategoryModel> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<AssetsCategoryModel> getData() {
        return data;
    }

    public void setData(List<AssetsCategoryModel> data) {
        this.data = data;
    }
}
