package com.blinky.istesa.Components;

import org.mindrot.jbcrypt.BCrypt;

import com.blinky.istesa.Model.Admin;
import com.blinky.istesa.Model.Dosen;
import com.blinky.istesa.Model.Mahasiswa;

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

        // System.out.println(BCrypt.hashpw(admin_data.getPwAdmin(), BCrypt.gensalt()));
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
