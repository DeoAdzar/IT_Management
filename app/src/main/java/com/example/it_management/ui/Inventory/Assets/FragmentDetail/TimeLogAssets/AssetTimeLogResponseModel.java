package com.example.it_management.ui.Inventory.Assets.FragmentDetail.TimeLogAssets;

import com.example.it_management.ui.Clients.FragmentDetail.ClientTimeLog.ClientsTimeLogModel;

import java.util.List;

public class AssetTimeLogResponseModel {
    private String message;
    private List<AssetTimeLogModel> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<AssetTimeLogModel> getData() {
        return data;
    }

    public void setData(List<AssetTimeLogModel> data) {
        this.data = data;
    }
}
