package com.blinky.istesa;

import java.util.ArrayList;
import java.util.List;

import com.blinky.istesa.TableLayout.TableDosen;
import com.blinky.istesa.TableLayout.TableKaprodi;
import com.blinky.istesa.TableLayout.TableMahasiswa;
import com.blinky.istesa.TableLayout.TableProdi;
import com.blinky.istesa.TableLayout.TableWarek;
import com.blinky.istesa.component.TableRoute;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
// import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

@SuppressWarnings("exports")
public class Home {
    private final BorderPane rootPane;
    private String jenisTabel = "Mahasiswa";
    private String userType;
    private String userName;
    
    private TableRoute[] listTableA = new TableRoute[]
    {
        new TableRoute("Mahasiswa", new TableMahasiswa().getPane(), new Button("Data Mahasiswa")),
        new TableRoute("Dosen", new TableDosen().getPane(), new Button("Data Dosen")),
        new TableRoute("Kaprodi", new TableKaprodi().getPane(), new Button("Data Kaprodi")),
        new TableRoute("Warek", new TableWarek().getPane(), new Button("Data Warek")),
        new TableRoute("Prodi", new TableProdi().getPane(), new Button("Data Prodi")),
    };

    private TableRoute[] listTableM = new TableRoute[]
    {
        new TableRoute("Mahasiswa", new TableMahasiswa().getPane(), new Button("Status Absensi")),
        // "Dosen", "Kelas", "Matkul", "Prodi", "Warek", "Kaprodi", "StatusAbsensi"
    };

    public Home(String u_type, String user_data){
        userType = u_type;
        userName = user_data;

        // BorderPane (Root)
        rootPane = new BorderPane();
        rootPane.setPadding(new Insets(25, 40, 25, 40));
        rootPane.getStyleClass().addAll(new String[]{"bg-primary"});

        rootPane.setTop(headerPane());
        rootPane.setCenter(bodyPane());
        rootPane.setBottom(actionButtonPane());
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
        Label lbl_namaAdmin = new Label(userName);
        Label lbl_roleAdmin = new Label((userType.equals("MHSWA")) ? "Mahasiswa" : "Admin");

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
        HBox listPaneA = new HBox();
        HBox listPaneM = new HBox();

        HBox.setHgrow(listPaneA, Priority.ALWAYS);
        listPaneA.setSpacing(20);
        listPaneA.setPadding(new Insets(0, 0, 10, 0));
        listPaneA.setAlignment(Pos.CENTER_LEFT);
        listPaneA.getStyleClass().addAll(new String[]{"border-limit"});
        for(int i = 0; i < listTableA.length; i++){
            Button btn = listTableA[i].button;
            String link = listTableA[i].routeName;
            
            btn.setOnAction(e -> {
                jenisTabel = link;
                rootPane.setCenter(null);
                rootPane.setCenter(bodyPane());
            });

            listPaneA.getChildren().add(btn);
        }

        listPaneM.setSpacing(20);
        listPaneM.setPadding(new Insets(0, 0, 10, 0));
        listPaneM.setAlignment(Pos.CENTER_LEFT);
        listPaneM.getStyleClass().addAll(new String[]{"border-limit"});
        for(int i = 0; i < listTableM.length; i++){
            Button btn = listTableM[i].button;
            String link = listTableM[i].routeName;

            btn.setOnAction(e -> {
                jenisTabel = link;
                rootPane.setCenter(null);
                rootPane.setCenter(bodyPane());
            });

            listPaneM.getChildren().add(btn);
        }

        for(int x = 0; x < listPaneA.getChildren().size(); x++) {
            listPaneA.getChildren().get(x).getStyleClass().addAll(new String[]{"lbl_navbar"});
        }
        for(int x = 0; x < listPaneM.getChildren().size(); x++) {
            listPaneM.getChildren().get(x).getStyleClass().addAll(new String[]{"lbl_navbar"});
        }

        if(userType == "MHSWA"){
            return listPaneM;
        }
        else{
            return listPaneA;
        }
    }

    public VBox bodyPane(){
        // Pane Utama untuk konten
        List<Node> paneList = new ArrayList<Node>();

        VBox bodyPane = new VBox();
        bodyPane.setPadding(new Insets(50, 40, 50, 40));
        bodyPane.setSpacing(20);
        bodyPane.getStyleClass().addAll(new String[]{"bg-white", "rounded", "body-pane-m"});        

        paneList.add(navbarPane());
        if(userType == "MHSWA"){
            for(int i = 0; i < listTableM.length; i++){
                if(listTableM[i].routeName == jenisTabel){
                    paneList.add(listTableM[i].table);
                }
            }
        }
        else{
            for(int i = 0; i < listTableA.length; i++){
                if(listTableA[i].routeName == jenisTabel){
                    paneList.add(listTableA[i].table);
                }
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

        btn_tambah.getStyleClass().addAll(new String[]{"btn", "btn-blue"});
        btn_edit.getStyleClass().addAll(new String[]{"btn", "btn-yellow"});
        btn_hapus.getStyleClass().addAll(new String[]{"btn", "btn-red"});

        buttonPane.getChildren().addAll(btn_tambah, btn_edit, btn_hapus);
        buttonPane.getStyleClass().addAll(new String[]{"bg-white", "rounded"});

        return buttonPane;
    }

    public Pane getRootPane(){
        return rootPane;
    }
}
