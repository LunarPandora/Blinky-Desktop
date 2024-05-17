package com.blinky.istesa.Model;

import java.util.ArrayList;
import java.util.List;

import com.blinky.istesa.DB;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@SuppressWarnings("unchecked")
public class StatusAbsensi {
    private final StringProperty idStatusAbsensi = new SimpleStringProperty();
    private final StringProperty nmStatusAbsensi = new SimpleStringProperty();
    private final StringProperty tglDitambah = new SimpleStringProperty();
    private final StringProperty tglDiupdate = new SimpleStringProperty();

    public StatusAbsensi(){

    }

    public StatusAbsensi(Object obj){
        List<String> list = ((ArrayList<String>) obj);

        idStatusAbsensi.set(list.get(0).toString());
        nmStatusAbsensi.set(list.get(1).toString());
        tglDitambah.set(list.get(2).toString());
        tglDiupdate.set(list.get(3).toString());
    }

    public String getByID(int i){
        StringProperty statusAbsensi[] = {idStatusAbsensi, nmStatusAbsensi, tglDitambah, tglDiupdate};
        return statusAbsensi[i].get();
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

    public Boolean create(){
        String sql = "INSERT INTO tb_status_absensi (nm_status_absensi) VALUES ('" + getNmStatusAbsensi() + "')";
        DB db = new DB();

        return db.runSql(sql);
    }

    public Boolean update(){
        String sql = "UPDATE tb_status_absensi SET nm_status_absensi = '" + getNmStatusAbsensi() + "' WHERE id_status_absensi = '" + getIdStatusAbsensi() + "'";
        DB db = new DB();

        return db.runSql(sql);
    }

    public Boolean delete(){
        String sql = "DELETE FROM tb_status_absensi WHERE id_status_absensi =  ('" + getIdStatusAbsensi() + "')";
        DB db = new DB();

        return db.runSql(sql);
    }
}
