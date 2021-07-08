package com.example.it_management.ui.Inventory.Assets.FragmentDetail.FilesAssets;


import com.example.it_management.ui.Clients.FragmentDetail.ClientFiles.ClientsFilesModel;

import java.util.List;

public class AssetsFilesResponseModel {
    private String message;
    private List<AssetsFilesModel> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<AssetsFilesModel> getData() {
        return data;
    }

    public void setData(List<AssetsFilesModel> data) {
        this.data = data;
    }
}
