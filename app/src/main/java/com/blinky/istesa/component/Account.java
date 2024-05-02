package com.blinky.istesa.component;

import com.blinky.istesa.obj.Admin;
import com.blinky.istesa.obj.Dosen;
import com.blinky.istesa.obj.Mahasiswa;

public class Account {
    private String user_type;
    private Admin admin_data;
    private Mahasiswa mhswa_data;
    private Dosen dosen_data;

    public Account(){
        user_type = "Denied";
    }

    public Account(String u_type, Admin u_data) {
        user_type = u_type;
        admin_data = u_data;
    }

    public Account(String u_type, Mahasiswa u_data) {
        user_type = u_type;
        mhswa_data = u_data;
    }

    public Account(String u_type, Dosen u_data) {
        user_type = u_type;
        dosen_data = u_data;
    }

    public String getUserType(){
        return user_type;
    }

    public Mahasiswa getMhswaData(){
        return mhswa_data;
    }

    public Dosen getDosenData(){
        return dosen_data;
    }

    public Admin getAdminData(){
        return admin_data;
    }
}
