package com.example.it_management.ui.Clients;

public class ClientsModel {
    private int id;
    private String name, asset_tag_prefix, license_tag_prefix, notes;

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

    public String getAsset_tag_prefix() {
        return asset_tag_prefix;
    }

    public void setAsset_tag_prefix(String asset_tag_prefix) {
        this.asset_tag_prefix = asset_tag_prefix;
    }

    public String getLicense_tag_prefix() {
        return license_tag_prefix;
    }

    public void setLicense_tag_prefix(String license_tag_prefix) {
        this.license_tag_prefix = license_tag_prefix;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return name;
    }
}