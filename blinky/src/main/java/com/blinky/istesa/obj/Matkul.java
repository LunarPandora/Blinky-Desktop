package com.blinky.istesa.obj;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@SuppressWarnings("unchecked")
public class Matkul {
    private final StringProperty idMatkul = new SimpleStringProperty();
    private final StringProperty nmMatkul = new SimpleStringProperty();
    private final StringProperty tglDitambah = new SimpleStringProperty();
    private final StringProperty tglDiupdate = new SimpleStringProperty();

    public Matkul(Object obj){
        List<String> list = ((ArrayList<String>) obj);

        idMatkul.set(list.get(0).toString());
        nmMatkul.set(list.get(1).toString());
        tglDitambah.set(list.get(2).toString());
        tglDiupdate.set(list.get(3).toString());
    }

    public String getIdMatkul() {
        return idMatkul.get();
    }

    public void setIdMatkul(String idMatkul) {
        this.idMatkul.set(idMatkul);
    }

    public StringProperty idMatkulProperty() {
        return idMatkul;
    }

    public String getNmMatkul() {
        return nmMatkul.get();
    }

    public void setNmMatkul(String nmMatkul) {
        this.nmMatkul.set(nmMatkul);
    }

    public StringProperty nmMatkulProperty() {
        return nmMatkul;
    }

    public String getTglDitambah() {
        return tglDitambah.get();
    }

    public void setTglDitambah(String tglDitambah) {
        this.tglDitambah.set(tglDitambah);
    }

    public StringProperty tglDitambahProperty() {
        return tglDitambah;
    }

    public String getTglDiupdate() {
        return tglDiupdate.get();
    }

    public void setTglDiupdate(String tglDiupdate) {
        this.tglDiupdate.set(tglDiupdate);
    }

    public StringProperty tglDiupdateProperty() {
        return tglDiupdate;
    }
}
