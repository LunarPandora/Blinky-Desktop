package com.blinky.istesa.component;

import java.util.ArrayList;
import java.util.List;

import com.blinky.istesa.DB;

@SuppressWarnings("unchecked")
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

    public String authenticate(){
        DB db = new DB();

        if(id.substring(0, 1).equals("A")){
            List<Object> rs = db.runQuery("SELECT * FROM tb_admin WHERE id_admin = '" + id + "' and pw_admin = '" + pw + "'");

            if(rs.isEmpty()){
                return "ADMIN_ERR";
            }
            else{
                List<String> u_data = ((ArrayList<String>) rs.get(0));
                u_name = u_data.get(1);

                return "ADMIN_OK";
            }
        }
        else{
            List<Object> rs = db.runQuery("SELECT * FROM tb_mahasiswa WHERE id_mhswa = '" + id + "' and pw_mhswa = '" + pw + "'");

            if(rs.isEmpty()){
                return "MHSWA_ERR";
            }
            else{
                List<String> u_data = ((ArrayList<String>) rs.get(0));
                u_name = u_data.get(3);

                return "MHSWA_OK";
            }
        }
    }
}
