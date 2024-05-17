package com.blinky.istesa.Model;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@SuppressWarnings("unchecked")
public class Admin {
    private final StringProperty idAdmin = new SimpleStringProperty();
    private final StringProperty nmAdmin = new SimpleStringProperty();
    private final StringProperty uAdmin = new SimpleStringProperty();
    private final StringProperty pwAdmin = new SimpleStringProperty();
    private final StringProperty fotoAdmin = new SimpleStringProperty();
    private final StringProperty tglDitambah = new SimpleStringProperty();
    private final StringProperty tglDiupdate = new SimpleStringProperty();

    public Admin(Object obj) {
        List<String> list = ((ArrayList<String>) obj);

        idAdmin.set(list.get(0).toString());
        nmAdmin.set(list.get(1).toString());
        uAdmin.set(list.get(2).toString());
        pwAdmin.set(list.get(3).toString());
        fotoAdmin.set(list.get(4).toString());
        tglDitambah.set(list.get(5).toString());
        tglDiupdate.set(list.get(6).toString());
    }

    public StringProperty idAdminProperty() {
        return idAdmin;
    }

    public String getIdAdmin() {
        return idAdmin.get();
    }

    public void setIdAdmin(String idAdmin) {
        this.idAdmin.set(idAdmin);
    }

    public StringProperty nmAdminProperty() {
        return nmAdmin;
    }

    public String getNmAdmin() {
        return nmAdmin.get();
    }

    public void setNmAdmin(String nmAdmin) {
        this.nmAdmin.set(nmAdmin);
    }

    public StringProperty uAdminProperty() {
        return uAdmin;
    }

    public String getUAdmin() {
        return uAdmin.get();
    }

    public void setUAdmin(String uAdmin) {
        this.uAdmin.set(uAdmin);
    }

    public StringProperty pwAdminProperty() {
        return pwAdmin;
    }

    public String getPwAdmin() {
        return pwAdmin.get();
    }

    public void setPwAdmin(String pwAdmin) {
        this.pwAdmin.set(pwAdmin);
    }

    public StringProperty fotoAdminProperty() {
        return fotoAdmin;
    }

    public String getFotoAdmin() {
        return fotoAdmin.get();
    }

    public void setFotoAdmin(String fotoAdmin) {
        this.fotoAdmin.set(fotoAdmin);
    }

    public StringProperty tglDitambahProperty() {
        return tglDitambah;
    }

    public String getTglDitambah() {
        return tglDitambah.get();
    }

    public void setTglDitambah(String tglDitambah) {
        this.tglDitambah.set(tglDitambah);
    }

    public StringProperty tglDiupdateProperty() {
        return tglDiupdate;
    }

    public String getTglDiupdate() {
        return tglDiupdate.get();
    }

    public void setTglDiupdate(String tglDiupdate) {
        this.tglDiupdate.set(tglDiupdate);
    }
}
