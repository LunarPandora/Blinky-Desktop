package com.blinky.istesa.Layout.Table;

// import java.text.DecimalFormat;
// import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.blinky.istesa.DB;
import com.blinky.istesa.Model.Kaprodi;

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

public class TableKaprodi extends Table{
    private Kaprodi selectedData;

    private List<Kaprodi> listKaprodi;
    private TableView<Kaprodi> tb = new TableView<Kaprodi>();

    public TableKaprodi(){
        rootPane = new BorderPane();
        tb.setColumnResizePolicy((param) -> true);

        rootPane.setTop(filterPane());
        rootPane.setCenter(getTable());
    }

    public HBox filterPane(){
        HBox filterPane = new HBox();
        List<Node> paneList = new ArrayList<Node>();
        
        Label namaTabel = new Label("Data Kaprodi");
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

    public TableView<Kaprodi> createTable(){
        TableColumn<Kaprodi, String> col_nidn = new TableColumn<>("NIDN");
        TableColumn<Kaprodi, String> col_nama = new TableColumn<>("Nama");
        TableColumn<Kaprodi, String> col_uname = new TableColumn<>("Username");
        TableColumn<Kaprodi, String> col_prodi = new TableColumn<>("Prodi");
        TableColumn<Kaprodi, String> col_insert = new TableColumn<>("Waktu Pendaftaran");
        TableColumn<Kaprodi, String> col_update = new TableColumn<>("Terakhir kali diedit");

        col_nidn.setCellValueFactory(v -> v.getValue().nidnProperty());
        col_nama.setCellValueFactory(v -> v.getValue().nmKaprodiProperty());
        col_uname.setCellValueFactory(v -> v.getValue().uKaprodiProperty());
        col_prodi.setCellValueFactory(v -> v.getValue().idProdiProperty());
        col_insert.setCellValueFactory(v -> v.getValue().tglDitambahProperty());
        col_update.setCellValueFactory(v -> v.getValue().tglDiupdateProperty());

        ArrayList<TableColumn<Kaprodi, String>> col = new ArrayList<>();
        col.add(col_nidn);
        col.add(col_nama);
        col.add(col_uname);
        col.add(col_prodi);
        col.add(col_insert);
        col.add(col_update);

        for(int i = 0; i < col.size(); i++){
            col.get(i).prefWidthProperty().bind(tb.widthProperty().divide(col.size()));
            tb.getColumns().add(col.get(i));
        }

        tb.setRowFactory(tv -> {
            TableRow<Kaprodi> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if(!row.isEmpty()) {
                    Kaprodi rowData = row.getItem();
                    setSelectedData(rowData);
                }
            });

            return row;
        });

        return tb;
    }

    public HBox getTable(){
        HBox table = new HBox();
        TableView<Kaprodi> tb = createTable();

        // table.prefWidthProperty().bind(rootPane.widthProperty());
        HBox.setHgrow(table, Priority.ALWAYS);
        HBox.setHgrow(tb, Priority.ALWAYS);
        VBox.setVgrow(tb, Priority.ALWAYS);

        List<Kaprodi> list_kaprodi = getData();
        // SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy, hh:mm");   

        for(int y = 0; y < list_kaprodi.size(); y++){
            tb.getItems().add(list_kaprodi.get(y));
            // System.out.println(list_mhswa.get(y).tgl_ditambah);        
        }

        table.getChildren().add(tb);

        return table;
    }

    public List<Kaprodi> getData(){
        DB db = new DB();
        String query = "SELECT id_kaprodi, nm_kaprodi, u_kaprodi, pw_kaprodi, foto_kaprodi, nm_prodi, nidn, kp.tgl_ditambah, kp.tgl_diupdate FROM tb_kaprodi AS kp INNER JOIN tb_prodi AS p ON kp.id_prodi = p.id_prodi";

        List<Object> rs = db.runQuery(query);
        listKaprodi = new ArrayList<Kaprodi>();

        for(int i = 0; i < rs.size(); i++){
            // System.out.println(rs.get(i));
            Kaprodi kaprodi = new Kaprodi(rs.get(i));

            listKaprodi.add(kaprodi);
        }
        
        return listKaprodi;
    }

    public void setSelectedData(Kaprodi sd){
        selectedData = sd;
    }

    public Kaprodi getSelectedData(){
        return selectedData;
    }

    public BorderPane getPane(){
        return rootPane;
    }
}
