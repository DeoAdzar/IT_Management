package com.example.it_management.ui.People.Contacs;


import java.util.List;

public class ContacsResponseModel {
    private String message;
    private List<ContacsModel> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ContacsModel> getData() {
        return data;
    }

    public void setData(List<ContacsModel> data) {
        this.data = data;
    }
}
