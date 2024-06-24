package com.blinky.istesa.Layout.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.blinky.istesa.DB;
import com.blinky.istesa.Model.Absensi;
import com.blinky.istesa.Model.Dosen;
import com.blinky.istesa.Model.Kelas;
import com.blinky.istesa.Model.Mahasiswa;
import com.blinky.istesa.Model.Matkul;
import com.blinky.istesa.Model.Prodi;
import com.blinky.istesa.Model.StatusAbsensi;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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

public class TableAbsensiMahasiswa extends Table{
    private Absensi selectedData;
    private Mahasiswa mahasiswa;

    private List<Absensi> listAbsensi = new ArrayList<Absensi>();
    private TableView<Absensi> tb = new TableView<Absensi>();

    private List<Dosen> listDosen = new ArrayList<Dosen>();
    private List<Matkul> listMatkul = new ArrayList<Matkul>();
    private List<StatusAbsensi> listStatus = new ArrayList<StatusAbsensi>();

    TextField searchBar = new TextField();
    ComboBox<String> matkulChoiceBox = new ComboBox<String>();
    ComboBox<String> statusChoiceBox = new ComboBox<String>();
    ComboBox<String> dosenChoiceBox = new ComboBox<String>();
    DatePicker tglMatkulDatePicker = new DatePicker(LocalDate.now());

    public TableAbsensiMahasiswa(Mahasiswa m){
        mahasiswa = m;
        
        rootPane = new BorderPane();
        tb.setColumnResizePolicy((param) -> true);

        searchBar.prefWidthProperty().bind(rootPane.widthProperty().multiply(0.2));
        tglMatkulDatePicker.prefWidthProperty().bind(rootPane.widthProperty().multiply(0.1));
        matkulChoiceBox.prefWidthProperty().bind(rootPane.widthProperty().multiply(0.1));
        statusChoiceBox.prefWidthProperty().bind(rootPane.widthProperty().multiply(0.1));
        dosenChoiceBox.prefWidthProperty().bind(rootPane.widthProperty().multiply(0.1));

        searchBar.setPromptText("Cari dosen...");
        searchBar.setOnKeyTyped(e -> {
            rootPane.setCenter(null);
            rootPane.setCenter(getTable());
        });
        matkulChoiceBox.setOnAction(e -> {
            rootPane.setCenter(null);
            rootPane.setCenter(getTable());
        });
        statusChoiceBox.setOnAction(e -> {
            rootPane.setCenter(null);
            rootPane.setCenter(getTable());
        });
        dosenChoiceBox.setOnAction(e -> {
            rootPane.setCenter(null);
            rootPane.setCenter(getTable());
        });

        tglMatkulDatePicker.setOnAction(e -> {
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
        filterBoxPane.getChildren().addAll(searchBar, tglMatkulDatePicker, dosenChoiceBox, matkulChoiceBox, statusChoiceBox);

        Label namaTabel = new Label("Data Absensi Kelas");

        paneList.add(namaTabel);
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

    public TableView<Absensi> createTable(){
        TableColumn<Absensi, Number> col_id = new TableColumn<>("No.");
        TableColumn<Absensi, String> col_dosen = new TableColumn<>("Nama Dosen");
        TableColumn<Absensi, String> col_jdwl = new TableColumn<>("Mata Kuliah");
        TableColumn<Absensi, String> col_wktu= new TableColumn<>("Waktu Absensi");
        TableColumn<Absensi, String> col_status = new TableColumn<>("Status");
        TableColumn<Absensi, String> col_insert = new TableColumn<>("Ditambahkan pada");
        TableColumn<Absensi, String> col_update = new TableColumn<>("Terakhir kali diedit");

        col_id.setCellValueFactory(v -> new ReadOnlyObjectWrapper<Number>(tb.getItems().indexOf(v.getValue()) + 1));
        col_dosen.setCellValueFactory(v -> getDosen(v.getValue().getIdDosen()).nmDosenProperty());
        col_jdwl.setCellValueFactory(v -> getMatkul(v.getValue().getIdMatkul()).nmMatkulProperty());
        col_wktu.setCellValueFactory(v -> v.getValue().waktuAbsenProperty());
        col_status.setCellValueFactory(v -> getStatusAbsensi(v.getValue().getStatusAbsensi()).nmStatusAbsensiProperty());
        col_insert.setCellValueFactory(v -> v.getValue().tglDitambahProperty());
        col_update.setCellValueFactory(v -> v.getValue().tglDiupdateProperty());

        col_id.prefWidthProperty().bind(tb.widthProperty().multiply(0.05));
        col_dosen.prefWidthProperty().bind(tb.widthProperty().multiply(0.2));
        col_jdwl.prefWidthProperty().bind(tb.widthProperty().multiply(0.2));
        col_wktu.prefWidthProperty().bind(tb.widthProperty().multiply(0.15));
        col_status.prefWidthProperty().bind(tb.widthProperty().multiply(0.1));
        col_insert.prefWidthProperty().bind(tb.widthProperty().multiply(0.15));
        col_update.prefWidthProperty().bind(tb.widthProperty().multiply(0.15));

        ArrayList<TableColumn<Absensi, ?>> col = new ArrayList<>();
        col.add(col_id);
        col.add(col_dosen);
        col.add(col_jdwl);
        col.add(col_wktu);
        col.add(col_status);
        col.add(col_insert);
        col.add(col_update);

        for(int i = 0; i < col.size(); i++){
            tb.getColumns().add(col.get(i));
        }

        tb.setRowFactory(tv -> {
            TableRow<Absensi> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if(!row.isEmpty()) {
                    Absensi rowData = row.getItem();
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

        for(int y = 0; y < listAbsensi.size(); y++){
            tb.getItems().add(listAbsensi.get(y));
        }

        table.getChildren().add(tb);

        return table;
    }

    public void getData(){
        DB db = new DB();
        List<String> query_builder = new ArrayList<String>();

        if(searchBar.textProperty().getValue() != null && searchBar.textProperty().getValue() != ""){
            query_builder.add("(nm_mahasiswa LIKE '%" + searchBar.textProperty().getValue() + "%')");
        }
        if(matkulChoiceBox.getValue() != "Semua matkul"){
            for(Matkul m : listMatkul){
                if(m.getIdMatkul().equals(matkulChoiceBox.getValue().split(" - ")[1])){
                    query_builder.add("(id_matkul = '" + m.getIdMatkul() + "')");
                }
            }
        }
        if(statusChoiceBox.getValue() != "Semua status"){
            for(StatusAbsensi s : listStatus){
                if(s.getIdStatusAbsensi().equals(statusChoiceBox.getValue().split(" - ")[1])){
                    query_builder.add("(kode_status_absensi = '" + s.getIdStatusAbsensi() + "')");
                }
            }
        }
        if(dosenChoiceBox.getValue() != "Semua dosen"){
            for(Dosen d : listDosen){
                if(d.getIdDosen().equals(dosenChoiceBox.getValue().split(" - ")[1])){
                    query_builder.add("(j.id_dosen = '" + d.getIdDosen() + "')");
                }
            }
        }

        query_builder.add("(a.id_mahasiswa = '" + mahasiswa.getIdMahasiswa() + "')");
        query_builder.add("(a.waktu_absen BETWEEN '" + tglMatkulDatePicker.getValue().toString() + " 00:00:00' AND '" + tglMatkulDatePicker.getValue() + " 23:59:59')");

        String query_absensi = "SELECT id_absensi, id_mahasiswa, waktu_absen, kode_status_absensi, a.id_jadwal, a.tgl_ditambah, a.tgl_diupdate, j.id_matkul, j.id_dosen FROM tb_absensi AS a INNER JOIN tb_jadwal as j ON a.id_jadwal = j.id_jadwal INNER JOIN tb_mahasiswa AS m ON a.id_mahasiswa = m.id_mhswa INNER JOIN tb_dosen AS d ON j.id_dosen = d.id_dosen";
        if(query_builder.size() != 0){
            query_absensi += " WHERE " + String.join(" AND ", query_builder);
        }
        
        List<Object> rs = db.runQuery(query_absensi);
        listAbsensi.clear();

        for(int i = 0; i < rs.size(); i++){
            Absensi mhswa = new Absensi(rs.get(i));

            listAbsensi.add(mhswa);
        }
    }

    private void fetchData(){
        DB db = new DB();

        listMatkul.clear();
        listStatus.clear();
        listDosen.clear();
        matkulChoiceBox.getItems().clear();
        statusChoiceBox.getItems().clear();
        dosenChoiceBox.getItems().clear();

        matkulChoiceBox.getItems().add("Semua matkul");
        statusChoiceBox.getItems().add("Semua status");
        dosenChoiceBox.getItems().add("Semua dosen");
        matkulChoiceBox.setValue("Semua matkul");
        statusChoiceBox.setValue("Semua status");
        dosenChoiceBox.setValue("Semua dosen");

        String sql_matkul = "SELECT * FROM tb_matkul AS m INNER JOIN tb_jadwal AS j ON m.id_matkul = j.id_matkul WHERE id_kelas = '" + mahasiswa.getIdKelas() + "' GROUP BY m.nm_matkul";
        List<Object> rs_matkul = db.runQuery(sql_matkul);
        for(Object obj : rs_matkul){
            Matkul matkul = new Matkul(obj);
            listMatkul.add(matkul);

            matkulChoiceBox.getItems().add(matkul.getNmMatkul() + " - " + matkul.getIdMatkul());
        }

        String sql_status = "SELECT * FROM tb_status_absensi";
        List<Object> rs_status = db.runQuery(sql_status);
        for(Object obj : rs_status){
            StatusAbsensi status = new StatusAbsensi(obj);
            listStatus.add(status);

            statusChoiceBox.getItems().add(status.getNmStatusAbsensi() + " - " + status.getIdStatusAbsensi());
        }

        String sql_dosen = "SELECT * FROM tb_dosen AS d INNER JOIN tb_jadwal AS j ON j.id_dosen = d.id_dosen WHERE id_kelas = '" + mahasiswa.getIdKelas() + "' GROUP BY d.nm_dosen";
        List<Object> rs_dosen = db.runQuery(sql_dosen);
        for(Object obj : rs_dosen){
            Dosen dosen = new Dosen(obj);
            listDosen.add(dosen);

            dosenChoiceBox.getItems().add(dosen.getNmDosen() + " - " + dosen.getIdDosen());
        }
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

    private StatusAbsensi getStatusAbsensi(String id){
        for(StatusAbsensi s : listStatus){
            if(s.getIdStatusAbsensi().equals(id)){
                return s;
            }
        }

        return new StatusAbsensi();
    }

    public void setSelectedData(Absensi sd){
        selectedData = sd;
    }

    public Absensi getSelectedData(){
        return selectedData;
    }

    public BorderPane getPane(){
        return rootPane;
    }
}
