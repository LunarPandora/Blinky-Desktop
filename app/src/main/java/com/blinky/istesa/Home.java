package com.blinky.istesa;

import java.util.ArrayList;
import java.util.List;

import com.blinky.istesa.Components.Account;
import com.blinky.istesa.Components.TableRoute;
import com.blinky.istesa.Layout.Form.FormAbsensiDosen;
import com.blinky.istesa.Layout.Form.FormDosen;
import com.blinky.istesa.Layout.Form.FormJabatan;
import com.blinky.istesa.Layout.Form.FormJadwal;
import com.blinky.istesa.Layout.Form.FormKelas;
import com.blinky.istesa.Layout.Form.FormMahasiswa;
import com.blinky.istesa.Layout.Form.FormMatkul;
import com.blinky.istesa.Layout.Form.FormProdi;
import com.blinky.istesa.Layout.Form.FormStatusAbsensi;
import com.blinky.istesa.Layout.Table.Table;
import com.blinky.istesa.Layout.Table.TableAbsensiDosen;
import com.blinky.istesa.Layout.Table.TableAbsensiMahasiswa;
import com.blinky.istesa.Layout.Table.TableDosen;
import com.blinky.istesa.Layout.Table.TableJabatan;
import com.blinky.istesa.Layout.Table.TableJadwal;
import com.blinky.istesa.Layout.Table.TableKelas;
import com.blinky.istesa.Layout.Table.TableMahasiswa;
import com.blinky.istesa.Layout.Table.TableMatkul;
import com.blinky.istesa.Layout.Table.TableProdi;
import com.blinky.istesa.Layout.Table.TableStatusAbsensi;
import com.blinky.istesa.Model.Admin;
import com.blinky.istesa.Model.Dosen;
import com.blinky.istesa.Model.Mahasiswa;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

@SuppressWarnings("exports")
public class Home {
    private final BorderPane rootPane;
    private String jenisTabel = "TableMahasiswa";
    private String userType;

    private Account acc;

    public Admin userAdmin;
    public Mahasiswa userMhswa;
    public Dosen userDosen;
    private TableRoute[] listTable;

    public Home(Account account){
        acc = account;
        initTable();

        // BorderPane (Root)
        rootPane = new BorderPane();
        rootPane.setPadding(new Insets(25, 40, 25, 40));
        rootPane.getStyleClass().addAll(new String[]{"bg-primary"});

        rootPane.setTop(headerPane());
        rootPane.setCenter(bodyPane());
        rootPane.setBottom(actionButtonPane());

    }

    private void initTable(){
        userType = acc.getUserType();

        if(userType.equals("Admin")){
            userAdmin = acc.getAdminData();
            listTable = new TableRoute[]
            {
                new TableRoute("TableMahasiswa", new TableMahasiswa(), new Button("Data Mahasiswa")),
                new TableRoute("TableDosen", new TableDosen(), new Button("Data Dosen")),
                new TableRoute("TableJabatan", new TableJabatan(), new Button("Data Jabatan")),
                new TableRoute("TableProdi", new TableProdi(), new Button("Data Prodi")),
                new TableRoute("TableKelas", new TableKelas(), new Button("Data Kelas")),
                new TableRoute("TableMatkul", new TableMatkul(), new Button("Data Matkul")),
                new TableRoute("TableStatusAbsensi", new TableStatusAbsensi(), new Button("Data Status Absensi")),
                new TableRoute("TableJadwal", new TableJadwal(), new Button("Data Jadwal")),
            };
        }
        else if(userType.equals("Dosen")){
            userDosen = acc.getDosenData();
            listTable = new TableRoute[]
            {
                new TableRoute("TableAbsensiDosen", new TableAbsensiDosen(userDosen.getIdDosen()), new Button("Absensi Kelas")),
            };
        }
        else{
            userMhswa = acc.getMhswaData();
            listTable = new TableRoute[]
            {
                new TableRoute("TableAbsensi", new TableAbsensiMahasiswa(userMhswa), new Button("Histori Absensi")),
            };
        }
    }

    public HBox headerPane(){
        List<Node> paneList = new ArrayList<Node>();

        // Logo Aplikasi
        Image icon = new Image("icon.png");
        ImageView icon_plc = new ImageView();
        icon_plc.setFitWidth(100);
        icon_plc.setPreserveRatio(true);
        icon_plc.setImage(icon);

        // AccPane (Detail Akun Admin (Nama, Foto))
        Label lbl_namaAdmin = new Label(userType.equals("Mahasiswa") ? userMhswa.getNmMahasiswa() : userType.equals("Dosen") ? userDosen.getNmDosen() : userAdmin.getNmAdmin());
        Label lbl_roleAdmin = new Label(userType);
        jenisTabel = listTable[0].routeName;

        Image fp = new Image("fp.png");
        ImageView fp_plc = new ImageView();
        fp_plc.setFitWidth(60);
        fp_plc.setPreserveRatio(true);
        fp_plc.setImage(fp);

        VBox lbl_AccPane = new VBox();
        lbl_AccPane.setAlignment(Pos.CENTER_RIGHT);
        lbl_AccPane.getChildren().addAll(lbl_namaAdmin, lbl_roleAdmin);
        lbl_namaAdmin.getStyleClass().addAll(new String[]{"lbl_admin_name"});

        HBox AccPane = new HBox();
        AccPane.getChildren().addAll(lbl_AccPane, fp_plc);
        AccPane.setSpacing(10);

        paneList.add(icon_plc);
        paneList.add(AccPane);

        // HeaderPane (Header)
        HBox headerPane = new HBox();
        headerPane.setPadding(new Insets(25, 40, 25, 40));
        headerPane.setSpacing(20);
        headerPane.setAlignment(Pos.CENTER);
        headerPane.getStyleClass().addAll(new String[]{"bg-white", "rounded"});

        for(int x = 0; x < paneList.size(); x++){
            Region r = new Region();
            HBox.setHgrow(r, Priority.ALWAYS);

            if(x != paneList.size() - 1){
                headerPane.getChildren().addAll(paneList.get(x), r);
            }
            else{
                headerPane.getChildren().addAll(paneList.get(x));
            }
        }

        return headerPane;
    }

    public HBox navbarPane(){
        // ListPane (Ribbon untuk list tabel atau navbar tabel)
        HBox listPane = new HBox();

        HBox.setHgrow(listPane, Priority.ALWAYS);
        listPane.setSpacing(20);
        listPane.setPadding(new Insets(0, 0, 10, 0));
        listPane.setAlignment(Pos.CENTER_LEFT);
        listPane.getStyleClass().addAll(new String[]{"border-limit"});
        for(int i = 0; i < listTable.length; i++){
            Button btn = listTable[i].button;
            String link = listTable[i].routeName;
            
            btn.setOnAction(e -> {
                jenisTabel = link;
                rootPane.setCenter(null);
                rootPane.setCenter(bodyPane());
            });

            listPane.getChildren().add(btn);
        }

        for(int x = 0; x < listPane.getChildren().size(); x++) {
            listPane.getChildren().get(x).getStyleClass().addAll(new String[]{"lbl_navbar"});
        }
        
        return listPane;
    }

    public VBox bodyPane(){
        // Pane Utama untuk konten
        List<Node> paneList = new ArrayList<Node>();

        VBox bodyPane = new VBox();
        bodyPane.setPadding(new Insets(50, 40, 50, 40));
        bodyPane.setSpacing(20);
        bodyPane.getStyleClass().addAll(new String[]{"bg-white", "rounded", "body-pane-m"});        

        paneList.add(navbarPane());
        for(int i = 0; i < listTable.length; i++){
            if(listTable[i].routeName == jenisTabel){
                Table obj = (Table) listTable[i].table;
                paneList.add(obj.rootPane);
            }
        }
        
        for(int x = 0; x < paneList.size(); x++){
            bodyPane.getChildren().add(paneList.get(x));
        }

        return bodyPane;
    }

    public HBox actionButtonPane(){
        HBox buttonPane = new HBox();
        buttonPane.setPadding(new Insets(25, 40, 25, 40));
        buttonPane.setSpacing(20);
        buttonPane.setAlignment(Pos.CENTER);

        Button btn_tambah = new Button("Tambah");
        Button btn_edit = new Button("Edit");
        Button btn_hapus = new Button("Hapus");
        Button btn_refresh = new Button("Refresh");

        btn_tambah.getStyleClass().addAll(new String[]{"btn", "btn-blue"});
        btn_edit.getStyleClass().addAll(new String[]{"btn", "btn-yellow"});
        btn_hapus.getStyleClass().addAll(new String[]{"btn", "btn-red"});
        btn_refresh.getStyleClass().addAll(new String[]{"btn", "btn-green"});

        if(userType.equals("Mahasiswa")){
            buttonPane.getChildren().addAll(btn_refresh);
        }
        else{
            buttonPane.getChildren().addAll(btn_tambah, btn_edit, btn_hapus, btn_refresh);
        }

        buttonPane.getStyleClass().addAll(new String[]{"bg-white", "rounded"});

        btn_tambah.setOnAction(e -> {
            for(int i = 0; i < listTable.length; i++){
                if(listTable[i].routeName == jenisTabel){
                    try{
                        if(jenisTabel.equals("TableMatkul")){
                            new FormMatkul(App.getStage(), this, "create");
                        }
                        else if (jenisTabel.equals("TableAbsensiDosen")) {
                            new FormAbsensiDosen(App.getStage(), this, "create", userDosen);
                        }
                        else if (jenisTabel.equals("TableDosen")) {
                            new FormDosen(App.getStage(), this, "create");
                        }
                        else if (jenisTabel.equals("TableJadwal")) {
                            new FormJadwal(App.getStage(), this, "create");
                        }
                        else if (jenisTabel.equals("TableJabatan")) {
                            new FormJabatan(App.getStage(), this, "create");
                        }
                        else if (jenisTabel.equals("TableKelas")) {
                            new FormKelas(App.getStage(), this, "create");
                        }
                        else if (jenisTabel.equals("TableMahasiswa")) {
                            new FormMahasiswa(App.getStage(), this, "create");
                        }
                        else if (jenisTabel.equals("TableProdi")) {
                            new FormProdi(App.getStage(), this, "create");
                        }
                        else if (jenisTabel.equals("TableStatusAbsensi")) {
                            new FormStatusAbsensi(App.getStage(), this, "create");
                        }
                    }
                    catch(Exception er){
                        er.printStackTrace();
                    }
                }
            }
        });

        btn_edit.setOnAction(e -> {
            for(int i = 0; i < listTable.length; i++){
                if(listTable[i].routeName == jenisTabel){
                    try{
                        if(jenisTabel.equals("TableMatkul")){
                            TableMatkul tableMatkul = (TableMatkul) listTable[i].table;
                            new FormMatkul(App.getStage(), this, "edit", tableMatkul.getSelectedData());
                        }
                        else if (jenisTabel.equals("TableAbsensiDosen")) {
                            TableAbsensiDosen tableAbsensiMahasiswa = (TableAbsensiDosen) listTable[i].table;
                            new FormAbsensiDosen(App.getStage(), this, "edit", tableAbsensiMahasiswa.getSelectedData(), userDosen);
                        }
                        else if (jenisTabel.equals("TableDosen")) {
                            TableDosen tableDosen = (TableDosen) listTable[i].table;
                            new FormDosen(App.getStage(), this, "edit", tableDosen.getSelectedData());
                        }
                        else if (jenisTabel.equals("TableJadwal")) {
                            TableJadwal tableJadwal = (TableJadwal) listTable[i].table;
                            new FormJadwal(App.getStage(), this, "edit", tableJadwal.getSelectedData());
                        }
                        else if (jenisTabel.equals("TableJabatan")) {
                            TableJabatan tableJabatan = (TableJabatan) listTable[i].table;
                            new FormJabatan(App.getStage(), this, "edit", tableJabatan.getSelectedData());
                        }
                        else if (jenisTabel.equals("TableKelas")) {
                            TableKelas tableKelas = (TableKelas) listTable[i].table;
                            new FormKelas(App.getStage(), this, "edit", tableKelas.getSelectedData());
                        }
                        else if (jenisTabel.equals("TableMahasiswa")) {
                            TableMahasiswa tableMahasiswa = (TableMahasiswa) listTable[i].table;
                            new FormMahasiswa(App.getStage(), this, "edit", tableMahasiswa.getSelectedData());
                        }
                        else if (jenisTabel.equals("TableProdi")) {
                            TableProdi tableProdi = (TableProdi) listTable[i].table;
                            new FormProdi(App.getStage(), this, "edit", tableProdi.getSelectedData());
                        }
                        else if (jenisTabel.equals("TableStatusAbsensi")) {
                            TableStatusAbsensi tableStatusAbsensi = (TableStatusAbsensi) listTable[i].table;
                            new FormStatusAbsensi(App.getStage(), this, "edit", tableStatusAbsensi.getSelectedData());
                        }
                    }
                    catch(Exception er){
                        er.printStackTrace();
                    }
                }
            }
        });

        btn_hapus.setOnAction(e -> {
            for(int i = 0; i < listTable.length; i++){
                if(listTable[i].routeName == jenisTabel){
                    try{
                        if(jenisTabel.equals("TableMatkul")){
                            TableMatkul tableMatkul = (TableMatkul) listTable[i].table;
                            new FormMatkul(App.getStage(), this, "delete", tableMatkul.getSelectedData());
                        }
                        else if (jenisTabel.equals("TableAbsensiDosen")) {
                            TableAbsensiDosen tableAbsensiMahasiswa = (TableAbsensiDosen) listTable[i].table;
                            new FormAbsensiDosen(App.getStage(), this, "delete", tableAbsensiMahasiswa.getSelectedData(), userDosen);
                        }
                        else if (jenisTabel.equals("TableDosen")) {
                            TableDosen tableDosen = (TableDosen) listTable[i].table;
                            new FormDosen(App.getStage(), this, "delete", tableDosen.getSelectedData());
                        }
                        else if (jenisTabel.equals("TableJadwal")) {
                            TableJadwal tableJadwal = (TableJadwal) listTable[i].table;
                            new FormJadwal(App.getStage(), this, "delete", tableJadwal.getSelectedData());
                        }
                        else if (jenisTabel.equals("TableJabatan")) {
                            TableJabatan tableJabatan = (TableJabatan) listTable[i].table;
                            new FormJabatan(App.getStage(), this, "delete", tableJabatan.getSelectedData());
                        }
                        else if (jenisTabel.equals("TableKelas")) {
                            TableKelas tableKelas = (TableKelas) listTable[i].table;
                            new FormKelas(App.getStage(), this, "delete", tableKelas.getSelectedData());
                        }
                        else if (jenisTabel.equals("TableMahasiswa")) {
                            TableMahasiswa tableMahasiswa = (TableMahasiswa) listTable[i].table;
                            new FormMahasiswa(App.getStage(), this, "delete", tableMahasiswa.getSelectedData());
                        }
                        else if (jenisTabel.equals("TableProdi")) {
                            TableProdi tableProdi = (TableProdi) listTable[i].table;
                            new FormProdi(App.getStage(), this, "delete", tableProdi.getSelectedData());
                        }
                        else if (jenisTabel.equals("TableStatusAbsensi")) {
                            TableStatusAbsensi tableStatusAbsensi = (TableStatusAbsensi) listTable[i].table;
                            new FormStatusAbsensi(App.getStage(), this, "delete", tableStatusAbsensi.getSelectedData());
                        }
                    }
                    catch(Exception er){
                        er.printStackTrace();
                    }
                }
            }
        });

        btn_refresh.setOnAction(e -> {
            refreshTable();
        });

        return buttonPane;
    }

    public void refreshTable(){
        initTable();
        rootPane.setCenter(null);
        rootPane.setCenter(bodyPane());
    }

    public Pane getRootPane(){
        return rootPane;
    }
}
