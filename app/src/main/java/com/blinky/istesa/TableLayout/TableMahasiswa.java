package com.blinky.istesa.TableLayout;

// import java.text.DecimalFormat;
// import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.blinky.istesa.DB;
import com.blinky.istesa.obj.Mahasiswa;

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

public class TableMahasiswa implements Table{
    private final BorderPane rootPane;
    
    private List<Mahasiswa> listMhswa;
    private TableView<Mahasiswa> tb = new TableView<Mahasiswa>();

    public TableMahasiswa(){
        rootPane = new BorderPane();
        tb.setColumnResizePolicy((param) -> true);

        rootPane.setTop(filterPane());
        rootPane.setCenter(getTable());
    }

    public HBox filterPane(){
        HBox filterPane = new HBox();
        List<Node> paneList = new ArrayList<Node>();
        
        Label namaTabel = new Label("Data Mahasiswa");
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
        col_kelas.setCellValueFactory(v -> v.getValue().idKelasProperty());
        col_prodi.setCellValueFactory(v -> v.getValue().idProdiProperty());
        col_angkt.setCellValueFactory(v -> v.getValue().angkatanProperty());
        col_insert.setCellValueFactory(v -> v.getValue().tglDitambahProperty());
        col_update.setCellValueFactory(v -> v.getValue().tglDiupdateProperty());

        ArrayList<TableColumn<Mahasiswa, String>> col = new ArrayList<>();
        col.add(col_nim);
        col.add(col_nama);
        col.add(col_kelas);
        col.add(col_prodi);
        col.add(col_angkt);
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
        TableView<Mahasiswa> tb = createTable();

        // table.prefWidthProperty().bind(rootPane.widthProperty());
        HBox.setHgrow(table, Priority.ALWAYS);
        HBox.setHgrow(tb, Priority.ALWAYS);
        VBox.setVgrow(tb, Priority.ALWAYS);

        List<Mahasiswa> list_mhswa = getData();
        // SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy, hh:mm");   

        for(int y = 0; y < list_mhswa.size(); y++){
            tb.getItems().add(list_mhswa.get(y));
            // System.out.println(list_mhswa.get(y).tgl_ditambah);        
        }

        table.getChildren().add(tb);

        return table;
    }

    public List<Mahasiswa> getData(){
        DB db = new DB();
        String query_admin = "SELECT id_mhswa, nm_kelas, nm_prodi, nm_mhswa, angkatan, m.tgl_ditambah, m.tgl_diupdate, foto_mhswa, id_admin FROM tb_mahasiswa AS m INNER JOIN tb_kelas AS k ON m.id_kelas = k.id_kelas INNER JOIN tb_prodi AS p ON m.id_prodi = p.id_prodi;";

        List<Object> rs = db.runQuery(query_admin);
        listMhswa = new ArrayList<Mahasiswa>();

        for(int i = 0; i < rs.size(); i++){
            // System.out.println(rs.get(i));
            Mahasiswa mhswa = new Mahasiswa(rs.get(i));

            listMhswa.add(mhswa);
        }
        
        return listMhswa;
    }

    public BorderPane getPane(){
        return rootPane;
    }
}
