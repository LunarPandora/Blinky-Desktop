package com.blinky.istesa.Layout.Table;

// import java.text.DecimalFormat;
// import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.blinky.istesa.DB;
import com.blinky.istesa.Model.Matkul;

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

public class TableMatkul extends Table{    
    private Matkul selectedData;

    private List<Matkul> listMatkul;
    private TableView<Matkul> tb = new TableView<Matkul>();

    public TableMatkul(){
        rootPane = new BorderPane();
        tb.setColumnResizePolicy((param) -> true);

        rootPane.setTop(filterPane());
        rootPane.setCenter(getTable());
    }

    public HBox filterPane(){
        HBox filterPane = new HBox();
        List<Node> paneList = new ArrayList<Node>();
        
        Label namaTabel = new Label("Data Matkul");
        TextField searchBar = new TextField();
        ComboBox<String> jenis = new ComboBox<String>();
        
        jenis.getItems().addAll(
            "Contoh1",
            "Contoh2",
            "Contoh3"
        );

        paneList.add(namaTabel);
        paneList.add(searchBar);
        paneList.add(jenis);

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

    public TableView<Matkul> createTable(){
        TableColumn<Matkul, String> col_id = new TableColumn<>("ID");
        TableColumn<Matkul, String> col_nama = new TableColumn<>("Nama Matkul");
        TableColumn<Matkul, String> col_insert = new TableColumn<>("Ditambahkan pada");
        TableColumn<Matkul, String> col_update = new TableColumn<>("Terakhir kali diedit");

        col_id.setCellValueFactory(v -> v.getValue().idMatkulProperty());
        col_nama.setCellValueFactory(v -> v.getValue().nmMatkulProperty());
        col_insert.setCellValueFactory(v -> v.getValue().tglDitambahProperty());
        col_update.setCellValueFactory(v -> v.getValue().tglDiupdateProperty());

        ArrayList<TableColumn<Matkul, String>> col = new ArrayList<>();
        col.add(col_id);
        col.add(col_nama);
        col.add(col_insert);
        col.add(col_update);

        for(int i = 0; i < col.size(); i++){
            col.get(i).prefWidthProperty().bind(tb.widthProperty().divide(col.size()));
            tb.getColumns().add(col.get(i));
        }

        tb.setRowFactory(tv -> {
            TableRow<Matkul> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if(!row.isEmpty()) {
                    Matkul rowData = row.getItem();
                    setSelectedData(rowData);
                }
            });

            return row;
        });

        return tb;
    }

    public HBox getTable(){
        HBox table = new HBox();
        TableView<Matkul> tb = createTable();

        // table.prefWidthProperty().bind(rootPane.widthProperty());
        HBox.setHgrow(table, Priority.ALWAYS);
        HBox.setHgrow(tb, Priority.ALWAYS);
        VBox.setVgrow(tb, Priority.ALWAYS);

        List<Matkul> list_matkul = getData();
        // SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy, hh:mm");   

        for(int y = 0; y < list_matkul.size(); y++){
            tb.getItems().add(list_matkul.get(y));
            // System.out.println(list_mhswa.get(y).tgl_ditambah);        
        }

        table.getChildren().add(tb);

        return table;
    }

    public List<Matkul> getData(){
        DB db = new DB();

        List<Object> rs = db.runQuery("SELECT * FROM tb_matkul");
        listMatkul = new ArrayList<Matkul>();

        for(int i = 0; i < rs.size(); i++){
            // System.out.println(rs.get(i));
            Matkul matkul = new Matkul(rs.get(i));

            listMatkul.add(matkul);
        }
        
        return listMatkul;
    }

    public void setSelectedData(Matkul sd){
        selectedData = sd;
    }

    public Matkul getSelectedData(){
        return selectedData;
    }
    
    public BorderPane getPane(){
        return rootPane;
    }
}
