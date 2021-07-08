package com.example.it_management.ui.Inventory.Assets.FragmentDetail.IssuesAssets;

import com.example.it_management.ui.Clients.FragmentDetail.ClientIssues.ClientsIssuesModel;

import java.util.List;

public class AssetIssuesResponseModel {
    private String message;
    private List<AssetIssuesModel> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<AssetIssuesModel> getData() {
        return data;
    }

    public void setData(List<AssetIssuesModel> data) {
        this.data = data;
    }
}
