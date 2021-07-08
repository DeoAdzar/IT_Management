package com.example.it_management.ui.People.Users;



public class UsersModel {
    private int id;
    private String name,email,namaClient,namaRole;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNamaClient() {
        return namaClient;
    }

    public void setNamaClient(String namaClient) {
        this.namaClient = namaClient;
    }

    public String getNamaRole() {
        return namaRole;
    }

    public void setNamaRole(String namaRole) {
        this.namaRole = namaRole;
    }
}
