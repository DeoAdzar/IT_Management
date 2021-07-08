package com.example.it_management.ui.Tickets.ActiveTickets;

public class ActiveTicketsModel {
    private int id,ticket,departmentid,clientid,userid,adminid,assetid,projectid,timespent;
    private String email,subject,status,priority,timestamp,notes,ccs,nama;

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

    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }

    public int getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(int departmentid) {
        this.departmentid = departmentid;
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

    public int getAdminid() {
        return adminid;
    }

    public void setAdminid(int adminid) {
        this.adminid = adminid;
    }

    public int getAssetid() {
        return assetid;
    }

    public void setAssetid(int assetid) {
        this.assetid = assetid;
    }

    public int getProjectid() {
        return projectid;
    }

    public void setProjectid(int projectid) {
        this.projectid = projectid;
    }

    public int getTimespent() {
        return timespent;
    }

    public void setTimespent(int timespent) {
        this.timespent = timespent;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCcs() {
        return ccs;
    }

    public void setCcs(String ccs) {
        this.ccs = ccs;
    }
}
