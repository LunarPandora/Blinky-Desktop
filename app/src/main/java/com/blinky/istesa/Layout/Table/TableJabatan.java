package com.blinky.istesa.Layout.Table;

import java.util.ArrayList;
import java.util.List;

import com.blinky.istesa.DB;
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

public class TableJabatan extends Table {
    private Jabatan selectedData;

    private List<Jabatan> listJabatan = new ArrayList<Jabatan>();
    private TableView<Jabatan> tb = new TableView<Jabatan>();

    TextField searchBar = new TextField();

    public TableJabatan(){
        rootPane = new BorderPane();
        tb.setColumnResizePolicy((param) -> true);

        searchBar.setPromptText("Cari jabatan...");
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
        
        Label namaTabel = new Label("Data Jabatan");

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

    public TableView<Jabatan> createTable(){
        TableColumn<Jabatan, String> col_id = new TableColumn<>("ID");
        TableColumn<Jabatan, String> col_jabatan = new TableColumn<>("Jabatan");
        TableColumn<Jabatan, String> col_insert = new TableColumn<>("Ditambahkan pada");
        TableColumn<Jabatan, String> col_update = new TableColumn<>("Terakhir kali diedit");

        col_id.setCellValueFactory(v -> v.getValue().idJabatanProperty());
        col_jabatan.setCellValueFactory(v -> v.getValue().nmJabatanProperty());
        col_insert.setCellValueFactory(v -> v.getValue().tglDitambahProperty());
        col_update.setCellValueFactory(v -> v.getValue().tglDiupdateProperty());

        col_id.prefWidthProperty().bind(tb.widthProperty().multiply(0.1));
        col_jabatan.prefWidthProperty().bind(tb.widthProperty().multiply(0.5));
        col_insert.prefWidthProperty().bind(tb.widthProperty().multiply(0.2));
        col_update.prefWidthProperty().bind(tb.widthProperty().multiply(0.2));

        ArrayList<TableColumn<Jabatan, String>> col = new ArrayList<>();
        col.add(col_id);
        col.add(col_jabatan);
        col.add(col_insert);
        col.add(col_update);

        for(int i = 0; i < col.size(); i++){
            tb.getColumns().add(col.get(i));
        }

        tb.setRowFactory(tv -> {
            TableRow<Jabatan> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if(!row.isEmpty()) {
                    Jabatan rowData = row.getItem();
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

        for(int y = 0; y < listJabatan.size(); y++){
            tb.getItems().add(listJabatan.get(y));
        }

        table.getChildren().add(tb);

        return table;
    }

    public void getData(){
        DB db = new DB();
        List<String> query_builder = new ArrayList<String>();

        if(searchBar.textProperty().getValue() != null && searchBar.textProperty().getValue() != ""){
            query_builder.add("nm_jabatan LIKE '%" + searchBar.textProperty().getValue() + "%'");
        }

        String query_jabatan = "SELECT * FROM tb_jabatan";
        if(query_builder.size() != 0){
            query_jabatan += " WHERE " + String.join(" AND ", query_builder);
        }

        List<Object> rs = db.runQuery(query_jabatan + " ORDER BY id_jabatan ASC;");
        listJabatan.clear();

        for(int i = 0; i < rs.size(); i++){
            Jabatan mhswa = new Jabatan(rs.get(i));

            listJabatan.add(mhswa);
        }
    }

    public void setSelectedData(Jabatan sd){
        selectedData = sd;
    }

    public Jabatan getSelectedData(){
        return selectedData;
    }

    public BorderPane getPane(){
        return rootPane;
    }
}
