package com.example.it_management.ui.Clients.FragmentDetail.ClientLicense;

public class ClientsLicenseModel {
    private int id,clientid,statusid,categoryid,supplierid;
    private String seats,tag,name,serial,notes,customfields,qrvalue,nama,statusname;

    public String getStatusname() {
        return statusname;
    }

    public void setStatusname(String statusname) {
        this.statusname = statusname;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientid() {
        return clientid;
    }

    public void setClientid(int clientid) {
        this.clientid = clientid;
    }

    public int getStatusid() {
        return statusid;
    }

    public void setStatusid(int statusid) {
        this.statusid = statusid;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public int getSupplierid() {
        return supplierid;
    }

    public void setSupplierid(int supplierid) {
        this.supplierid = supplierid;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
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
}
