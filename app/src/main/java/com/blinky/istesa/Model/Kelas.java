package com.blinky.istesa.Model;

import java.util.ArrayList;
import java.util.List;

import com.blinky.istesa.DB;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@SuppressWarnings("unchecked")
public class Kelas {
    private final StringProperty idKelas = new SimpleStringProperty();
    private final StringProperty nmKelas = new SimpleStringProperty();
    private final StringProperty tglDitambah = new SimpleStringProperty();
    private final StringProperty tglDiupdate = new SimpleStringProperty();

    public Kelas(){

    }

    public Kelas(Object obj){
        List<String> list = ((ArrayList<String>) obj);

        idKelas.set(list.get(0).toString());
        nmKelas.set(list.get(1).toString());
        tglDitambah.set(list.get(2).toString());
        tglDiupdate.set(list.get(3).toString());
    }

    public String getByID(int i){
        StringProperty kelas[] = {idKelas, nmKelas, tglDitambah, tglDiupdate};
        return kelas[i].get();
    }

    public String getIdKelas() {
        return idKelas.get();
    }

    public void setIdKelas(String idKelas) {
        this.idKelas.set(idKelas);
    }

    public StringProperty idKelasProperty() {
        return idKelas;
    }

    public String getNmKelas() {
        return nmKelas.get();
    }

    public void setNmKelas(String nmKelas) {
        this.nmKelas.set(nmKelas);
    }

    public StringProperty nmKelasProperty() {
        return nmKelas;
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
        String sql = "INSERT INTO tb_kelas (nm_kelas) VALUES ('" + getNmKelas() + "')";
        DB db = new DB();

        return db.runSql(sql);
    }

    public Boolean update(){
        String sql = "UPDATE tb_kelas SET nm_kelas = '" + getNmKelas() + "' WHERE id_kelas = '" + getIdKelas() + "'";
        DB db = new DB();

        return db.runSql(sql);
    }

    public Boolean delete(){
        String sql = "DELETE FROM tb_kelas WHERE id_kelas =  ('" + getIdKelas() + "')";
        DB db = new DB();

        return db.runSql(sql);
    }
}
