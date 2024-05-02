package com.blinky.istesa.TableLayout;

// import java.text.DecimalFormat;
// import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.blinky.istesa.DB;
import com.blinky.istesa.obj.Warek;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class TableWarek implements Table{
    private final BorderPane rootPane;
    
    private List<Warek> listWarek;
    private TableView<Warek> tb = new TableView<Warek>();

    public TableWarek(){
        rootPane = new BorderPane();
        tb.setColumnResizePolicy((param) -> true);

        rootPane.setTop(filterPane());
        rootPane.setCenter(getTable());
    }

    public HBox filterPane(){
        HBox filterPane = new HBox();
        List<Node> paneList = new ArrayList<Node>();
        
        Label namaTabel = new Label("Data Warek");
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

    public TableView<Warek> createTable(){
        TableColumn<Warek, String> col_nidn = new TableColumn<>("NIDN");
        TableColumn<Warek, String> col_nama = new TableColumn<>("Nama");
        TableColumn<Warek, String> col_uname = new TableColumn<>("Username");
        TableColumn<Warek, String> col_insert = new TableColumn<>("Waktu Pendaftaran");
        TableColumn<Warek, String> col_update = new TableColumn<>("Terakhir kali diedit");

        col_nidn.setCellValueFactory(v -> v.getValue().nidnProperty());
        col_nama.setCellValueFactory(v -> v.getValue().nmWarekProperty());
        col_uname.setCellValueFactory(v -> v.getValue().uWarekProperty());
        col_insert.setCellValueFactory(v -> v.getValue().tglDitambahProperty());
        col_update.setCellValueFactory(v -> v.getValue().tglDiupdateProperty());

        ArrayList<TableColumn<Warek, String>> col = new ArrayList<>();
        col.add(col_nidn);
        col.add(col_nama);
        col.add(col_uname);
        col.add(col_insert);
        col.add(col_update);

        for(int i = 0; i < col.size(); i++){
            col.get(i).prefWidthProperty().bind(tb.widthProperty().divide(col.size()));
            tb.getColumns().add(col.get(i));
        }

        return tb;
    }

    public HBox getTable(){
        HBox table = new HBox();
        TableView<Warek> tb = createTable();

        // table.prefWidthProperty().bind(rootPane.widthProperty());
        HBox.setHgrow(table, Priority.ALWAYS);
        HBox.setHgrow(tb, Priority.ALWAYS);
        VBox.setVgrow(tb, Priority.ALWAYS);

        List<Warek> list_warek = getData();
        // SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy, hh:mm");   

        for(int y = 0; y < list_warek.size(); y++){
            tb.getItems().add(list_warek.get(y));
            // System.out.println(list_mhswa.get(y).tgl_ditambah);        
        }

        table.getChildren().add(tb);

        return table;
    }

    public List<Warek> getData(){
        DB db = new DB();

        List<Object> rs = db.runQuery("SELECT * FROM tb_warek");
        listWarek = new ArrayList<Warek>();

        for(int i = 0; i < rs.size(); i++){
            // System.out.println(rs.get(i));
            Warek warek = new Warek(rs.get(i));

            listWarek.add(warek);
        }
        
        return listWarek;
    }

    public BorderPane getPane(){
        return rootPane;
    }
}
