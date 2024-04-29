package com.blinky.istesa.obj;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@SuppressWarnings("unchecked")
public class Absensi {
    private final StringProperty idAbsensi = new SimpleStringProperty();
    private final StringProperty idMahasiswa = new SimpleStringProperty();
    private final StringProperty idJadwal = new SimpleStringProperty();
    private final StringProperty waktuAbsen = new SimpleStringProperty();
    private final StringProperty statusAbsensi = new SimpleStringProperty();
    private final StringProperty tglDitambah = new SimpleStringProperty();
    private final StringProperty tglDiupdate = new SimpleStringProperty();

    public Absensi(Object obj){
        List<String> list = ((ArrayList<String>) obj);

        idAbsensi.set(list.get(0).toString());
        idMahasiswa.set(list.get(1).toString());
        idJadwal.set(list.get(2).toString());
        waktuAbsen.set(list.get(3).toString());
        statusAbsensi.set(list.get(4).toString());
        tglDitambah.set(list.get(5).toString());
        tglDiupdate.set(list.get(6).toString());
    }

    public StringProperty idAbsensiProperty() {
        return idAbsensi;
    }

    public String getIdAbsensi() {
        return idAbsensi.get();
    }

    public void setIdAbsensi(String idAbsensi) {
        this.idAbsensi.set(idAbsensi);
    }

    public StringProperty idMahasiswaProperty() {
        return idMahasiswa;
    }

    public String getIdMahasiswa() {
        return idMahasiswa.get();
    }

    public void setIdMahasiswa(String idMahasiswa) {
        this.idMahasiswa.set(idMahasiswa);
    }

    public StringProperty idJadwalProperty() {
        return idJadwal;
    }

    public String getIdJadwal() {
        return idJadwal.get();
    }

    public void setIdJadwal(String idJadwal) {
        this.idJadwal.set(idJadwal);
    }

    public StringProperty waktuAbsenProperty() {
        return waktuAbsen;
    }

    public String getWaktuAbsen() {
        return waktuAbsen.get();
    }

    public void setWaktuAbsen(String waktuAbsen) {
        this.waktuAbsen.set(waktuAbsen);
    }

    public StringProperty statusAbsensiProperty() {
        return statusAbsensi;
    }

    public String getStatusAbsensi() {
        return statusAbsensi.get();
    }

    public void setStatusAbsensi(String statusAbsensi) {
        this.statusAbsensi.set(statusAbsensi);
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
