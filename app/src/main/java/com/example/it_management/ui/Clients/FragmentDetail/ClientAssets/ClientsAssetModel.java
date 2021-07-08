package com.example.it_management.ui.Clients.FragmentDetail.ClientAssets;

import java.io.Serializable;

public class ClientsAssetModel implements Serializable {
    private int id,categoryid,adminid,clientid,userid,manufacturerid,modelid,supplierid,warranty_months,locationid,statusid;
    private String purchase_date,tag,name,serial,notes,customfields,qrvalue;

    public int getId() {
        return id;
    }

    public int getStatusid() {
        return statusid;
    }

    public void setStatusid(int statusid) {
        this.statusid = statusid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public int getAdminid() {
        return adminid;
    }

    public void setAdminid(int adminid) {
        this.adminid = adminid;
    }

    public int getClientid() {
        return clientid;
    }

    public void setClientid(int clientid) {
        this.clientid = clientid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getManufacturerid() {
        return manufacturerid;
    }

    public void setManufacturerid(int manufacturerid) {
        this.manufacturerid = manufacturerid;
    }

    public int getModelid() {
        return modelid;
    }

    public void setModelid(int modelid) {
        this.modelid = modelid;
    }

    public int getSupplierid() {
        return supplierid;
    }

    public void setSupplierid(int supplierid) {
        this.supplierid = supplierid;
    }

    public int getWarranty_months() {
        return warranty_months;
    }

    public void setWarranty_months(int warranty_months) {
        this.warranty_months = warranty_months;
    }

    public int getLocationid() {
        return locationid;
    }

    public void setLocationid(int locationid) {
        this.locationid = locationid;
    }

    public String getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(String purchase_date) {
        this.purchase_date = purchase_date;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCustomfields() {
        return customfields;
    }

    public void setCustomfields(String customfields) {
        this.customfields = customfields;
    }

    public String getQrvalue() {
        return qrvalue;
    }

    public void setQrvalue(String qrvalue) {
        this.qrvalue = qrvalue;
    }

    @Override
    public String toString() {
        return name;
    }

}
