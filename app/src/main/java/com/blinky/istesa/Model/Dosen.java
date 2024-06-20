package com.blinky.istesa.Model;

import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import com.blinky.istesa.DB;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@SuppressWarnings("unchecked")
public class Dosen {
    private final StringProperty idDosen = new SimpleStringProperty();
    private final StringProperty idProdi = new SimpleStringProperty();
    private final StringProperty nmDosen = new SimpleStringProperty();
    private final StringProperty nidn = new SimpleStringProperty();
    private final StringProperty idJabatan = new SimpleStringProperty();
    private final StringProperty uDosen = new SimpleStringProperty();
    private final StringProperty pwDosen = new SimpleStringProperty();
    private final StringProperty tglDitambah = new SimpleStringProperty();
    private final StringProperty tglDiupdate = new SimpleStringProperty();

    public Dosen(){
        
    }

    public Dosen(Object obj) {
        List<String> list = ((ArrayList<String>) obj);

        idDosen.set(list.get(0).toString());
        idProdi.set(list.get(1).toString());
        nmDosen.set(list.get(2).toString());
        nidn.set(list.get(3).toString());
        idJabatan.set(list.get(4).toString());
        uDosen.set(list.get(5).toString());
        pwDosen.set(list.get(6).toString());
        tglDitambah.set(list.get(7).toString());
        tglDiupdate.set(list.get(8).toString());
    }

    public String getByID(int i){
        StringProperty dosen[] = {idDosen, idProdi, nmDosen, nidn, idJabatan, uDosen, pwDosen, tglDitambah, tglDiupdate};
        return dosen[i].get();
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

    public StringProperty idProdiProperty() {
        return idProdi;
    }

    public String getIdProdi() {
        return idProdi.get();
    }

    public void setIdProdi(String idProdi) {
        this.idProdi.set(idProdi);
    }

    public StringProperty nmDosenProperty() {
        return nmDosen;
    }

    public String getNmDosen() {
        return nmDosen.get();
    }

    public void setNmDosen(String nmDosen) {
        this.nmDosen.set(nmDosen);
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

    public StringProperty idJabatanProperty() {
        return idJabatan;
    }

    public String getIdJabatan() {
        return idJabatan.get();
    }

    public void setIdJabatan(String idJabatan) {
        this.idJabatan.set(idJabatan);
    }

    public StringProperty uDosenProperty() {
        return uDosen;
    }

    public String getUDosen() {
        return uDosen.get();
    }

    public void setUDosen(String uDosen) {
        this.uDosen.set(uDosen);
    }

    public StringProperty pwDosenProperty() {
        return pwDosen;
    }

    public String getPwDosen() {
        return pwDosen.get();
    }

    public void setPwDosen(String pwDosen) {
        this.pwDosen.set(pwDosen);
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
        String pw = getPwDosen();
        String hash = BCrypt.hashpw(pw, BCrypt.gensalt());

        String data[] = new String[]{getIdProdi(), getNmDosen(), getNidn(), getIdJabatan(), getUDosen(), getNmDosen(), hash};
        String queryVal = String.join("','", data);

        String sql = "INSERT INTO tb_dosen (id_prodi, nm_dosen, nidn, id_jabatan, u_dosen, nm_dosen, pw_dosen) VALUES ('" + queryVal + "')";
        DB db = new DB();

        return db.runSql(sql);
    }

    public Boolean update(){
        String data[] = new String[]{
            "id_prodi = '" + getIdProdi() + "'",
            "nm_dosen = '" + getNmDosen() + "'",
            "nidn = '" + getNidn() + "'",
            "id_jabatan = '" + getIdJabatan() + "'",
            "u_dosen = '" + getUDosen() + "'",
            "pw_dosen = '" + BCrypt.hashpw(getPwDosen(), BCrypt.gensalt()) + "'",
        };
        String queryVal = String.join(",", data);

        String sql = "UPDATE tb_dosen SET " + queryVal + " WHERE id_dosen = '" + getIdDosen() + "'";
        DB db = new DB();

        return db.runSql(sql);
    }

    public Boolean delete(){
        String sql = "DELETE FROM tb_dosen WHERE id_dosen =  ('" + getIdDosen() + "')";
        DB db = new DB();

        return db.runSql(sql);
    }
}
