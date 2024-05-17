package com.blinky.istesa.Model;

import java.util.ArrayList;
import java.util.List;

import com.blinky.istesa.DB;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@SuppressWarnings("unchecked")
public class Prodi {
    private final StringProperty idProdi = new SimpleStringProperty();
    private final StringProperty nmProdi = new SimpleStringProperty();
    private final StringProperty tglDitambah = new SimpleStringProperty();
    private final StringProperty tglDiupdate = new SimpleStringProperty();

    public Prodi(){

    }

    public Prodi(Object obj){
        List<String> list = ((ArrayList<String>) obj);

        idProdi.set(list.get(0).toString());
        nmProdi.set(list.get(1).toString());
        tglDitambah.set(list.get(2).toString());
        tglDiupdate.set(list.get(3).toString());
    }

    public String getByID(int i){
        StringProperty prodi[] = {idProdi, nmProdi, tglDitambah, tglDiupdate};
        return prodi[i].get();
    }

    public String getIdProdi() {
        return idProdi.get();
    }

    public void setIdProdi(String idProdi) {
        this.idProdi.set(idProdi);
    }

    public StringProperty idProdiProperty() {
        return idProdi;
    }

    public String getNmProdi() {
        return nmProdi.get();
    }

    public void setNmProdi(String nmProdi) {
        this.nmProdi.set(nmProdi);
    }

    public StringProperty nmProdiProperty() {
        return nmProdi;
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
        String sql = "INSERT INTO tb_prodi (nm_prodi) VALUES ('" + getNmProdi() + "')";
        DB db = new DB();

        return db.runSql(sql);
    }

    public Boolean update(){
        String sql = "UPDATE tb_prodi SET nm_prodi = '" + getNmProdi() + "' WHERE id_prodi = '" + getIdProdi() + "'";
        DB db = new DB();

        return db.runSql(sql);
    }

    public Boolean delete(){
        String sql = "DELETE FROM tb_prodi WHERE id_prodi =  ('" + getIdProdi() + "')";
        DB db = new DB();

        return db.runSql(sql);
    }
}
