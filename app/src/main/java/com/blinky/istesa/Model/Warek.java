package com.blinky.istesa.Model;

import java.util.ArrayList;
import java.util.List;

import com.blinky.istesa.DB;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@SuppressWarnings("unchecked")
public class Warek {
    private final StringProperty idWarek = new SimpleStringProperty();
    private final StringProperty nmWarek = new SimpleStringProperty();
    private final StringProperty uWarek = new SimpleStringProperty();
    private final StringProperty pwWarek = new SimpleStringProperty();
    private final StringProperty fotoWarek = new SimpleStringProperty();
    private final StringProperty nidn = new SimpleStringProperty();
    private final StringProperty tglDitambah = new SimpleStringProperty();
    private final StringProperty tglDiupdate = new SimpleStringProperty();

    public Warek(){

    }

    public Warek(Object obj){
        List<String> list = ((ArrayList<String>) obj);

        idWarek.set(list.get(0).toString());
        nmWarek.set(list.get(1).toString());
        uWarek.set(list.get(2).toString());
        pwWarek.set(list.get(3).toString());
        fotoWarek.set(list.get(4).toString());
        nidn.set(list.get(5).toString());
        tglDitambah.set(list.get(6).toString());
        tglDiupdate.set(list.get(7).toString());
    }

    public String getByID(int i){
        StringProperty warek[] = {idWarek, nmWarek, uWarek, pwWarek, fotoWarek, nidn, tglDitambah, tglDiupdate};
        return warek[i].get();
    }

    public StringProperty idWarekProperty() {
        return idWarek;
    }

    public String getIdWarek() {
        return idWarek.get();
    }

    public void setIdWarek(String idWarek) {
        this.idWarek.set(idWarek);
    }

    public StringProperty nmWarekProperty() {
        return nmWarek;
    }

    public String getNmWarek() {
        return nmWarek.get();
    }

    public void setNmWarek(String nmWarek) {
        this.nmWarek.set(nmWarek);
    }

    public StringProperty uWarekProperty() {
        return uWarek;
    }

    public String getUWarek() {
        return uWarek.get();
    }

    public void setUWarek(String uWarek) {
        this.uWarek.set(uWarek);
    }

    public StringProperty pwWarekProperty() {
        return pwWarek;
    }

    public String getPwWarek() {
        return pwWarek.get();
    }

    public void setPwWarek(String pwWarek) {
        this.pwWarek.set(pwWarek);
    }

    public StringProperty fotoWarekProperty() {
        return fotoWarek;
    }

    public String getFotoWarek() {
        return fotoWarek.get();
    }

    public void setFotoWarek(String fotoWarek) {
        this.fotoWarek.set(fotoWarek);
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
        String data[] = new String[]{getNmWarek(), getUWarek(), getPwWarek(), "-", getNidn()};
        String queryVal = String.join("','", data);

        String sql = "INSERT INTO tb_warek (nm_warek, u_warek, pw_warek, foto_warek, nidn) VALUES ('" + queryVal + "')";
        DB db = new DB();

        return db.runSql(sql);
    }

    public Boolean update(){
        String data[] = new String[]{
            "nm_warek = '" + getNmWarek() + "'",
            "u_warek = '" + getUWarek() + "'",
            "pw_warek = '" + getPwWarek() + "'",
            "nidn = '" + getNidn() + "'",
        };
        String queryVal = String.join(",", data);

        String sql = "UPDATE tb_warek SET " + queryVal + " WHERE id_warek = '" + getIdWarek() + "'";
        DB db = new DB();

        return db.runSql(sql);
    }

    public Boolean delete(){
        String sql = "DELETE FROM tb_warek WHERE id_warek =  ('" + getIdWarek() + "')";
        DB db = new DB();

        return db.runSql(sql);
    }
}
