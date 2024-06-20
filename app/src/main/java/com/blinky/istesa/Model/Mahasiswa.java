package com.blinky.istesa.Model;

import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import com.blinky.istesa.DB;

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
    private final StringProperty pwMahasiswa = new SimpleStringProperty();
    private final StringProperty uidRFID = new SimpleStringProperty();

    public Mahasiswa(){

    }

    public Mahasiswa(Object obj) {
        List<String> list = ((ArrayList<String>) obj);

        idMahasiswa.set(list.get(0).toString());
        idKelas.set(list.get(1).toString());
        idProdi.set(list.get(2).toString());
        idAdmin.set(list.get(3).toString());
        nmMahasiswa.set(list.get(4).toString());
        pwMahasiswa.set(list.get(5).toString());
        angkatan.set(list.get(6).toString());
        tglDitambah.set(list.get(7).toString());
        tglDiupdate.set(list.get(8).toString());
        uidRFID.set(list.get(9).toString());
    }

    public String getByID(int i){
        StringProperty mahasiswa[] = {idMahasiswa, idKelas, idProdi, idAdmin, nmMahasiswa, pwMahasiswa, angkatan, tglDitambah, tglDiupdate, uidRFID};
        return mahasiswa[i].get();
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

    public StringProperty pwMahasiswaProperty() {
        return pwMahasiswa;
    }

    public String getPwMahasiswa() {
        return pwMahasiswa.get();
    }

    public void setPwMahasiswa(String pwMahasiswa) {
        this.pwMahasiswa.set(pwMahasiswa);
    }

    public StringProperty uidRFIDProperty() {
        return uidRFID;
    }

    public String getUidRFID() {
        return uidRFID.get();
    }

    public void setUidRFID(String uidRFID) {
        this.uidRFID.set(uidRFID);
    }

    public Boolean create(){
        String pw = getPwMahasiswa();
        String hash = BCrypt.hashpw(pw, BCrypt.gensalt());

        String data[] = new String[]{getIdMahasiswa(), getIdKelas(), getIdProdi(), getIdAdmin(), getNmMahasiswa(), hash, getAngkatan(), "-"};
        String queryVal = String.join("','", data);

        String sql = "INSERT INTO tb_mahasiswa (id_mhswa, id_kelas, id_prodi, id_admin, nm_mahasiswa, pw_mahasiswa, angkatan, uid_rfid) VALUES ('" + queryVal + "')";
        DB db = new DB();

        return db.runSql(sql);
    }

    public Boolean update(String nimLama){
        String data[] = new String[]{
            "id_mhswa = '" + getIdMahasiswa() + "'",
            "id_kelas = '" + getIdKelas() + "'",
            "id_prodi = '" + getIdProdi() + "'",
            "id_admin = '" + getIdAdmin() + "'",
            "nm_mahasiswa = '" + getNmMahasiswa() + "'",
            "pw_mahasiswa = '" + BCrypt.hashpw(getPwMahasiswa(), BCrypt.gensalt()) + "'",
            "angkatan = '" + getAngkatan() + "'",
            "uid_rfid = '" + getUidRFID() + "'",
        };
        String queryVal = String.join(",", data);

        String sql = "UPDATE tb_mahasiswa SET " + queryVal + " WHERE id_mhswa = '" + nimLama + "'";
        DB db = new DB();

        System.out.println(sql);

        return db.runSql(sql);
    }

    public Boolean delete(){
        String sql = "DELETE FROM tb_mahasiswa WHERE id_mhswa =  ('" + getIdMahasiswa() + "')";
        DB db = new DB();

        return db.runSql(sql);
    }
}
