package com.blinky.istesa.Layout.Form;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.blinky.istesa.App;
import com.blinky.istesa.DB;
import com.blinky.istesa.Home;
import com.blinky.istesa.Layout.Input.Input;
import com.blinky.istesa.Layout.Input.NumPicker;
import com.blinky.istesa.Layout.Input.TimePicker;
import com.blinky.istesa.Model.Absensi;
import com.blinky.istesa.Model.Dosen;
import com.blinky.istesa.Model.Jadwal;
import com.blinky.istesa.Model.Mahasiswa;
import com.blinky.istesa.Model.Kelas;
import com.blinky.istesa.Model.Matkul;
import com.blinky.istesa.Model.Prodi;
import com.blinky.istesa.Model.StatusAbsensi;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FormAbsensiDosen{
    private static Stage window;
    private static Home home;
    private static BorderPane border = new BorderPane();
    private static Scene scene = new Scene(border, 1280, 720);
    private static Absensi data;

    private static String idDosen;
    
    private static List<Kelas> listKelas = new ArrayList<Kelas>();
    private static List<Mahasiswa> listMahasiswa = new ArrayList<Mahasiswa>();
    private static List<Jadwal> listJadwal = new ArrayList<Jadwal>();
    private static List<StatusAbsensi> listStatus = new ArrayList<StatusAbsensi>();

    private static List<Mahasiswa> tempListMahasiswa = new ArrayList<Mahasiswa>();
    private static List<Jadwal> tempListJadwal = new ArrayList<Jadwal>();

    private static Input[] formInputList = new Input[]{
        new Input("idKelas", new Label("Kelas"), new ComboBox<String>(), -1, "Pilih Kelas").setType("ComboBox"),
        new Input("idMahasiswa", new Label("Mahasiswa"), new ComboBox<String>(), 1, "Masukkan nama mahasiswa").setType("ComboBox"),
        new Input("idJadwal", new Label("Jadwal"), new ComboBox<String>(), 4, "Pilih jadwal").setType("ComboBox"),
        new Input("waktuAbsensi", new Label("Waktu Absen"), new DatePicker(), 2, "Masukkan waktu absensi").setType("DatePicker"),
        new Input("idStatus", new Label("Status Absensi"), new ComboBox<String>(), 3, "Pilih status absensi").setType("ComboBox"),
        new Input("jamAbsensi", new Label("Jam Absen"), new TimePicker(1, 1), 2, "Masukkan jam absensi").setType("TimePicker"),
    };

    private static List<Input> readyInput = new ArrayList<Input>();

    public FormAbsensiDosen(Stage rStage, Home h, String type, Dosen dosen) throws Exception {
        home = h;
        idDosen = dosen.getIdDosen();

        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.initOwner(rStage);
        window.initStyle(StageStyle.TRANSPARENT);
        window.setMaximized(true);

        getListKelas();
        getListStatus();

        border.setOpacity(1);
        if(type.equals("create")){
            border.setCenter(createFormAdd());
        }

        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add(App.class.getResource("css/form.css").toExternalForm());

        window.setScene(scene);
        window.show();

        border.setPadding(
            new Insets(
                (window.getHeight() / 2) - 275, 
                (window.getWidth() / 2) - 350, 
                (window.getHeight() / 2) - 275, 
                (window.getWidth() / 2) - 350
            )
        );
    }

    public FormAbsensiDosen(Stage rStage, Home h, String type, Absensi obj, Dosen dosen){
        home = h;
        data = obj;
        idDosen = dosen.getIdDosen();

        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.initOwner(rStage);
        window.initStyle(StageStyle.TRANSPARENT);
        window.setMaximized(true);

        getListKelas();
        getListStatus();
        fetchTempData();

        border.setOpacity(1);
        if(type.equals("edit")){
            border.setCenter(createFormEdit());
        }
        else{
            border.setCenter(createFormDelete());
        }

        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add(App.class.getResource("css/form.css").toExternalForm());

        window.setScene(scene);
        window.show();

        border.setPadding(
            new Insets(
                (window.getHeight() / 2) - 275, 
                (window.getWidth() / 2) - 350, 
                (window.getHeight() / 2) - 275, 
                (window.getWidth() / 2) - 350
            )
        );
    }

    public VBox alertBox(String alert){
        VBox grid = new VBox();
        grid.setAlignment(Pos.CENTER_LEFT);
        grid.setSpacing(30);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text formTitle = new Text("Informasi");
        Text alertContent = new Text(alert);

        Button closeDialog = new Button("Kembali");
        closeDialog.setPrefWidth(Double.MAX_VALUE);
        closeDialog.setOnAction(e -> {
            home.refreshTable();

            window.close();
        });

        grid.getStyleClass().addAll(new String[]{"bg-white", "rounded"});
        closeDialog.getStyleClass().addAll(new String[]{"btn", "btn-primary"});
        formTitle.getStyleClass().addAll(new String[]{"title"});

        grid.getChildren().addAll(formTitle, alertContent, closeDialog);

        return grid;
    }

    public GridPane createFormAdd() throws Exception {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER_LEFT);
        grid.setHgap(10);
        grid.setVgap(30);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text formTitle = new Text("Tambah Absensi");
        
        Button btnAcc = new Button("Simpan");
        btnAcc.setMaxWidth(Double.MAX_VALUE);
        btnAcc.setOnAction(e -> {
            Absensi absensi = new Absensi();
            absensi.setWaktuAbsen(getInputValue("waktuAbsensi") + " " + getInputValue("jamAbsensi"));

            for(Mahasiswa mahasiswa : listMahasiswa){
                if(mahasiswa.getIdMahasiswa().equals(getInputValue("idMahasiswa").split(" - ")[1])){
                    absensi.setIdMahasiswa(mahasiswa.getIdMahasiswa());
                }
            }

            for(StatusAbsensi status : listStatus){
                if(status.getIdStatusAbsensi().equals(getInputValue("idStatus").split(" - ")[1])){
                    absensi.setStatusAbsensi(status.getIdStatusAbsensi());
                }
            }

            for(Jadwal jadwal : listJadwal){
                if(jadwal.getIdJadwal().equals(getInputValue("idJadwal").split(" - ")[1])){
                    absensi.setIdJadwal(jadwal.getIdJadwal());
                }
            }

            if(absensi.create()){
                border.setCenter(null);
                border.setCenter(alertBox("Data berhasil ditambahkan!"));
            }
            else{
                border.setCenter(null);
                border.setCenter(alertBox("Data gagal ditambahkan!"));
            }
        });
        
        Button closeDialog = new Button("Kembali");
        closeDialog.setMaxWidth(Double.MAX_VALUE);
        closeDialog.setOnAction(e -> {
            window.close();
        });
       
        grid.getStyleClass().addAll(new String[]{"bg-white", "rounded"});
        btnAcc.getStyleClass().addAll(new String[]{"btn", "btn-primary"});
        closeDialog.getStyleClass().addAll(new String[]{"btn", "btn-alternate"});
        formTitle.getStyleClass().addAll(new String[]{"title"});

        grid.add(formTitle, 0, 0);

        int x = 0;
        int y = 1;

        for(Input input : formInputList){
            VBox span = new VBox();
            span.setSpacing(5);

            if(input.getType().equals("TextField")){
                input.getTextField().setText(null);
                input.getTextField().setPromptText(input.getPlaceholder());
                input.getTextField().getStyleClass().addAll(new String[]{"input"});
                input.getTextField().setPadding(new Insets(10, 10, 10, 10));
                input.getTextField().setPrefWidth(border.getWidth() / 2);
                
                span.getChildren().addAll(input.getLabel(), input.getTextField());
            }
            else if(input.getType().equals("ComboBox")){
                input.getComboBox().getItems().clear();

                if(input.getName().equals("idKelas")){
                    input.getComboBox().setValue("Pilih kelas");
                    
                    formInputList[1].getComboBox().setDisable(true);
                    formInputList[2].getComboBox().setDisable(true);

                    input.getComboBox().setOnAction(e -> {
                        if(input.getComboBox().getValue() != "Pilih kelas"){
                            getListMahasiswa();
                            getListJadwal();

                            toggleInput();
                        }
                    });

                    for(Kelas kelas : listKelas){
                        input.getComboBox().getItems().add(kelas.getNmKelas() + " - " + kelas.getIdKelas());
                    }
                }
                else if(input.getName().equals("idStatus")){
                    for(StatusAbsensi status : listStatus){
                        input.getComboBox().getItems().add(status.getNmStatusAbsensi() + " - " + status.getIdStatusAbsensi());
                    }
                }

                input.getComboBox().getStyleClass().addAll(new String[]{"input"});
                input.getComboBox().setPadding(new Insets(10, 10, 10, 10));
                input.getComboBox().setPrefWidth(border.getWidth() / 2);

                span.getChildren().addAll(input.getLabel(), input.getComboBox());
            }
            else if(input.getType().equals("DatePicker")){
                input.getDatePicker().getStyleClass().addAll(new String[]{"input"});
                input.getDatePicker().setPadding(new Insets(10, 10, 10, 10));
                input.getDatePicker().setPrefWidth(border.getWidth() / 2);

                span.getChildren().addAll(input.getLabel(), input.getDatePicker());
            }
            else if(input.getType().equals("TimePicker")){
                span.getChildren().addAll(input.getLabel(), input.getTimePicker().getInput());
            }

            readyInput.add(input);

            if(x % 2 == 0 && x != 0){
                y++;
                x = 0;
            }

            grid.add(span, x, y);
            x++;
        }
            
        VBox actionButtonPane = new VBox();
        actionButtonPane.getChildren().addAll(btnAcc, closeDialog);
        actionButtonPane.setSpacing(10);

        grid.add(actionButtonPane, 0, y + 1, 2, 1);

        return grid;
    }

    public GridPane createFormEdit(){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER_LEFT);
        grid.setHgap(10);
        grid.setVgap(30);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text formTitle = new Text("Edit Mahasiswa");
        
        Button btnAcc = new Button("Perbarui");
        btnAcc.setMaxWidth(Double.MAX_VALUE);
        btnAcc.setOnAction(e -> {
            data.setIdMahasiswa(getInputValue("idMahasiswa"));
            data.setWaktuAbsen(getInputValue("waktuAbsensi") + " " + getInputValue("jamAbsensi"));

            for(Mahasiswa mahasiswa : listMahasiswa){
                if(mahasiswa.getIdMahasiswa().equals(getInputValue("idMahasiswa").split(" - ")[1])){
                    data.setIdMahasiswa(mahasiswa.getIdMahasiswa());
                }
            }

            for(StatusAbsensi status : listStatus){
                if(status.getIdStatusAbsensi().equals(getInputValue("idStatus").split(" - ")[1])){
                    data.setStatusAbsensi(status.getIdStatusAbsensi());
                }
            }

            for(Jadwal jadwal : listJadwal){
                if(jadwal.getIdJadwal().equals(getInputValue("idJadwal").split(" - ")[1])){
                    data.setIdJadwal(jadwal.getIdJadwal());
                }
            }

            if(data.update()){
                border.setCenter(null);
                border.setCenter(alertBox("Data berhasil diperbarui!"));
            }
            else{
                border.setCenter(null);
                border.setCenter(alertBox("Data gagal diperbarui!"));
            }
        });

        Button closeDialog = new Button("Kembali");
        closeDialog.setMaxWidth(Double.MAX_VALUE);
        closeDialog.setOnAction(e -> {
            window.close();
        });
       
        grid.getStyleClass().addAll(new String[]{"bg-white", "rounded"});
        btnAcc.getStyleClass().addAll(new String[]{"btn", "btn-primary"});
        closeDialog.getStyleClass().addAll(new String[]{"btn", "btn-alternate"});
        formTitle.getStyleClass().addAll(new String[]{"title"});

        grid.add(formTitle, 0, 0);

        int x = 0;
        int y = 1;

        for(Input input : formInputList){
            VBox span = new VBox();
            span.setSpacing(5);

            if(input.getType().equals("TextField")){
                input.getTextField().setText(data.getByID(input.getIndex()));
                input.getTextField().setPromptText(input.getPlaceholder());
                input.getTextField().getStyleClass().addAll(new String[]{"input"});
                input.getTextField().setPadding(new Insets(10, 10, 10, 10));
                input.getTextField().setPrefWidth(border.getWidth() / 2);

                span.getChildren().addAll(input.getLabel(), input.getTextField());
            }
            else if(input.getType().equals("ComboBox")){
                input.getComboBox().getItems().clear();

                if(input.getName().equals("idKelas")){
                    input.getComboBox().setOnAction(e -> {
                        getListMahasiswa();
                        getListJadwal();

                        toggleInput();
                    });

                    for(Kelas kelas : listKelas){
                        input.getComboBox().getItems().add(kelas.getNmKelas() + " - " + kelas.getIdKelas());
                        Kelas k = searchKelas(data.getIdMahasiswa());

                        if(kelas.getIdKelas().equals(k.getIdKelas())){
                            input.getComboBox().setValue(kelas.getNmKelas() + " - " + kelas.getIdKelas());
                        }
                    }
                }
                else if(input.getName().equals("idStatus")){
                    for(StatusAbsensi status : listStatus){
                        input.getComboBox().getItems().add(status.getNmStatusAbsensi() + " - " + status.getIdStatusAbsensi());

                        if(data.getStatusAbsensi().equals(status.getIdStatusAbsensi())){
                            input.getComboBox().setValue(status.getNmStatusAbsensi() + " - " + status.getIdStatusAbsensi());
                        }
                    }
                }
                else if(input.getName().equals("idMahasiswa")){
                    for(Mahasiswa mahasiswa : tempListMahasiswa){
                        if(data.getIdMahasiswa().equals(mahasiswa.getIdMahasiswa())){
                            input.getComboBox().setValue(mahasiswa.getNmMahasiswa() + " - " + mahasiswa.getIdMahasiswa());
                        }
                    }
                }
                else if(input.getName().equals("idJadwal")){
                    for(Jadwal jadwal : tempListJadwal){
                        if(data.getIdJadwal().equals(jadwal.getIdJadwal())){
                            input.getComboBox().setValue(jadwal.getNmMatkul() + " (" + tglFormatter(jadwal.getHari(), jadwal.getJamMulai(), jadwal.getJamSelesai()) + ") - " + jadwal.getIdJadwal());
                        }
                    }
                }

                input.getComboBox().setPromptText(input.getPlaceholder());
                input.getComboBox().getStyleClass().addAll(new String[]{"input"});
                input.getComboBox().setPadding(new Insets(10, 10, 10, 10));
                input.getComboBox().setPrefWidth(border.getWidth() / 2);

                span.getChildren().addAll(input.getLabel(), input.getComboBox());
            }
            else if(input.getType().equals("DatePicker")){
                input.getDatePicker().getStyleClass().addAll(new String[]{"input"});
                input.getDatePicker().setPadding(new Insets(10, 10, 10, 10));
                input.getDatePicker().setPrefWidth(border.getWidth() / 2);

                input.getDatePicker().setValue(LocalDate.parse(data.getByID(input.getIndex()).split(" ")[0]));

                span.getChildren().addAll(input.getLabel(), input.getDatePicker());
            }
            else if(input.getType().equals("TimePicker")){
                String[] time = data.getByID(input.getIndex()).split(" ")[1].split(":");

                input.getTimePicker().getHour().setNum(Integer.parseInt(time[0]));
                input.getTimePicker().getMinute().setNum(Integer.parseInt(time[1]));

                span.getChildren().addAll(input.getLabel(), input.getTimePicker().getInput());
            }
            
            readyInput.add(input);

            if(x % 2 == 0 && x != 0){
                y++;
                x = 0;
            }

            grid.add(span, x, y);
            x++;
        }

        VBox actionButtonPane = new VBox();
        actionButtonPane.getChildren().addAll(btnAcc, closeDialog);
        actionButtonPane.setSpacing(10);

        grid.add(actionButtonPane, 0, y + 1, 2, 1);

        getListMahasiswa();
        getListJadwal();

        return grid;
    }

    public VBox createFormDelete(){
        VBox grid = new VBox();
        grid.setAlignment(Pos.CENTER_LEFT);
        grid.setSpacing(30);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text formTitle = new Text("Hapus Absensi");
        Text formSubtitle = new Text("Apakah anda yakin ingin menghapus absensi ini ?");
        
        Button btnAcc = new Button("Hapus");
        btnAcc.setPrefWidth(Double.MAX_VALUE);
        btnAcc.setOnAction(e -> {
            if(data.delete()){
                border.setCenter(null);
                border.setCenter(alertBox("Data berhasil dihapus!"));
            }
            else{
                border.setCenter(null);
                border.setCenter(alertBox("Data gagal dihapus!"));
            }
        });
        
        Button closeDialog = new Button("Kembali");
        closeDialog.setPrefWidth(Double.MAX_VALUE);
        closeDialog.setOnAction(e -> {
            window.close();
        });
       
        grid.getStyleClass().addAll(new String[]{"bg-white", "rounded"});
        btnAcc.getStyleClass().addAll(new String[]{"btn", "btn-primary"});
        closeDialog.getStyleClass().addAll(new String[]{"btn", "btn-alternate"});
        formTitle.getStyleClass().addAll(new String[]{"title"});

        VBox actionButtonPane = new VBox();
        actionButtonPane.getChildren().addAll(btnAcc, closeDialog);
        actionButtonPane.setSpacing(10);

        grid.getChildren().addAll(formTitle, formSubtitle, actionButtonPane);

        return grid;
    }

    private String getInputValue(String name){
        for(Input input : readyInput){
            if(input.getName().equals(name)){
                if(input.getType().equals("TextField")){
                    return input.getTextField().getText();
                }
                else if(input.getType().equals("ComboBox")){
                    return input.getComboBox().getValue();
                }
                else if(input.getType().equals("TimePicker")){
                    return input.getTimePicker().getTime();
                }
                else if(input.getType().equals("DatePicker")){
                    return input.getDatePicker().getValue().toString();
                }
            }
        }

        return null;
    }

    private void getListJadwal(){
        readyInput.get(2).getComboBox().getItems().clear();
        listJadwal.clear();
        DB db = new DB();

        String idKelas = readyInput.get(0).getComboBox().getValue().split(" - ")[1];
    
        List<Object> rs = db.runQuery("SELECT * FROM tb_jadwal AS j INNER JOIN tb_matkul AS m ON j.id_matkul = m.id_matkul WHERE id_kelas = '" + idKelas + "' AND id_dosen = '" + idDosen + "'");
    
        for(int i = 0; i < rs.size(); i++){
            Jadwal jadwal = new Jadwal(rs.get(i));
            readyInput.get(2).getComboBox().getItems().add(jadwal.getNmMatkul() + " (" + tglFormatter(jadwal.getHari(), jadwal.getJamMulai(), jadwal.getJamSelesai()) + ") - " + jadwal.getIdJadwal());
    
            listJadwal.add(jadwal);
        }
    }

    private void getListStatus(){
        listStatus.clear();
        DB db = new DB();
    
        List<Object> rs = db.runQuery("SELECT * FROM tb_status_absensi");
    
        for(int i = 0; i < rs.size(); i++){
            StatusAbsensi status = new StatusAbsensi(rs.get(i));
    
            listStatus.add(status);
        }
    }

    private void getListMahasiswa(){
        readyInput.get(1).getComboBox().getItems().clear();
        listMahasiswa.clear();

        DB db = new DB();

        String idKelas = readyInput.get(0).getComboBox().getValue().split(" - ")[1];
    
        List<Object> rs = db.runQuery("SELECT * FROM tb_mahasiswa WHERE id_kelas = '" + idKelas + "'");
    
        for(int i = 0; i < rs.size(); i++){
            Mahasiswa mahasiswa = new Mahasiswa(rs.get(i));
            readyInput.get(1).getComboBox().getItems().add(mahasiswa.getNmMahasiswa() + " - " + mahasiswa.getIdMahasiswa());
    
            listMahasiswa.add(mahasiswa);
        }
    }

    private void getListKelas(){
        listKelas.clear();
        DB db = new DB();

        List<Object> rs = db.runQuery("SELECT * FROM tb_kelas");

        for(int i = 0; i < rs.size(); i++){
            Kelas kelas = new Kelas(rs.get(i));

            listKelas.add(kelas);
        }
    }

    private void toggleInput(){
        if(listMahasiswa.size() >= 1){
            readyInput.get(1).getComboBox().setDisable(false);
            readyInput.get(1).getComboBox().setValue("Pilih mahasiswa");
        }
        else{
            readyInput.get(1).getComboBox().setDisable(true);
            readyInput.get(1).getComboBox().setValue("Tidak ada mahasiswa");
        }

        if(listJadwal.size() >= 1){
            readyInput.get(2).getComboBox().setDisable(false);
            readyInput.get(2).getComboBox().setValue("Pilih jadwal");
        }
        else{
            readyInput.get(2).getComboBox().setDisable(true);
            readyInput.get(2).getComboBox().setValue("Tidak ada jadwal");
        }
    }

    private String tglFormatter(String hari, String wktM, String wktS){
        String[] listHari = new String[]{"Minggu", "Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu"};

        String[] wktMS = wktM.split(":");
        String[] wktSS = wktS.split(":");

        String wktMulai = wktMS[0] + ":" + wktMS[1];
        String wktSelesai = wktSS[0] + ":" + wktSS[1];

        return listHari[Integer.parseInt(hari)] + ", " + wktMulai + " | " + wktSelesai;
    }

    private Kelas searchKelas(String nim){
        DB db = new DB();

        List<Object> rs_m = db.runQuery("SELECT * FROM tb_mahasiswa WHERE id_mhswa = '" + nim + "'");
        Mahasiswa m = new Mahasiswa(rs_m.get(0));

        List<Object> rs_k = db.runQuery("SELECT * FROM tb_kelas WHERE id_kelas = '" + m.getIdKelas() + "'");
        Kelas k = new Kelas(rs_k.get(0));

        return k;
    }

    private void fetchTempData(){
        DB db = new DB();

        List<Object> rs_m = db.runQuery("SELECT * FROM tb_mahasiswa");
        for(Object x : rs_m){
            Mahasiswa m = new Mahasiswa(x);
            tempListMahasiswa.add(m);
        }

        List<Object> rs_j = db.runQuery("SELECT * FROM tb_jadwal AS j INNER JOIN tb_matkul AS m ON j.id_matkul = m.id_matkul");
        for(Object x : rs_j){
            Jadwal j = new Jadwal(x);
            tempListJadwal.add(j);
        }
    }
}