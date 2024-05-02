package com.blinky.istesa.obj;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@SuppressWarnings("unchecked")
public class StatusAbsensi {
    private final StringProperty idStatusAbsensi = new SimpleStringProperty();
    private final StringProperty nmStatusAbsensi = new SimpleStringProperty();
    private final StringProperty tglDitambah = new SimpleStringProperty();
    private final StringProperty tglDiupdate = new SimpleStringProperty();

    public StatusAbsensi(Object obj){
        List<String> list = ((ArrayList<String>) obj);

        idStatusAbsensi.set(list.get(0).toString());
        nmStatusAbsensi.set(list.get(1).toString());
        tglDitambah.set(list.get(2).toString());
        tglDiupdate.set(list.get(3).toString());
    }

    public String getIdStatusAbsensi() {
        return idStatusAbsensi.get();
    }

    public void setIdStatusAbsensi(String idStatusAbsensi) {
        this.idStatusAbsensi.set(idStatusAbsensi);
    }

    public StringProperty idStatusAbsensiProperty() {
        return idStatusAbsensi;
    }

    public String getNmStatusAbsensi() {
        return nmStatusAbsensi.get();
    }

    public void setNmStatusAbsensi(String nmStatusAbsensi) {
        this.nmStatusAbsensi.set(nmStatusAbsensi);
    }

    public StringProperty nmStatusAbsensiProperty() {
        return nmStatusAbsensi;
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
