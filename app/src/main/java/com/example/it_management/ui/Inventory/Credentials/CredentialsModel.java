package com.example.it_management.ui.Inventory.Credentials;



public class CredentialsModel{
    private int id;
    private String nama_client, nama_assets, type, username,password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama_client() {
        return nama_client;
    }

    public void setNama_client(String nama_client) {
        this.nama_client = nama_client;
    }

    public String getNama_assets() {
        return nama_assets;
    }

    public void setNama_assets(String nama_assets) {
        this.nama_assets = nama_assets;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
