package com.example.it_management.ui.Inventory.Assets.FragmentDetail.TicketsAssets;

import com.example.it_management.ui.Clients.FragmentDetail.ClientTickets.ClientsTicketsModel;

import java.util.List;

public class AssetTicketsResponseModel {
    private String message;
    private List<AssetTicketsModel> data;
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<AssetTicketsModel> getData() {
        return data;
    }

    public void setData(List<AssetTicketsModel> data) {
        this.data = data;
    }


}
