package com.blinky.istesa.Layout.Table;

// import java.text.DecimalFormat;
// import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.blinky.istesa.DB;
import com.blinky.istesa.Model.Kelas;
import com.blinky.istesa.Model.Mahasiswa;
import com.blinky.istesa.Model.Prodi;

import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class TableMahasiswa extends Table{
    private Mahasiswa selectedData;

    private List<Mahasiswa> listMhswa = new ArrayList<Mahasiswa>();
    private TableView<Mahasiswa> tb = new TableView<Mahasiswa>();

    private List<Kelas> listKelas = new ArrayList<Kelas>();
    private List<Prodi> listProdi = new ArrayList<Prodi>(); 
    
    TextField searchBar = new TextField();
    ComboBox<String> kelasChoiceBox = new ComboBox<String>();
    ComboBox<String> prodiChoiceBox = new ComboBox<String>();

    public TableMahasiswa(){
        rootPane = new BorderPane();
        tb.setColumnResizePolicy((param) -> true);

        searchBar.setPromptText("Cari mahasiswa...");
        searchBar.setOnKeyTyped(e -> {
            rootPane.setCenter(null);
            rootPane.setCenter(getTable());
        });
        kelasChoiceBox.setOnAction(e -> {
            rootPane.setCenter(null);
            rootPane.setCenter(getTable());
        });
        prodiChoiceBox.setOnAction(e -> {
            rootPane.setCenter(null);
            rootPane.setCenter(getTable());
        });

        fetchData();

        rootPane.setTop(filterPane());
        rootPane.setCenter(getTable());
    }

    public HBox filterPane(){
        HBox filterPane = new HBox();
        List<Node> paneList = new ArrayList<Node>();

        HBox filterBoxPane = new HBox();
        filterBoxPane.setSpacing(15);
        filterBoxPane.getChildren().addAll(kelasChoiceBox, prodiChoiceBox);
        
        Label namaTabel = new Label("Data Mahasiswa");

        paneList.add(namaTabel);
        paneList.add(searchBar);
        paneList.add(filterBoxPane);

        filterPane.setSpacing(20);
        filterPane.setAlignment(Pos.CENTER);
        filterPane.setPadding(new Insets(0, 0, 20, 0));
        namaTabel.getStyleClass().addAll(new String[]{"table-title"});

        for(int x = 0; x < paneList.size(); x++){
            Region r = new Region();
            HBox.setHgrow(r, Priority.ALWAYS);

            if(x != paneList.size() - 1){
                filterPane.getChildren().addAll(paneList.get(x), r);
            }
            else{
                filterPane.getChildren().addAll(paneList.get(x));
            }
        }

        return filterPane;
    }

    public TableView<Mahasiswa> createTable(){
        TableColumn<Mahasiswa, String> col_nim = new TableColumn<>("NIM");
        TableColumn<Mahasiswa, String> col_nama = new TableColumn<>("Nama");
        TableColumn<Mahasiswa, String> col_kelas = new TableColumn<>("Kelas");
        TableColumn<Mahasiswa, String> col_prodi = new TableColumn<>("Prodi");
        TableColumn<Mahasiswa, String> col_angkt = new TableColumn<>("Angkatan");
        TableColumn<Mahasiswa, String> col_insert = new TableColumn<>("Waktu Pendaftaran");
        TableColumn<Mahasiswa, String> col_update = new TableColumn<>("Terakhir kali diedit");

        col_nim.setCellValueFactory(v -> v.getValue().idMahasiswaProperty());
        col_nama.setCellValueFactory(v -> v.getValue().nmMahasiswaProperty());
        col_kelas.setCellValueFactory(v -> getKelas(v.getValue().getIdKelas()).nmKelasProperty());
        col_prodi.setCellValueFactory(v -> getProdi(v.getValue().getIdProdi()).nmProdiProperty());
        col_angkt.setCellValueFactory(v -> v.getValue().angkatanProperty());
        col_insert.setCellValueFactory(v -> v.getValue().tglDitambahProperty());
        col_update.setCellValueFactory(v -> v.getValue().tglDiupdateProperty());

        col_nim.prefWidthProperty().bind(tb.widthProperty().multiply(0.07));
        col_nama.prefWidthProperty().bind(tb.widthProperty().multiply(0.25));
        col_kelas.prefWidthProperty().bind(tb.widthProperty().multiply(0.06));
        col_prodi.prefWidthProperty().bind(tb.widthProperty().multiply(0.25));
        col_angkt.prefWidthProperty().bind(tb.widthProperty().multiply(0.07));
        col_insert.prefWidthProperty().bind(tb.widthProperty().multiply(0.15));
        col_update.prefWidthProperty().bind(tb.widthProperty().multiply(0.15));

        ArrayList<TableColumn<Mahasiswa, String>> col = new ArrayList<>();
        col.add(col_nim);
        col.add(col_nama);
        col.add(col_kelas);
        col.add(col_prodi);
        col.add(col_angkt);
        col.add(col_insert);
        col.add(col_update);

        for(int i = 0; i < col.size(); i++){
            tb.getColumns().add(col.get(i));
        }

        tb.setRowFactory(tv -> {
            TableRow<Mahasiswa> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if(!row.isEmpty()) {

                    Mahasiswa rowData = row.getItem();
                    setSelectedData(rowData);
                }
            });

            return row;
        });

        return tb;
    }

    public HBox getTable(){
        HBox table = new HBox();

        tb.getItems().clear();
        tb.getColumns().clear();

        tb = createTable();

        HBox.setHgrow(table, Priority.ALWAYS);
        HBox.setHgrow(tb, Priority.ALWAYS);
        VBox.setVgrow(tb, Priority.ALWAYS);

        getData();

        for(int y = 0; y < listMhswa.size(); y++){
            tb.getItems().add(listMhswa.get(y));
        }

        table.getChildren().add(tb);

        return table;
    }

    public void getData(){
        DB db = new DB();
        List<String> query_builder = new ArrayList<String>();

        if(searchBar.textProperty().getValue() != null && searchBar.textProperty().getValue() != ""){
            query_builder.add("nm_mahasiswa LIKE '%" + searchBar.textProperty().getValue() + "%'");
        }
        if(kelasChoiceBox.getValue() != "Semua kelas"){
            for(Kelas k : listKelas){
                if(k.getIdKelas().equals(kelasChoiceBox.getValue().split(" - ")[1])){
                    query_builder.add("id_kelas = '" + k.getIdKelas() + "'");
                }
            }
        }
        if(prodiChoiceBox.getValue() != "Semua prodi"){
            for(Prodi p : listProdi){
                if(p.getIdProdi().equals(prodiChoiceBox.getValue().split(" - ")[1])){
                    query_builder.add("id_prodi = '" + p.getIdProdi() + "'");
                }
            }
        }

        String query_mhswa = "SELECT * FROM tb_mahasiswa";
        if(query_builder.size() != 0){
            query_mhswa += " WHERE " + String.join(" AND ", query_builder);
        }

        List<Object> rs = db.runQuery(query_mhswa);
        listMhswa.clear();

        for(int i = 0; i < rs.size(); i++){
            Mahasiswa mhswa = new Mahasiswa(rs.get(i));

            listMhswa.add(mhswa);
        }
    }

    private void fetchData(){
        DB db = new DB();

        listKelas.clear();
        listProdi.clear();
        kelasChoiceBox.getItems().clear();
        prodiChoiceBox.getItems().clear();

        kelasChoiceBox.getItems().add("Semua kelas");
        prodiChoiceBox.getItems().add("Semua prodi");
        kelasChoiceBox.setValue("Semua kelas");
        prodiChoiceBox.setValue("Semua prodi");

        String sql_kelas = "SELECT * FROM tb_kelas";
        List<Object> rs_kelas = db.runQuery(sql_kelas);
        for(Object obj : rs_kelas){
            Kelas kelas = new Kelas(obj);
            listKelas.add(kelas);

            kelasChoiceBox.getItems().add(kelas.getNmKelas() + " - " + kelas.getIdKelas());
        }

        String sql_prodi = "SELECT * FROM tb_prodi";
        List<Object> rs_prodi = db.runQuery(sql_prodi);
        for(Object obj : rs_prodi){
            Prodi prodi = new Prodi(obj);
            listProdi.add(prodi);

            prodiChoiceBox.getItems().add(prodi.getNmProdi() + " - " + prodi.getIdProdi());
        }
    }

    private Kelas getKelas(String id){
        for(Kelas k : listKelas){
            if(k.getIdKelas().equals(id)){
                return k;
            }
        }

        return new Kelas();
    }

    private Prodi getProdi(String id){
        for(Prodi p : listProdi){
            if(p.getIdProdi().equals(id)){
                return p;
            }
        }

        return new Prodi();
    }

    public void setSelectedData(Mahasiswa sd){
        selectedData = sd;
    }

    public Mahasiswa getSelectedData(){
        return selectedData;
    }

    public BorderPane getPane(){
        return rootPane;
    }
}
