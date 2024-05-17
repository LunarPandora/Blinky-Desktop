package com.blinky.istesa.Model;

import java.util.ArrayList;
import java.util.List;

import com.blinky.istesa.DB;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@SuppressWarnings("unchecked")
public class Kaprodi {
    private final StringProperty idKaprodi = new SimpleStringProperty();
    private final StringProperty nmKaprodi = new SimpleStringProperty();
    private final StringProperty uKaprodi = new SimpleStringProperty();
    private final StringProperty pwKaprodi = new SimpleStringProperty();
    private final StringProperty fotoKaprodi = new SimpleStringProperty();
    private final StringProperty idProdi = new SimpleStringProperty();
    private final StringProperty nidn = new SimpleStringProperty();
    private final StringProperty tglDitambah = new SimpleStringProperty();
    private final StringProperty tglDiupdate = new SimpleStringProperty();

    public Kaprodi(){

    }

    public Kaprodi(Object obj) {
        List<String> list = ((ArrayList<String>) obj);

        idKaprodi.set(list.get(0).toString());
        nmKaprodi.set(list.get(1).toString());
        uKaprodi.set(list.get(2).toString());
        pwKaprodi.set(list.get(3).toString());
        fotoKaprodi.set(list.get(4).toString());
        idProdi.set(list.get(5).toString());
        nidn.set(list.get(6).toString());
        tglDitambah.set(list.get(7).toString());
        tglDiupdate.set(list.get(8).toString());
    }

    public String getByID(int i){
        StringProperty kaprodi[] = {idKaprodi, nmKaprodi, uKaprodi, pwKaprodi, fotoKaprodi, idProdi, nidn, tglDitambah, tglDiupdate};
        return kaprodi[i].get();
    }

    public StringProperty idKaprodiProperty() {
        return idKaprodi;
    }

    public String getIdKaprodi() {
        return idKaprodi.get();
    }

    public void setIdKaprodi(String idKaprodi) {
        this.idKaprodi.set(idKaprodi);
    }

    public StringProperty nmKaprodiProperty() {
        return nmKaprodi;
    }

    public String getNmKaprodi() {
        return nmKaprodi.get();
    }

    public void setNmKaprodi(String nmKaprodi) {
        this.nmKaprodi.set(nmKaprodi);
    }

    public StringProperty uKaprodiProperty() {
        return uKaprodi;
    }

    public String getUKaprodi() {
        return uKaprodi.get();
    }

    public void setUKaprodi(String uKaprodi) {
        this.uKaprodi.set(uKaprodi);
    }

    public StringProperty pwKaprodiProperty() {
        return pwKaprodi;
    }

    public String getPwKaprodi() {
        return pwKaprodi.get();
    }

    public void setPwKaprodi(String pwKaprodi) {
        this.pwKaprodi.set(pwKaprodi);
    }

    public StringProperty fotoKaprodiProperty() {
        return fotoKaprodi;
    }

    public String getFotoKaprodi() {
        return fotoKaprodi.get();
    }

    public void setFotoKaprodi(String fotoKaprodi) {
        this.fotoKaprodi.set(fotoKaprodi);
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

    public Boolean create(){
        String data[] = new String[]{getNmKaprodi(), getUKaprodi(), getPwKaprodi(), "-", getIdProdi(), getNidn()};
        String queryVal = String.join("','", data);

        String sql = "INSERT INTO tb_kaprodi (nm_kaprodi, u_kaprodi, pw_kaprodi, foto_kaprodi, id_prodi, nidn) VALUES ('" + queryVal + "')";
        DB db = new DB();

        return db.runSql(sql);
    }

    public Boolean update(){
        String data[] = new String[]{
            "nm_kaprodi = '" + getNmKaprodi() + "'",
            "u_kaprodi = '" + getUKaprodi() + "'",
            "pw_kaprodi = '" + getPwKaprodi() + "'",
            "foto_kaprodi = '-'",
            "id_prodi = '" + getIdProdi() + "'",
            "nidn = '" + getNidn() + "'",
        };
        String queryVal = String.join(",", data);

        String sql = "UPDATE tb_kaprodi SET " + queryVal + " WHERE id_kaprodi = '" + getIdKaprodi() + "'";
        DB db = new DB();

        return db.runSql(sql);
    }

    public Boolean delete(){
        String sql = "DELETE FROM tb_kaprodi WHERE id_kaprodi =  ('" + getIdKaprodi() + "')";
        DB db = new DB();

        return db.runSql(sql);
    }
}

