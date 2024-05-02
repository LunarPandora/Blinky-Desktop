package com.blinky.istesa.obj;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@SuppressWarnings("unchecked")
public class Dosen {
    private final StringProperty idDosen = new SimpleStringProperty();
    private final StringProperty nmDosen = new SimpleStringProperty();
    private final StringProperty uDosen = new SimpleStringProperty();
    private final StringProperty pwDosen = new SimpleStringProperty();
    private final StringProperty fotoDosen = new SimpleStringProperty();
    private final StringProperty nidn = new SimpleStringProperty();
    private final StringProperty tglDitambah = new SimpleStringProperty();
    private final StringProperty tglDiupdate = new SimpleStringProperty();

    public Dosen(Object obj) {
        List<String> list = ((ArrayList<String>) obj);

        idDosen.set(list.get(0).toString());
        nmDosen.set(list.get(1).toString());
        uDosen.set(list.get(2).toString());
        pwDosen.set(list.get(3).toString());
        fotoDosen.set(list.get(4).toString());
        nidn.set(list.get(5).toString());
        tglDitambah.set(list.get(6).toString());
        tglDiupdate.set(list.get(7).toString());
    }

    public StringProperty idDosenProperty() {
        return idDosen;
    }

    public String getIdDosen() {
        return idDosen.get();
    }

    public void setIdDosen(String idDosen) {
        this.idDosen.set(idDosen);
    }

    public StringProperty nmDosenProperty() {
        return nmDosen;
    }

    public String getNmDosen() {
        return nmDosen.get();
    }

    public void setNmDosen(String nmDosen) {
        this.nmDosen.set(nmDosen);
    }

    public StringProperty uDosenProperty() {
        return uDosen;
    }

    public String getUDosen() {
        return uDosen.get();
    }

    public void setUDosen(String uDosen) {
        this.uDosen.set(uDosen);
    }

    public StringProperty pwDosenProperty() {
        return pwDosen;
    }

    public String getPwDosen() {
        return pwDosen.get();
    }

    public void setPwDosen(String pwDosen) {
        this.pwDosen.set(pwDosen);
    }

    public StringProperty fotoDosenProperty() {
        return fotoDosen;
    }

    public String getFotoDosen() {
        return fotoDosen.get();
    }

    public void setFotoDosen(String fotoDosen) {
        this.fotoDosen.set(fotoDosen);
    }

    public StringProperty nidnProperty() {
        return nidn;
    }

    public String getNidn() {
        return nidn.get();
    }

    public void setNidn(String nidn) {
        this.nidn.set(nidn);
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
