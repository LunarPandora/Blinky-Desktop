package com.blinky.istesa.Components;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import com.blinky.istesa.DB;
import com.blinky.istesa.Model.Admin;
import com.blinky.istesa.Model.Dosen;
import com.blinky.istesa.Model.Mahasiswa;

public class Login {
    private String id;
    private String pw;
    private String u_name;

    public Login(String id_u, String pw_u){
        id = id_u;
        pw = pw_u;
    }

    public String getCurrentUser(){
        return u_name;
    }

    public Account authenticate(){
        DB db = new DB();

        String query_admin = "SELECT * FROM tb_admin WHERE u_admin = '" + id + "'";
        String query_mhswa = "SELECT * FROM tb_mahasiswa WHERE id_mhswa = '" + id + "'";
        String query_dosen = "SELECT * FROM tb_dosen WHERE u_dosen = '" + id + "' and pw_dosen = '" + pw + "'";

        List<Object> rs = db.runQuery(query_admin);
        if(rs.isEmpty()){
            rs = db.runQuery(query_mhswa);
            if(rs.isEmpty()){
                rs = db.runQuery(query_dosen);
                if(rs.isEmpty()){
                    return new Account();
                }
                else{
                    return new Account("Dosen", new Dosen(rs.get(0)));
                }
            }
            else{
                Mahasiswa m = new Mahasiswa(rs.get(0));
                if(BCrypt.checkpw(pw, m.getPwMahasiswa())){
                    return new Account("Mahasiswa", m);
                }
                else{
                    return new Account();
                }
            }
        }
        else{
            Admin adm = new Admin(rs.get(0));
            if(BCrypt.checkpw(pw, adm.getPwAdmin())){
                return new Account("Admin", adm);
            }
            else{
                return new Account();
            }
        }
    }
}
