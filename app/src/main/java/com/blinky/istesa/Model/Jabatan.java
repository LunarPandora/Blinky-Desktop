package com.blinky.istesa.Model;

import java.util.ArrayList;
import java.util.List;

import com.blinky.istesa.DB;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@SuppressWarnings("unchecked")
public class Jabatan {
    private final StringProperty idJabatan = new SimpleStringProperty();
    private final StringProperty nmJabatan = new SimpleStringProperty();
    private final StringProperty tglDitambah = new SimpleStringProperty();
    private final StringProperty tglDiupdate = new SimpleStringProperty();

    public Jabatan(){

    }

    public Jabatan(Object obj){
        List<String> list = ((ArrayList<String>) obj);

        idJabatan.set(list.get(0).toString());
        nmJabatan.set(list.get(1).toString());
        tglDitambah.set(list.get(2).toString());
        tglDiupdate.set(list.get(3).toString());
    }

    public String getByID(int i){
        StringProperty jabatan[] = {idJabatan, nmJabatan, tglDitambah, tglDiupdate};
        return jabatan[i].get();
    }

    public String getIdJabatan() {
        return idJabatan.get();
    }

    public void setIdJabatan(String idJabatan) {
        this.idJabatan.set(idJabatan);
    }

    public StringProperty idJabatanProperty() {
        return idJabatan;
    }

    public String getNmJabatan() {
        return nmJabatan.get();
    }

    public void setNmJabatan(String nmJabatan) {
        this.nmJabatan.set(nmJabatan);
    }

    public StringProperty nmJabatanProperty() {
        return nmJabatan;
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
        String sql = "INSERT INTO tb_jabatan (nm_jabatan) VALUES ('" + getNmJabatan() + "')";
        DB db = new DB();

        return db.runSql(sql);
    }

    public Boolean update(){
        String sql = "UPDATE tb_jabatan SET nm_jabatan = '" + getNmJabatan() + "' WHERE id_jabatan = '" + getIdJabatan() + "'";
        DB db = new DB();

        return db.runSql(sql);
    }

    public Boolean delete(){
        String sql = "DELETE FROM tb_jabatan WHERE id_jabatan =  ('" + getIdJabatan() + "')";
        DB db = new DB();

        return db.runSql(sql);
    }
}

