package com.blinky.istesa.TableLayout;

// import java.text.DecimalFormat;
// import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.blinky.istesa.DB;
import com.blinky.istesa.obj.Absensi;

import javafx.beans.property.ReadOnlyObjectWrapper;
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

public class TableAbsensi implements Table{
    private final BorderPane rootPane;
    
    private List<Absensi> listMhswa;
    private TableView<Absensi> tb = new TableView<Absensi>();

    public TableAbsensi(String id, String jenis){
        rootPane = new BorderPane();
        tb.setColumnResizePolicy((param) -> true);

        rootPane.setTop(filterPane());
        rootPane.setCenter(getTable(id, jenis));
    }

    public HBox filterPane(){
        HBox filterPane = new HBox();
        List<Node> paneList = new ArrayList<Node>();
        
        Label namaTabel = new Label("Data Absensi");
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

    public TableView<Absensi> createTable(String jenis){
        if(jenis.equals("dosen")){
            TableColumn<Absensi, Number> col_id = new TableColumn<>("No.");
            TableColumn<Absensi, String> col_mhswa = new TableColumn<>("Nama Mahasiswa");
            TableColumn<Absensi, String> col_jdwl = new TableColumn<>("Mata Kuliah");
            TableColumn<Absensi, String> col_wktu= new TableColumn<>("Waktu Absensi");
            TableColumn<Absensi, String> col_status = new TableColumn<>("Status");
            TableColumn<Absensi, String> col_insert = new TableColumn<>("Ditambahkan pada");
            TableColumn<Absensi, String> col_update = new TableColumn<>("Terakhir kali diedit");

            col_id.setCellValueFactory(v -> new ReadOnlyObjectWrapper<Number>(tb.getItems().indexOf(v.getValue()) + 1));
            col_mhswa.setCellValueFactory(v -> v.getValue().idMahasiswaProperty());
            col_jdwl.setCellValueFactory(v -> v.getValue().idJadwalProperty());
            col_wktu.setCellValueFactory(v -> v.getValue().waktuAbsenProperty());
            col_status.setCellValueFactory(v -> v.getValue().statusAbsensiProperty());
            col_insert.setCellValueFactory(v -> v.getValue().tglDitambahProperty());
            col_update.setCellValueFactory(v -> v.getValue().tglDiupdateProperty());

            ArrayList<TableColumn<Absensi, ?>> col = new ArrayList<>();
            col.add(col_id);
            col.add(col_mhswa);
            col.add(col_jdwl);
            col.add(col_wktu);
            col.add(col_status);
            col.add(col_insert);
            col.add(col_update);

            for(int i = 0; i < col.size(); i++){
                col.get(i).prefWidthProperty().bind(tb.widthProperty().divide(col.size()));
                tb.getColumns().add(col.get(i));
            }

            return tb;
        }
        else{
            TableColumn<Absensi, Number> col_id = new TableColumn<>("No.");
            TableColumn<Absensi, String> col_mhswa = new TableColumn<>("Nama Dosen");
            TableColumn<Absensi, String> col_jdwl = new TableColumn<>("Mata Kuliah");
            TableColumn<Absensi, String> col_wktu= new TableColumn<>("Waktu Absensi");
            TableColumn<Absensi, String> col_status = new TableColumn<>("Status");

            col_id.setCellValueFactory(v -> new ReadOnlyObjectWrapper<Number>(tb.getItems().indexOf(v.getValue()) + 1));
            col_mhswa.setCellValueFactory(v -> v.getValue().idMahasiswaProperty());
            col_jdwl.setCellValueFactory(v -> v.getValue().idJadwalProperty());
            col_wktu.setCellValueFactory(v -> v.getValue().waktuAbsenProperty());
            col_status.setCellValueFactory(v -> v.getValue().statusAbsensiProperty());

            ArrayList<TableColumn<Absensi, ?>> col = new ArrayList<>();
            col.add(col_id);
            col.add(col_mhswa);
            col.add(col_jdwl);
            col.add(col_wktu);
            col.add(col_status);

            for(int i = 0; i < col.size(); i++){
                col.get(i).prefWidthProperty().bind(tb.widthProperty().divide(col.size()));
                tb.getColumns().add(col.get(i));
            }

            return tb;
        }
    }

    public List<Absensi> getData(String id, String jenis){
        DB db = new DB();
        String query_dosen = "SELECT id_absensi, nm_mhswa, nm_matkul, waktu_absen, nm_status_absensi, ab.tgl_ditambah, ab.tgl_diupdate FROM tb_absensi AS ab INNER JOIN tb_mahasiswa AS m ON ab.id_mhswa = m.id_mhswa INNER JOIN tb_jadwal AS j ON ab.id_jadwal = j.id_jadwal INNER JOIN tb_matkul AS mk ON j.id_matkul = mk.id_matkul INNER JOIN tb_status_absensi AS sa ON ab.id_status_absensi = sa.id_status_absensi WHERE j.id_dosen = '" + id + "' ORDER BY id_absensi DESC";

        String query_mhswa = "SELECT id_absensi, nm_dosen, nm_matkul, waktu_absen, nm_status_absensi, ab.tgl_ditambah, ab.tgl_diupdate FROM tb_absensi AS ab INNER JOIN tb_mahasiswa AS m ON ab.id_mhswa = m.id_mhswa INNER JOIN tb_jadwal AS j ON ab.id_jadwal = j.id_jadwal INNER JOIN tb_matkul AS mk ON j.id_matkul = mk.id_matkul INNER JOIN tb_status_absensi AS sa ON ab.id_status_absensi = sa.id_status_absensi INNER JOIN tb_dosen AS d ON j.id_dosen = d.id_dosen WHERE ab.id_mhswa = '" + id + "' ORDER BY id_absensi DESC";

        List<Object> rs = db.runQuery(jenis.equals("mhswa") ? query_mhswa : query_dosen);
        listMhswa = new ArrayList<Absensi>();

        for(int i = 0; i < rs.size(); i++){
            // System.out.println(rs.get(i));
            Absensi mhswa = new Absensi(rs.get(i));

            listMhswa.add(mhswa);
        }
        
        return listMhswa;
    }

    
    public HBox getTable(String id, String jenis){
        HBox table = new HBox();
        TableView<Absensi> tb = createTable(jenis);

        // table.prefWidthProperty().bind(rootPane.widthProperty());
        HBox.setHgrow(table, Priority.ALWAYS);
        HBox.setHgrow(tb, Priority.ALWAYS);
        VBox.setVgrow(tb, Priority.ALWAYS);

        List<Absensi> list_mhswa = getData(id, jenis);
        // SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy, hh:mm");   

        for(int y = 0; y < list_mhswa.size(); y++){
            tb.getItems().add(list_mhswa.get(y));
            // System.out.println(list_mhswa.get(y).tgl_ditambah);        
        }

        table.getChildren().add(tb);

        return table;
    }

    public BorderPane getPane(){
        return rootPane;
    }
}
