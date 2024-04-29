package com.blinky.istesa.obj;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@SuppressWarnings("unchecked")
public class Jadwal {
    private final StringProperty idJadwal = new SimpleStringProperty();
    private final StringProperty jam = new SimpleStringProperty();
    private final StringProperty hari = new SimpleStringProperty();
    private final StringProperty idKelas = new SimpleStringProperty();
    private final StringProperty idDosen = new SimpleStringProperty();
    private final StringProperty idMatkul = new SimpleStringProperty();
    private final StringProperty tglDitambah = new SimpleStringProperty();
    private final StringProperty tglDiupdate = new SimpleStringProperty();

    public Jadwal(Object obj) {
        List<String> list = ((ArrayList<String>) obj);

        idJadwal.set(list.get(0).toString());
        jam.set(list.get(1).toString());
        hari.set(list.get(2).toString());
        idKelas.set(list.get(3).toString());
        idDosen.set(list.get(4).toString());
        idMatkul.set(list.get(5).toString());
        tglDitambah.set(list.get(6).toString());
        tglDiupdate.set(list.get(7).toString());
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

    public StringProperty jamProperty() {
        return jam;
    }

    public String getJam() {
        return jam.get();
    }

    public void setJam(String jam) {
        this.jam.set(jam);
    }

    public StringProperty hariProperty() {
        return hari;
    }

    public String getHari() {
        return hari.get();
    }

    public void setHari(String hari) {
        this.hari.set(hari);
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

    public StringProperty idDosenProperty() {
        return idDosen;
    }

    public String getIdDosen() {
        return idDosen.get();
    }

    public void setIdDosen(String idDosen) {
        this.idDosen.set(idDosen);
    }

    public StringProperty idMatkulProperty() {
        return idMatkul;
    }

    public String getIdMatkul() {
        return idMatkul.get();
    }

    public void setIdMatkul(String idMatkul) {
        this.idMatkul.set(idMatkul);
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
