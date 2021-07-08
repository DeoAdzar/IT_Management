package com.example.it_management.ui.Dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class DashboardResponseModel {
    private String message;
    private List<DashboardModel> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DashboardModel> getData() {
        return data;
    }

    public void setData(List<DashboardModel> data) {
        this.data = data;
    }


}