package com.blinky.istesa.Layout.Table;

// import java.text.DecimalFormat;
// import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.blinky.istesa.DB;
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

public class TableProdi extends Table{
    private Prodi selectedData;

    private List<Prodi> listProdi = new ArrayList<Prodi>();
    private TableView<Prodi> tb = new TableView<Prodi>();

    TextField searchBar = new TextField();

    public TableProdi(){
        rootPane = new BorderPane();
        tb.setColumnResizePolicy((param) -> true);

        searchBar.setPromptText("Cari prodi...");
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
        
        Label namaTabel = new Label("Data Prodi");

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

    public TableView<Prodi> createTable(){
        TableColumn<Prodi, String> col_id = new TableColumn<>("ID");
        TableColumn<Prodi, String> col_nama = new TableColumn<>("Nama Prodi");
        TableColumn<Prodi, String> col_insert = new TableColumn<>("Ditambahkan pada");
        TableColumn<Prodi, String> col_update = new TableColumn<>("Terakhir kali diedit");

        col_id.setCellValueFactory(v -> v.getValue().idProdiProperty());
        col_nama.setCellValueFactory(v -> v.getValue().nmProdiProperty());
        col_insert.setCellValueFactory(v -> v.getValue().tglDitambahProperty());
        col_update.setCellValueFactory(v -> v.getValue().tglDiupdateProperty());

        col_id.prefWidthProperty().bind(tb.widthProperty().multiply(0.1));
        col_nama.prefWidthProperty().bind(tb.widthProperty().multiply(0.5));
        col_insert.prefWidthProperty().bind(tb.widthProperty().multiply(0.2));
        col_update.prefWidthProperty().bind(tb.widthProperty().multiply(0.2));

        ArrayList<TableColumn<Prodi, String>> col = new ArrayList<>();
        col.add(col_id);
        col.add(col_nama);
        col.add(col_insert);
        col.add(col_update);

        for(int i = 0; i < col.size(); i++){
            tb.getColumns().add(col.get(i));
        }

        tb.setRowFactory(tv -> {
            TableRow<Prodi> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if(!row.isEmpty()) {
                    Prodi rowData = row.getItem();
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

        for(int y = 0; y < listProdi.size(); y++){
            tb.getItems().add(listProdi.get(y));
        }

        table.getChildren().add(tb);

        return table;
    }

    public void getData(){
        DB db = new DB();
        List<String> query_builder = new ArrayList<String>();

        if(searchBar.textProperty().getValue() != null && searchBar.textProperty().getValue() != ""){
            query_builder.add("nm_prodi LIKE '%" + searchBar.textProperty().getValue() + "%'");
        }

        String query_prodi = "SELECT * FROM tb_prodi";
        if(query_builder.size() != 0){
            query_prodi += " WHERE " + String.join(" AND ", query_builder);
        }

        List<Object> rs = db.runQuery(query_prodi);
        listProdi.clear();

        for(int i = 0; i < rs.size(); i++){
            Prodi prodi = new Prodi(rs.get(i));

            listProdi.add(prodi);
        }
    }

    public void setSelectedData(Prodi sd){
        selectedData = sd;
    }

    public Prodi getSelectedData(){
        return selectedData;
    }

    public BorderPane getPane(){
        return rootPane;
    }
}
