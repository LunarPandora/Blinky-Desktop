package com.blinky.istesa.obj;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@SuppressWarnings("unchecked")
public class Mahasiswa {
    private final StringProperty idMahasiswa = new SimpleStringProperty();
    private final StringProperty idKelas = new SimpleStringProperty();
    private final StringProperty idProdi = new SimpleStringProperty();
    private final StringProperty idAdmin = new SimpleStringProperty();
    private final StringProperty nmMahasiswa = new SimpleStringProperty();
    private final StringProperty angkatan = new SimpleStringProperty();
    private final StringProperty tglDitambah = new SimpleStringProperty();
    private final StringProperty tglDiupdate = new SimpleStringProperty();
    private final StringProperty fotoMahasiswa = new SimpleStringProperty();

    public Mahasiswa(Object obj) {
        List<String> list = ((ArrayList<String>) obj);

        idMahasiswa.set(list.get(0).toString());
        idKelas.set(list.get(1).toString());
        idProdi.set(list.get(2).toString());
        nmMahasiswa.set(list.get(3).toString());
        angkatan.set(list.get(4).toString());
        tglDitambah.set(list.get(5).toString());
        tglDiupdate.set(list.get(6).toString());
        fotoMahasiswa.set(list.get(7).toString());
        idAdmin.set(list.get(8).toString());
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

    public StringProperty idKelasProperty() {
        return idKelas;
    }

    public String getIdKelas() {
        return idKelas.get();
    }

    public void setIdKelas(String idKelas) {
        this.idKelas.set(idKelas);
    }

    public StringProperty idProdiProperty() {
        return idProdi;
    }

    public String getIdProdi() {
        return idProdi.get();
    }

    public void setIdProdi(String idProdi) {
        this.idProdi.set(idProdi);
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

    public StringProperty nmMahasiswaProperty() {
        return nmMahasiswa;
    }

    public String getNmMahasiswa() {
        return nmMahasiswa.get();
    }

    public void setNmMahasiswa(String nmMahasiswa) {
        this.nmMahasiswa.set(nmMahasiswa);
    }

    public StringProperty angkatanProperty() {
        return angkatan;
    }

    public String getAngkatan() {
        return angkatan.get();
    }

    public void setAngkatan(String angkatan) {
        this.angkatan.set(angkatan);
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

    public StringProperty fotoMahasiswaProperty() {
        return fotoMahasiswa;
    }

    public String getFotoMahasiswa() {
        return fotoMahasiswa.get();
    }

    public void setFotoMahasiswa(String fotoMahasiswa) {
        this.fotoMahasiswa.set(fotoMahasiswa);
    }
}
