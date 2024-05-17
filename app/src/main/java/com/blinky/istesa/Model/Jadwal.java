package com.blinky.istesa.Model;

import java.util.ArrayList;
import java.util.List;

import com.blinky.istesa.DB;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@SuppressWarnings("unchecked")
public class Jadwal {
    private final StringProperty idJadwal = new SimpleStringProperty();
    private final StringProperty jamMulai = new SimpleStringProperty();
    private final StringProperty jamSelesai = new SimpleStringProperty();
    private final StringProperty hari = new SimpleStringProperty();
    private final StringProperty idKelas = new SimpleStringProperty();
    private final StringProperty idDosen = new SimpleStringProperty();
    private final StringProperty idMatkul = new SimpleStringProperty();
    private final StringProperty tglDitambah = new SimpleStringProperty();
    private final StringProperty tglDiupdate = new SimpleStringProperty();

    public Jadwal(){

    }

    public Jadwal(Object obj) {
        List<String> list = ((ArrayList<String>) obj);

        idJadwal.set(list.get(0).toString());
        jamMulai.set(list.get(1).toString());
        jamSelesai.set(list.get(2).toString()); 
        hari.set(list.get(3).toString());
        idKelas.set(list.get(4).toString());
        idDosen.set(list.get(5).toString());
        idMatkul.set(list.get(6).toString());
        tglDitambah.set(list.get(7).toString());
        tglDiupdate.set(list.get(8).toString());
    }

    public String getByID(int i){
        StringProperty matkul[] = {idJadwal, jamMulai, jamSelesai, hari, idKelas, idDosen, idMatkul, tglDitambah, tglDiupdate};
        return matkul[i].get();
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

    public StringProperty jamMulaiProperty() {
        return jamMulai;
    }

    public String getJamMulai() {
        return jamMulai.get();
    }

    public void setJamMulai(String jamMulai) {
        this.jamMulai.set(jamMulai);
    }

    public StringProperty jamSelesaiProperty() {
        return jamSelesai;
    }

    public String getJamSelesai() {
        return jamSelesai.get();
    }

    public void setJamSelesai(String jamSelesai) {
        this.jamSelesai.set(jamSelesai);
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

    public Boolean create(){
        String data[] = new String[]{getJamMulai(), getJamSelesai(), getHari(), getIdKelas(), getIdDosen(), getIdMatkul()};
        String queryVal = String.join("','", data);

        String sql = "INSERT INTO tb_jadwal (jam_mulai, jam_selesai, hari, id_kelas, id_dosen, id_matkul) VALUES ('" + queryVal + "')";
        DB db = new DB();

        return db.runSql(sql);
    }

    public Boolean update(){
        String data[] = new String[]{
            "jam_mulai = '" + getJamMulai() + "'",
            "jam_selesai = '" + getJamSelesai() + "'",
            "hari = '" + getHari() + "'",
            "id_kelas = '" + getIdKelas() + "'",
            "id_dosen = '" + getIdDosen() + "'",
            "id_matkul = '" + getIdMatkul() + "'",
        };
        String queryVal = String.join(",", data);

        String sql = "UPDATE tb_jadwal SET " + queryVal + " WHERE id_jadwal = '" + getIdJadwal() + "'";
        DB db = new DB();

        return db.runSql(sql);
    }

    public Boolean delete(){
        String sql = "DELETE FROM tb_jadwal WHERE id_jadwal =  ('" + getIdJadwal() + "')";
        DB db = new DB();
        
        return db.runSql(sql);
    }
}
