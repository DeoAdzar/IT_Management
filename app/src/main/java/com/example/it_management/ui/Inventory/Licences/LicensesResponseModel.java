package com.example.it_management.ui.Inventory.Licences;

import com.example.it_management.ui.Clients.ClientsModel;

import java.util.List;

public class LicensesResponseModel {
    private String message;
    private List<LicencesModel> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<LicencesModel> getData() {
        return data;
    }

    public void setData(List<LicencesModel> data) {
        this.data = data;
    }
}
