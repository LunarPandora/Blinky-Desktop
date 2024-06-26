package com.blinky.istesa.Layout.Table;

// import java.text.DecimalFormat;
// import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.blinky.istesa.DB;
import com.blinky.istesa.Model.Kelas;

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

public class TableKelas extends Table{
    private Kelas selectedData;

    private List<Kelas> listKelas = new ArrayList<Kelas>();
    private TableView<Kelas> tb = new TableView<Kelas>();

    TextField searchBar = new TextField();

    public TableKelas(){
        rootPane = new BorderPane();
        tb.setColumnResizePolicy((param) -> true);

        searchBar.setPromptText("Cari kelas...");
        searchBar.setOnKeyTyped(e -> {
            rootPane.setCenter(null);
            rootPane.setCenter(getTable());
        });

        rootPane.setTop(filterPane());
        rootPane.setCenter(getTable());
    }

    public HBox filterPane(){
        HBox filterPane = new HBox();
        List<Node> paneList = new ArrayList<Node>();
        
        Label namaTabel = new Label("Data Kelas");

        paneList.add(namaTabel);
        paneList.add(searchBar);

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

    public TableView<Kelas> createTable(){
        TableColumn<Kelas, String> col_id = new TableColumn<>("ID");
        TableColumn<Kelas, String> col_nama = new TableColumn<>("Nama Kelas");
        TableColumn<Kelas, String> col_insert = new TableColumn<>("Ditambahkan pada");
        TableColumn<Kelas, String> col_update = new TableColumn<>("Terakhir kali diedit");

        col_id.setCellValueFactory(v -> v.getValue().idKelasProperty());
        col_nama.setCellValueFactory(v -> v.getValue().nmKelasProperty());
        col_insert.setCellValueFactory(v -> v.getValue().tglDitambahProperty());
        col_update.setCellValueFactory(v -> v.getValue().tglDiupdateProperty());

        col_id.prefWidthProperty().bind(tb.widthProperty().multiply(0.1));
        col_nama.prefWidthProperty().bind(tb.widthProperty().multiply(0.5));
        col_insert.prefWidthProperty().bind(tb.widthProperty().multiply(0.2));
        col_update.prefWidthProperty().bind(tb.widthProperty().multiply(0.2));

        ArrayList<TableColumn<Kelas, String>> col = new ArrayList<>();
        col.add(col_id);
        col.add(col_nama);
        col.add(col_insert);
        col.add(col_update);

        for(int i = 0; i < col.size(); i++){
            tb.getColumns().add(col.get(i));
        }

        tb.setRowFactory(tv -> {
            TableRow<Kelas> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if(!row.isEmpty()) {
                    Kelas rowData = row.getItem();
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

        for(int y = 0; y < listKelas.size(); y++){
            tb.getItems().add(listKelas.get(y));
        }

        table.getChildren().add(tb);

        return table;
    }

    public void getData(){
        DB db = new DB();
        List<String> query_builder = new ArrayList<String>();

        if(searchBar.textProperty().getValue() != null && searchBar.textProperty().getValue() != ""){
            query_builder.add("nm_kelas LIKE '%" + searchBar.textProperty().getValue() + "%'");
        }

        String query_kelas = "SELECT * FROM tb_kelas";
        if(query_builder.size() != 0){
            query_kelas += " WHERE " + String.join(" AND ", query_builder);
        }

        List<Object> rs = db.runQuery(query_kelas);
        listKelas.clear();

        for(int i = 0; i < rs.size(); i++){
            Kelas kelas = new Kelas(rs.get(i));

            listKelas.add(kelas);
        }
    }

    public void setSelectedData(Kelas sd){
        selectedData = sd;
    }

    public Kelas getSelectedData(){
        return selectedData;
    }

    public BorderPane getPane(){
        return rootPane;
    }
}
