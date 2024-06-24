package com.blinky.istesa.Model;

import java.util.ArrayList;
import java.util.List;

import com.blinky.istesa.DB;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@SuppressWarnings("unchecked")
public class Absensi {
    private final StringProperty idAbsensi = new SimpleStringProperty();
    private final StringProperty idMahasiswa = new SimpleStringProperty();
    private final StringProperty idJadwal = new SimpleStringProperty();
    private final StringProperty waktuAbsen = new SimpleStringProperty();
    private final StringProperty statusAbsensi = new SimpleStringProperty();
    private final StringProperty tglDitambah = new SimpleStringProperty();
    private final StringProperty tglDiupdate = new SimpleStringProperty();
    private final StringProperty idMatkul = new SimpleStringProperty();
    private final StringProperty idDosen = new SimpleStringProperty();

    public Absensi(){

    }

    public Absensi(Object obj){
        List<String> list = ((ArrayList<String>) obj);

        idAbsensi.set(list.get(0).toString());
        idMahasiswa.set(list.get(1).toString());
        waktuAbsen.set(list.get(2).toString());
        statusAbsensi.set(list.get(3).toString());
        idJadwal.set(list.get(4).toString());   
        tglDitambah.set(list.get(5).toString());
        tglDiupdate.set(list.get(6).toString());

        if(list.size() > 7){
            idMatkul.set(list.get(7).toString());
        }
        if(list.size() > 8){
            idDosen.set(list.get(8).toString());
        }
    }

    public String getByID(int i){
        StringProperty matkul[] = {idAbsensi, idMahasiswa, waktuAbsen, statusAbsensi, idJadwal, tglDitambah, tglDiupdate, idMatkul};
        return matkul[i].get();
    }

    public StringProperty idAbsensiProperty() {
        return idAbsensi;
    }

    public String getIdAbsensi() {
        return idAbsensi.get();
    }

    public void setIdAbsensi(String idAbsensi) {
        this.idAbsensi.set(idAbsensi);
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

    public StringProperty idJadwalProperty() {
        return idJadwal;
    }

    public String getIdJadwal() {
        return idJadwal.get();
    }

    public void setIdJadwal(String idJadwal) {
        this.idJadwal.set(idJadwal);
    }

    public StringProperty waktuAbsenProperty() {
        return waktuAbsen;
    }

    public String getWaktuAbsen() {
        return waktuAbsen.get();
    }

    public void setWaktuAbsen(String waktuAbsen) {
        this.waktuAbsen.set(waktuAbsen);
    }

    public StringProperty statusAbsensiProperty() {
        return statusAbsensi;
    }

    public String getStatusAbsensi() {
        return statusAbsensi.get();
    }

    public void setStatusAbsensi(String statusAbsensi) {
        this.statusAbsensi.set(statusAbsensi);
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

    public StringProperty idMatkulProperty() {
        return idMatkul;
    }

    public String getIdMatkul() {
        return idMatkul.get();
    }

    public void setIdMatkul(String idMatkul) {
        this.idMatkul.set(idMatkul);
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

    public Boolean create(){
        String data[] = new String[]{getIdMahasiswa(), getWaktuAbsen(), getStatusAbsensi(), getIdJadwal()};
        String queryVal = String.join("','", data);

        String sql = "INSERT INTO tb_absensi (id_mahasiswa, waktu_absen, kode_status_absensi, id_jadwal) VALUES ('" + queryVal + "')";
        DB db = new DB();

        return db.runSql(sql);
    }

    public Boolean update(){
        String data[] = new String[]{
            "id_mahasiswa = '" + getIdMahasiswa() + "'",
            "waktu_absen = '" + getWaktuAbsen() + "'",
            "kode_status_absensi = '" + getStatusAbsensi() + "'",
            "id_jadwal = '" + getIdJadwal() + "'",
        };
        String queryVal = String.join(", ", data);

        String sql = "UPDATE tb_absensi SET " + queryVal + " WHERE id_absensi = '" + getIdAbsensi() + "'";
        DB db = new DB();

        System.out.println(sql);

        return db.runSql(sql);
    }

    public Boolean delete(){
        String sql = "DELETE FROM tb_absensi WHERE id_absensi = '" + getIdAbsensi() + "'";
        DB db = new DB();
        
        return db.runSql(sql);
    }
}
