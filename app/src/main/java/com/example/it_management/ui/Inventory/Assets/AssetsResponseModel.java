package com.example.it_management.ui.Inventory.Assets;

import com.example.it_management.ui.Clients.ClientsModel;
import com.example.it_management.ui.Clients.FragmentDetail.ClientAssets.ClientsAssetModel;

import java.util.List;

public class AssetsResponseModel {
    private String message;
    private List<AssetsModel> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<AssetsModel> getData() {
        return data;
    }

    public void setData(List<AssetsModel> data) {
        this.data = data;
    }
}
