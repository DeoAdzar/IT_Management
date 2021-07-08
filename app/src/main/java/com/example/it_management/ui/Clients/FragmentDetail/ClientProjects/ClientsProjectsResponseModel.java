package com.example.it_management.ui.Clients.FragmentDetail.ClientProjects;

import com.example.it_management.ui.Projects.ProjectsModel;

import java.util.List;

public class ClientsProjectsResponseModel {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ClientsProjectsModel> getData() {
        return data;
    }

    public void setData(List<ClientsProjectsModel> data) {
        this.data = data;
    }

    private List<ClientsProjectsModel> data;
}
