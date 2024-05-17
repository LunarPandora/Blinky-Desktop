package com.blinky.istesa.Layout.Table;

// import java.text.DecimalFormat;
// import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.blinky.istesa.DB;
import com.blinky.istesa.Model.Jadwal;

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

public class TableJadwal extends Table{
    private Jadwal selectedData;

    private List<Jadwal> listMhswa;
    private TableView<Jadwal> tb = new TableView<Jadwal>();

    public TableJadwal(){
        rootPane = new BorderPane();
        tb.setColumnResizePolicy((param) -> true);

        rootPane.setTop(filterPane());
        rootPane.setCenter(getTable());
    }

    public HBox filterPane(){
        HBox filterPane = new HBox();
        List<Node> paneList = new ArrayList<Node>();
        
        Label namaTabel = new Label("Data Jadwal");
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

    public TableView<Jadwal> createTable(){
        TableColumn<Jadwal, String> col_id = new TableColumn<>("ID");
        TableColumn<Jadwal, String> col_matkul = new TableColumn<>("Mata Kuliah");
        TableColumn<Jadwal, String> col_dosen = new TableColumn<>("Dosen");
        TableColumn<Jadwal, String> col_kelas = new TableColumn<>("Kelas");
        TableColumn<Jadwal, String> col_jamM = new TableColumn<>("Jam Mulai");
        TableColumn<Jadwal, String> col_jamS = new TableColumn<>("Jam Selesai");
        TableColumn<Jadwal, String> col_hari = new TableColumn<>("Hari");
        TableColumn<Jadwal, String> col_insert = new TableColumn<>("Ditambahkan pada");
        TableColumn<Jadwal, String> col_update = new TableColumn<>("Terakhir kali diedit");

        col_id.setCellValueFactory(v -> v.getValue().idJadwalProperty());
        col_matkul.setCellValueFactory(v -> v.getValue().idMatkulProperty());
        col_dosen.setCellValueFactory(v -> v.getValue().idDosenProperty());
        col_kelas.setCellValueFactory(v -> v.getValue().idKelasProperty());
        col_jamM.setCellValueFactory(v -> v.getValue().jamMulaiProperty());
        col_jamS.setCellValueFactory(v -> v.getValue().jamSelesaiProperty());
        col_hari.setCellValueFactory(v -> v.getValue().hariProperty());
        col_insert.setCellValueFactory(v -> v.getValue().tglDitambahProperty());
        col_update.setCellValueFactory(v -> v.getValue().tglDiupdateProperty());

        ArrayList<TableColumn<Jadwal, String>> col = new ArrayList<>();
        col.add(col_id);
        col.add(col_matkul);
        col.add(col_dosen);
        col.add(col_kelas);
        col.add(col_jamM);
        col.add(col_jamS);
        col.add(col_hari);
        col.add(col_insert);
        col.add(col_update);

        for(int i = 0; i < col.size(); i++){
            col.get(i).prefWidthProperty().bind(tb.widthProperty().divide(col.size()));
            tb.getColumns().add(col.get(i));
        }

        tb.setRowFactory(tv -> {
            TableRow<Jadwal> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if(!row.isEmpty()) {
                    Jadwal rowData = row.getItem();
                    setSelectedData(rowData);
                }
            });

            return row;
        });

        return tb;
    }

    public HBox getTable(){
        HBox table = new HBox();
        TableView<Jadwal> tb = createTable();

        // table.prefWidthProperty().bind(rootPane.widthProperty());
        HBox.setHgrow(table, Priority.ALWAYS);
        HBox.setHgrow(tb, Priority.ALWAYS);
        VBox.setVgrow(tb, Priority.ALWAYS);

        List<Jadwal> list_mhswa = getData();
        // SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy, hh:mm");   

        for(int y = 0; y < list_mhswa.size(); y++){
            tb.getItems().add(list_mhswa.get(y));
            // System.out.println(list_mhswa.get(y).tgl_ditambah);        
        }

        table.getChildren().add(tb);

        return table;
    }

    public List<Jadwal> getData(){
        DB db = new DB();
        String query = "SELECT id_jadwal, jam_mulai, jam_selesai, hari, nm_kelas, nm_dosen, nm_matkul, j.tgl_ditambah, j.tgl_diupdate FROM tb_jadwal AS j INNER JOIN tb_kelas AS k ON j.id_kelas = k.id_kelas INNER JOIN tb_dosen AS d ON j.id_dosen = d.id_dosen INNER JOIN tb_matkul AS m ON j.id_matkul = m.id_matkul ORDER BY j.id_jadwal ASC;";

        List<Object> rs = db.runQuery(query);
        listMhswa = new ArrayList<Jadwal>();

        for(int i = 0; i < rs.size(); i++){
            // System.out.println(rs.get(i));
            Jadwal mhswa = new Jadwal(rs.get(i));

            listMhswa.add(mhswa);
        }
        
        return listMhswa;
    }

    public void setSelectedData(Jadwal sd){
        selectedData = sd;
    }

    public Jadwal getSelectedData(){
        return selectedData;
    }

    public BorderPane getPane(){
        return rootPane;
    }
}
