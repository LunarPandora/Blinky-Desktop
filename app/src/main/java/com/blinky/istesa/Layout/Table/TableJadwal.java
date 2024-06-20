package com.blinky.istesa.Layout.Table;

// import java.text.DecimalFormat;
// import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.blinky.istesa.DB;
import com.blinky.istesa.Model.Dosen;
import com.blinky.istesa.Model.Jadwal;
import com.blinky.istesa.Model.Kelas;
import com.blinky.istesa.Model.Matkul;
import com.blinky.istesa.Model.Prodi;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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

public class TableJadwal extends Table{
    private Jadwal selectedData;

    private List<Jadwal> listJadwal = new ArrayList<Jadwal>();
    private TableView<Jadwal> tb = new TableView<Jadwal>();

    private List<Kelas> listKelas = new ArrayList<Kelas>();
    private List<Dosen> listDosen = new ArrayList<Dosen>(); 
    private List<Matkul> listMatkul = new ArrayList<Matkul>(); 
    private String[] listHari = new String[]{"Minggu", "Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu"};

    TextField searchBarMatkul = new TextField();
    TextField searchBarDosen = new TextField();
    ComboBox<String> kelasChoiceBox = new ComboBox<String>();
    ComboBox<String> hariChoiceBox = new ComboBox<String>();

    public TableJadwal(){
        rootPane = new BorderPane();
        tb.setColumnResizePolicy((param) -> true);

        searchBarMatkul.setPromptText("Cari mata kuliah...");
        searchBarMatkul.setOnKeyTyped(e -> {
            rootPane.setCenter(null);
            rootPane.setCenter(getTable());
        });

        searchBarDosen.setPromptText("Cari dosen...");
        searchBarDosen.setOnKeyTyped(e -> {
            rootPane.setCenter(null);
            rootPane.setCenter(getTable());
        });

        kelasChoiceBox.setOnAction(e -> {
            rootPane.setCenter(null);
            rootPane.setCenter(getTable());
        });
        hariChoiceBox.setOnAction(e -> {
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
        
        Label namaTabel = new Label("Data Jadwal");

        HBox searchBoxPane = new HBox();
        searchBoxPane.setSpacing(15);
        searchBoxPane.getChildren().addAll(searchBarDosen, searchBarMatkul);

        HBox filterBoxPane = new HBox();
        filterBoxPane.setSpacing(15);
        filterBoxPane.getChildren().addAll(kelasChoiceBox, hariChoiceBox);

        paneList.add(namaTabel);
        paneList.add(searchBoxPane);
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

    public TableView<Jadwal> createTable(){
        TableColumn<Jadwal, String> col_id = new TableColumn<>("ID");
        TableColumn<Jadwal, String> col_matkul = new TableColumn<>("Mata Kuliah");
        TableColumn<Jadwal, String> col_dosen = new TableColumn<>("Dosen");
        TableColumn<Jadwal, String> col_kelas = new TableColumn<>("Kelas");
        TableColumn<Jadwal, String> col_jamM = new TableColumn<>("Jam Mulai");
        TableColumn<Jadwal, String> col_jamS = new TableColumn<>("Jam Selesai");
        TableColumn<Jadwal, String> col_hari = new TableColumn<>("Hari");
        TableColumn<Jadwal, String> col_insert = new TableColumn<>("Ditambahkan pada");
        TableColumn<Jadwal, String> col_update = new TableColumn<>("Terakhir kali diedit");

        col_id.setCellValueFactory(v -> v.getValue().idJadwalProperty());
        col_matkul.setCellValueFactory(v -> getMatkul(v.getValue().getIdMatkul()).nmMatkulProperty());
        col_dosen.setCellValueFactory(v -> getDosen(v.getValue().getIdDosen()).nmDosenProperty());
        col_kelas.setCellValueFactory(v -> getKelas(v.getValue().getIdKelas()).nmKelasProperty());
        col_jamM.setCellValueFactory(v -> v.getValue().jamMulaiProperty());
        col_jamS.setCellValueFactory(v -> v.getValue().jamSelesaiProperty());
        col_hari.setCellValueFactory(v -> getDay(v.getValue().getHari()));
        col_insert.setCellValueFactory(v -> v.getValue().tglDitambahProperty());
        col_update.setCellValueFactory(v -> v.getValue().tglDiupdateProperty());

        col_id.prefWidthProperty().bind(tb.widthProperty().multiply(0.05));
        col_matkul.prefWidthProperty().bind(tb.widthProperty().multiply(0.2));
        col_dosen.prefWidthProperty().bind(tb.widthProperty().multiply(0.21));
        col_kelas.prefWidthProperty().bind(tb.widthProperty().multiply(0.05));
        col_jamM.prefWidthProperty().bind(tb.widthProperty().multiply(0.08));
        col_jamS.prefWidthProperty().bind(tb.widthProperty().multiply(0.08));
        col_hari.prefWidthProperty().bind(tb.widthProperty().multiply(0.06));
        col_insert.prefWidthProperty().bind(tb.widthProperty().multiply(0.13));
        col_update.prefWidthProperty().bind(tb.widthProperty().multiply(0.13));

        ArrayList<TableColumn<Jadwal, String>> col = new ArrayList<>();
        col.add(col_id);
        col.add(col_matkul);
        col.add(col_dosen);
        col.add(col_kelas);
        col.add(col_jamM);
        col.add(col_jamS);
        col.add(col_hari);
        col.add(col_insert);
        col.add(col_update);

        for(int i = 0; i < col.size(); i++){
            tb.getColumns().add(col.get(i));
        }

        tb.setRowFactory(tv -> {
            TableRow<Jadwal> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if(!row.isEmpty()) {
                    Jadwal rowData = row.getItem();
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

        for(int y = 0; y < listJadwal.size(); y++){
            tb.getItems().add(listJadwal.get(y));
        }

        table.getChildren().add(tb);

        return table;
    }

    public void getData(){
        DB db = new DB();
        List<String> query_builder = new ArrayList<String>();

        if(searchBarDosen.textProperty().getValue() != null && searchBarDosen.textProperty().getValue() != ""){
            query_builder.add("d.nm_dosen LIKE '%" + searchBarDosen.textProperty().getValue() + "%'");
        }
        if(searchBarMatkul.textProperty().getValue() != null && searchBarMatkul.textProperty().getValue() != ""){
            query_builder.add("m.nm_matkul LIKE '%" + searchBarMatkul.textProperty().getValue() + "%'");
        }
        if(kelasChoiceBox.getValue() != "Semua kelas"){
            for(Kelas k : listKelas){
                if(k.getIdKelas().equals(kelasChoiceBox.getValue().split(" - ")[1])){
                    query_builder.add("id_kelas = '" + k.getIdKelas() + "'");
                }
            }
        }
        if(hariChoiceBox.getValue() != "Semua hari"){
            for(int x = 0; x <= listHari.length; x++){
                if(x == hariChoiceBox.getSelectionModel().getSelectedIndex()){
                    query_builder.add("hari = '" + (x - 1) + "'");
                }
            }
        }

        String query_jadwal = "SELECT id_jadwal, jam_mulai, jam_selesai, hari, j.id_kelas, j.id_dosen, j.id_matkul, j.tgl_ditambah, j.tgl_diupdate FROM tb_jadwal as j INNER JOIN tb_dosen AS d ON j.id_dosen = d.id_dosen INNER JOIN tb_matkul AS m ON j.id_matkul = m.id_matkul";
        if(query_builder.size() != 0){
            query_jadwal += " WHERE " + String.join(" AND ", query_builder);
        }

        List<Object> rs = db.runQuery(query_jadwal);
        listJadwal.clear();

        for(int i = 0; i < rs.size(); i++){
            Jadwal mhswa = new Jadwal(rs.get(i));

            listJadwal.add(mhswa);
        }
    }

    private void fetchData(){
        DB db = new DB();

        listKelas.clear();
        listMatkul.clear();
        listDosen.clear();

        kelasChoiceBox.getItems().clear();
        hariChoiceBox.getItems().clear();

        kelasChoiceBox.getItems().add("Semua kelas");
        hariChoiceBox.getItems().add("Semua hari");

        kelasChoiceBox.setValue("Semua kelas");
        hariChoiceBox.setValue("Semua hari");

        String sql_kelas = "SELECT * FROM tb_kelas";
        List<Object> rs_kelas = db.runQuery(sql_kelas);
        for(Object obj : rs_kelas){
            Kelas kelas = new Kelas(obj);
            listKelas.add(kelas);

            kelasChoiceBox.getItems().add(kelas.getNmKelas() + " - " + kelas.getIdKelas());
        }

        String sql_dosen = "SELECT * FROM tb_dosen";
        List<Object> rs_dosen = db.runQuery(sql_dosen);
        for(Object obj : rs_dosen){
            Dosen dosen = new Dosen(obj);
            listDosen.add(dosen);
        }

        String sql_matkul = "SELECT * FROM tb_matkul";
        List<Object> rs_matkul = db.runQuery(sql_matkul);
        for(Object obj : rs_matkul){
            Matkul matkul = new Matkul(obj);
            listMatkul.add(matkul);
        }

        for(String hari : listHari){
            hariChoiceBox.getItems().add(hari);
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

    private Dosen getDosen(String id){
        for(Dosen d : listDosen){
            if(d.getIdDosen().equals(id)){
                return d;
            }
        }

        return new Dosen();
    }

    private Matkul getMatkul(String id){
        for(Matkul m : listMatkul){
            if(m.getIdMatkul().equals(id)){
                return m;
            }
        }

        return new Matkul();
    }

    private StringProperty getDay(String id){
        for(int x = 0; x < listHari.length; x++){
            if(x == Integer.parseInt(id)){
                return new SimpleStringProperty(listHari[x]);
            }
        }

        return new SimpleStringProperty("");
    }

    public void setSelectedData(Jadwal sd){
        selectedData = sd;
    }

    public Jadwal getSelectedData(){
        return selectedData;
    }

    public BorderPane getPane(){
        return rootPane;
    }
}
