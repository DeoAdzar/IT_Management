package com.example.it_management.ui.Inventory.Assets.FragmentDetail.FilesAssets;

public class AssetsFilesModel {
    private int id,clientid,projectid,assetid,ticketreplyid;
    private String name,file;

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

    public int getProjectid() {
        return projectid;
    }

    public void setProjectid(int projectid) {
        this.projectid = projectid;
    }

    public int getAssetid() {
        return assetid;
    }

    public void setAssetid(int assetid) {
        this.assetid = assetid;
    }

    public int getTicketreplyid() {
        return ticketreplyid;
    }

    public void setTicketreplyid(int ticketreplyid) {
        this.ticketreplyid = ticketreplyid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
