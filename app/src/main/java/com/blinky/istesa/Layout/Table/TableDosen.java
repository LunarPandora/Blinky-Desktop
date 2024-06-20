package com.blinky.istesa.Layout.Table;

// import java.text.DecimalFormat;
// import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.blinky.istesa.DB;
import com.blinky.istesa.Model.Dosen;
import com.blinky.istesa.Model.Jabatan;
import com.blinky.istesa.Model.Kelas;
import com.blinky.istesa.Model.Prodi;

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

public class TableDosen extends Table{
    private Dosen selectedData;

    private List<Dosen> listDosen = new ArrayList<Dosen>();
    private TableView<Dosen> tb = new TableView<Dosen>();

    private List<Jabatan> listJabatan = new ArrayList<Jabatan>();
    private List<Prodi> listProdi = new ArrayList<Prodi>(); 

    TextField searchBar = new TextField();
    ComboBox<String> prodiChoiceBox = new ComboBox<String>();

    public TableDosen(){
        rootPane = new BorderPane();
        tb.setColumnResizePolicy((param) -> true);

        searchBar.setPromptText("Cari dosen...");
        searchBar.setOnKeyTyped(e -> {
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
        
        Label namaTabel = new Label("Data Dosen");

        paneList.add(namaTabel);
        paneList.add(searchBar);
        paneList.add(prodiChoiceBox);

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

    public TableView<Dosen> createTable(){
        TableColumn<Dosen, String> col_nidn = new TableColumn<>("NIDN");
        TableColumn<Dosen, String> col_nama = new TableColumn<>("Nama");
        TableColumn<Dosen, String> col_jbtn = new TableColumn<>("Jabatan");
        TableColumn<Dosen, String> col_prodi = new TableColumn<>("Prodi");
        TableColumn<Dosen, String> col_uname = new TableColumn<>("Username");
        TableColumn<Dosen, String> col_insert = new TableColumn<>("Waktu Pendaftaran");
        TableColumn<Dosen, String> col_update = new TableColumn<>("Terakhir kali diedit");

        col_nidn.setCellValueFactory(v -> v.getValue().nidnProperty());
        col_nama.setCellValueFactory(v -> v.getValue().nmDosenProperty());
        col_jbtn.setCellValueFactory(v -> getJabatan(v.getValue().getIdJabatan()).nmJabatanProperty());
        col_prodi.setCellValueFactory(v -> getProdi(v.getValue().getIdProdi()).nmProdiProperty());
        col_uname.setCellValueFactory(v -> v.getValue().uDosenProperty());
        col_insert.setCellValueFactory(v -> v.getValue().tglDitambahProperty());
        col_update.setCellValueFactory(v -> v.getValue().tglDiupdateProperty());

        col_nidn.prefWidthProperty().bind(tb.widthProperty().multiply(0.08));
        col_nama.prefWidthProperty().bind(tb.widthProperty().multiply(0.2));
        col_jbtn.prefWidthProperty().bind(tb.widthProperty().multiply(0.2));
        col_prodi.prefWidthProperty().bind(tb.widthProperty().multiply(0.16));
        col_uname.prefWidthProperty().bind(tb.widthProperty().multiply(0.10));
        col_insert.prefWidthProperty().bind(tb.widthProperty().multiply(0.13));
        col_update.prefWidthProperty().bind(tb.widthProperty().multiply(0.13));

        ArrayList<TableColumn<Dosen, String>> col = new ArrayList<>();
        col.add(col_nidn);
        col.add(col_nama);
        col.add(col_jbtn);
        col.add(col_prodi);
        col.add(col_uname);
        col.add(col_insert);
        col.add(col_update);

        for(int i = 0; i < col.size(); i++){
            tb.getColumns().add(col.get(i));
        }

        tb.setRowFactory(tv -> {
            TableRow<Dosen> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if(!row.isEmpty()) {
                    Dosen rowData = row.getItem();
                    setSelectedData(rowData);
                }
            });

            return row;
        });

        return tb;
    }

    public HBox getTable(){
        HBox table = new HBox();
        TableView<Dosen> tb = createTable();

        tb.getItems().clear();
        tb.getColumns().clear();

        tb = createTable();
        
        HBox.setHgrow(table, Priority.ALWAYS);
        HBox.setHgrow(tb, Priority.ALWAYS);
        VBox.setVgrow(tb, Priority.ALWAYS);

        getData();

        for(int y = 0; y < listDosen.size(); y++){
            tb.getItems().add(listDosen.get(y));
        }

        table.getChildren().add(tb);

        return table;
    }

    public void getData(){
        DB db = new DB();
        List<String> query_builder = new ArrayList<String>();

        if(searchBar.textProperty().getValue() != null && searchBar.textProperty().getValue() != ""){
            query_builder.add("nm_dosen LIKE '%" + searchBar.textProperty().getValue() + "%'");
        }
        if(prodiChoiceBox.getValue() != "Semua prodi"){
            for(Prodi p : listProdi){
                if(p.getIdProdi().equals(prodiChoiceBox.getValue().split(" - ")[1])){
                    query_builder.add("id_prodi = '" + p.getIdProdi() + "'");
                }
            }
        }

        String query_dosen = "SELECT * FROM tb_dosen";
        if(query_builder.size() != 0){
            query_dosen += " WHERE " + String.join(" AND ", query_builder);
        }

        List<Object> rs = db.runQuery(query_dosen);
        listDosen.clear();

        for(int i = 0; i < rs.size(); i++){
            Dosen dosen = new Dosen(rs.get(i));

            listDosen.add(dosen);
        }
    }

    private void fetchData(){
        DB db = new DB();

        listJabatan.clear();
        listProdi.clear();
        prodiChoiceBox.getItems().clear();

        prodiChoiceBox.getItems().add("Semua prodi");
        prodiChoiceBox.setValue("Semua prodi");

        String sql_jabatan = "SELECT * FROM tb_jabatan";
        List<Object> rs_jabatan = db.runQuery(sql_jabatan);
        for(Object obj : rs_jabatan){
            Jabatan jabatan = new Jabatan(obj);
            listJabatan.add(jabatan);
        }

        String sql_prodi = "SELECT * FROM tb_prodi";
        List<Object> rs_prodi = db.runQuery(sql_prodi);
        for(Object obj : rs_prodi){
            Prodi prodi = new Prodi(obj);
            listProdi.add(prodi);

            prodiChoiceBox.getItems().add(prodi.getNmProdi() + " - " + prodi.getIdProdi());
        }
    }

    private Jabatan getJabatan(String id){
        for(Jabatan j : listJabatan){
            if(j.getIdJabatan().equals(id)){
                return j;
            }
        }

        return new Jabatan();
    }

    private Prodi getProdi(String id){
        for(Prodi p : listProdi){
            if(p.getIdProdi().equals(id)){
                return p;
            }
        }

        return new Prodi();
    }


    public void setSelectedData(Dosen sd){
        selectedData = sd;
    }

    public Dosen getSelectedData(){
        return selectedData;
    }

    public BorderPane getPane(){
        return rootPane;
    }
}
