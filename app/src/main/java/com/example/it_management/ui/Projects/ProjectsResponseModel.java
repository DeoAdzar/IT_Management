package com.example.it_management.ui.Projects;

import java.util.List;

public class ProjectsResponseModel{
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ProjectsModel> getData() {
        return data;
    }

    public void setData(List<ProjectsModel> data) {
        this.data = data;
    }

    private List<ProjectsModel> data;
}
