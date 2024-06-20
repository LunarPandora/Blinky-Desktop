package com.blinky.istesa.Layout.Form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.blinky.istesa.App;
import com.blinky.istesa.DB;
import com.blinky.istesa.Home;
import com.blinky.istesa.Layout.Input.Input;
import com.blinky.istesa.Layout.Input.NumPicker;
import com.blinky.istesa.Layout.Input.TimePicker;
import com.blinky.istesa.Model.Dosen;
import com.blinky.istesa.Model.Jadwal;
import com.blinky.istesa.Model.Kelas;
import com.blinky.istesa.Model.Matkul;
import com.blinky.istesa.Model.Prodi;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FormJadwal{
    private static Stage window;
    private static Home home;
    private static BorderPane border = new BorderPane();
    private static Scene scene = new Scene(border, 1280, 720);
    private static Jadwal data;
    
    private static List<Matkul> listMatkul = new ArrayList<Matkul>();
    private static List<Dosen> listDosen = new ArrayList<Dosen>();
    private static List<Kelas> listKelas = new ArrayList<Kelas>();

    private static Input[] formInputList = new Input[]{
        new Input("idMatkul", new Label("Mata Kuliah"), new ComboBox<String>(), 6, "Pilih mata kuliah").setType("ComboBox"),
        new Input("idDosen", new Label("Dosen Pengajar"), new ComboBox<String>(), 5, "Pilih Dosen pengajar").setType("ComboBox"),
        new Input("idKelas", new Label("Kelas"), new ComboBox<String>(), 4, "Pilih Kelas").setType("ComboBox"),
        new Input("hari", new Label("Hari"), new ComboBox<String>(), 3, "Pilih Hari").setType("ComboBox"),
        new Input("jamMulai", new Label("Jam Mulai"), new TimePicker(), 1, "Pilih jam mulai").setType("TimePicker"),
        new Input("jamSelesai", new Label("Jam Selesai"), new TimePicker(), 2, "Pilih selesai").setType("TimePicker"),
    };

    private static String[] listHari = new String[]{"Minggu", "Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu"};
    private static List<Input> readyInput = new ArrayList<Input>();

    public FormJadwal(Stage rStage, Home h, String type){
        home = h;

        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.initOwner(rStage);
        window.initStyle(StageStyle.TRANSPARENT);
        window.setMaximized(true);

        getListMatkul();
        getListDosen();
        getListKelas();

        border.setOpacity(1);
        if(type.equals("create")){
            border.setCenter(createFormAdd());
        }

        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add(App.class.getResource("css/form.css").toExternalForm());
        // scene.getStylesheets().add(App.class.getResource("css/font.css").toExternalForm());

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

    public FormJadwal(Stage rStage, Home h, String type, Jadwal obj){
        home = h;
        data = obj;

        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.initOwner(rStage);
        window.initStyle(StageStyle.TRANSPARENT);
        window.setMaximized(true);

        getListMatkul();
        getListDosen();
        getListKelas();

        border.setOpacity(1);
        if(type.equals("edit")){
            border.setCenter(createFormEdit());
        }
        else{
            border.setCenter(createFormDelete());
        }

        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add(App.class.getResource("css/form.css").toExternalForm());
        // scene.getStylesheets().add(App.class.getResource("css/font.css").toExternalForm());

        window.setScene(scene);
        window.show();

        border.setPadding(
            new Insets(
                (window.getHeight() / 2) - (type.equals("edit") ? 375 : 150), 
                (window.getWidth() / 2) - 350, 
                (window.getHeight() / 2) - (type.equals("edit") ? 375 : 150), 
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
        closeDialog.setMaxWidth(Double.MAX_VALUE);
        closeDialog.setOnAction(e -> {
            home.refreshTable();

            window.close();
        });

        grid.getStyleClass().addAll(new String[]{"bg-white", "rounded"});
        closeDialog.getStyleClass().addAll(new String[]{"btn", "btn-primary"});
        formTitle.getStyleClass().addAll(new String[]{"title"});
        // alertContent.

        grid.getChildren().addAll(formTitle, alertContent, closeDialog);

        return grid;
    }

    public GridPane createFormAdd(){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER_LEFT);
        grid.setHgap(10);
        grid.setVgap(30);
        // grid.setSpacing(30);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text formTitle = new Text("Tambah Jadwal");
        
        Button btnAcc = new Button("Tambahkan");
        btnAcc.setMaxWidth(Double.MAX_VALUE);
        btnAcc.setOnAction(e -> {
            Jadwal jadwal = new Jadwal();

            for(Matkul matkul : listMatkul){
                if(matkul.getNmMatkul().equals(getInputValue("idMatkul"))){
                    jadwal.setIdMatkul(matkul.getIdMatkul());
                }
            }

            for(Dosen dosen : listDosen){
                if(dosen.getNmDosen().equals(getInputValue("idDosen"))){
                    jadwal.setIdDosen(dosen.getIdDosen());
                }
            }

            for(Kelas kelas : listKelas){
                if(kelas.getNmKelas().equals(getInputValue("idKelas"))){
                    jadwal.setIdKelas(kelas.getIdKelas());
                }
            }

            jadwal.setJamMulai(getInputValue("jamMulai"));
            jadwal.setJamSelesai(getInputValue("jamSelesai"));
            jadwal.setHari(getInputValue("hari"));
            for(int x = 0; x < listHari.length; x++){
                if(x == readyInput.get(3).getComboBox().getSelectionModel().getSelectedIndex()){
                    jadwal.setHari(String.valueOf(x));
                }
            }

            if(jadwal.create()){
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

            // TextField txt = ;
            if(input.getType().equals("TextField")){
                input.getTextField().setText(null);
                input.getTextField().setPromptText(input.getPlaceholder());
                input.getTextField().getStyleClass().addAll(new String[]{"input"});
                input.getTextField().setPadding(new Insets(10, 10, 10, 10));
    
                span.getChildren().addAll(input.getLabel(), input.getTextField());
            }
            else if(input.getType().equals("ComboBox")){
                input.getComboBox().getItems().clear();

                if(input.getName().equals("idMatkul")){
                    for(Matkul matkul : listMatkul){
                        input.getComboBox().getItems().add(matkul.getNmMatkul());
                    }
                }
                else if(input.getName().equals("idDosen")){
                    for(Dosen dosen : listDosen){
                        input.getComboBox().getItems().add(dosen.getNmDosen());
                    }
                }
                else if(input.getName().equals("idKelas")){
                    for(Kelas kelas : listKelas){
                        input.getComboBox().getItems().add(kelas.getNmKelas());
                    }
                }
                else if(input.getName().equals("hari")){
                    for(String hari : listHari){
                        input.getComboBox().getItems().add(hari);
                    }
                }

                input.getComboBox().setPromptText(input.getPlaceholder());
                input.getComboBox().getStyleClass().addAll(new String[]{"input"});
                input.getComboBox().setPadding(new Insets(10, 10, 10, 10));
                input.getComboBox().setMaxWidth(Double.MAX_VALUE);

                span.getChildren().addAll(input.getLabel(), input.getComboBox());
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

    public VBox createFormEdit(){
        VBox grid = new VBox();
        grid.setAlignment(Pos.CENTER_LEFT);
        // grid.setHgap(10);
        // grid.setVgap(30);
        grid.setSpacing(30);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text formTitle = new Text("Edit Jadwal");
        
        Button btnAcc = new Button("Perbarui");
        btnAcc.setMaxWidth(Double.MAX_VALUE);
        btnAcc.setOnAction(e -> {
            for(Matkul matkul : listMatkul){
                if(matkul.getNmMatkul().equals(getInputValue("idMatkul"))){
                    data.setIdMatkul(matkul.getIdMatkul());
                }
            }

            for(Dosen dosen : listDosen){
                if(dosen.getNmDosen().equals(getInputValue("idDosen"))){
                    data.setIdDosen(dosen.getIdDosen());
                }
            }

            for(Kelas kelas : listKelas){
                if(kelas.getNmKelas().equals(getInputValue("idKelas"))){
                    data.setIdKelas(kelas.getIdKelas());
                }
            }

            for(String hari : listHari){
                if(hari.equals(getInputValue("hari"))){
                    data.setHari(hari);
                }
            }

            data.setJamMulai(getInputValue("jamMulai"));
            data.setJamSelesai(getInputValue("jamSelesai"));
            for(int x = 0; x < listHari.length; x++){
                if(x == readyInput.get(3).getComboBox().getSelectionModel().getSelectedIndex()){
                    data.setHari(String.valueOf(x));
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

        grid.getChildren().add(formTitle);
        for(Input input : formInputList){
            VBox span = new VBox();
            span.setSpacing(5);

            // TextField txt = ;
            if(input.getType().equals("TextField")){
                input.getTextField().setText(data.getByID(input.getIndex()));
                input.getTextField().setPromptText(input.getPlaceholder());
                input.getTextField().getStyleClass().addAll(new String[]{"input"});
                input.getTextField().setPadding(new Insets(10, 10, 10, 10));
    
                span.getChildren().addAll(input.getLabel(), input.getTextField());
            }
            else if(input.getType().equals("ComboBox")){
                input.getComboBox().getItems().clear();

                if(input.getName().equals("idMatkul")){
                    for(Matkul matkul : listMatkul){
                        input.getComboBox().getItems().add(matkul.getNmMatkul());

                        if(data.getIdMatkul().equals(matkul.getNmMatkul())){
                            input.getComboBox().setValue(matkul.getNmMatkul());
                        }
                    }
                }
                else if(input.getName().equals("idDosen")){
                    for(Dosen dosen : listDosen){
                        input.getComboBox().getItems().add(dosen.getNmDosen());

                        if(data.getIdDosen().equals(dosen.getNmDosen())){
                            input.getComboBox().setValue(dosen.getNmDosen());
                        }
                    }
                }
                else if(input.getName().equals("idKelas")){
                    for(Kelas kelas : listKelas){
                        input.getComboBox().getItems().add(kelas.getNmKelas());

                        if(data.getIdKelas().equals(kelas.getNmKelas())){
                            input.getComboBox().setValue(kelas.getNmKelas());
                        }
                    }
                }
                else if(input.getName().equals("hari")){
                    for(String hari : listHari){
                        input.getComboBox().getItems().add(hari);

                        if(data.getHari().equals(hari)){
                            input.getComboBox().setValue(hari);
                        }
                    }
                }

                input.getComboBox().setPromptText(input.getPlaceholder());
                input.getComboBox().getStyleClass().addAll(new String[]{"input"});
                input.getComboBox().setPadding(new Insets(10, 10, 10, 10));
                input.getComboBox().setMaxWidth(Double.MAX_VALUE);

                span.getChildren().addAll(input.getLabel(), input.getComboBox());
            }
            else if(input.getType().equals("TimePicker")){
                String[] time = data.getByID(input.getIndex()).split(":");

                input.getTimePicker().getHour().setNum(Integer.parseInt(time[0]));
                input.getTimePicker().getMinute().setNum(Integer.parseInt(time[1]));

                span.getChildren().addAll(input.getLabel(), input.getTimePicker().getInput());
            }
            
            readyInput.add(input);

            grid.getChildren().add(span);
        }

        VBox actionButtonPane = new VBox();
        actionButtonPane.getChildren().addAll(btnAcc, closeDialog);
        actionButtonPane.setSpacing(10);

        grid.getChildren().add(actionButtonPane);

        return grid;
    }

    public VBox createFormDelete(){
        VBox grid = new VBox();
        grid.setAlignment(Pos.CENTER_LEFT);
        // grid.setHgap(10);
        // grid.setVgap(30);
        grid.setSpacing(30);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text formTitle = new Text("Hapus Jadwal");
        Text formSubtitle = new Text("Apakah anda yakin ingin menghapus jadwal ini ?");
        
        Button btnAcc = new Button("Hapus");
        btnAcc.setMaxWidth(Double.MAX_VALUE);
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
        closeDialog.setMaxWidth(Double.MAX_VALUE);
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
            }
        }

        return null;
    }

    private void getListMatkul(){
        listMatkul.clear();
        DB db = new DB();

        List<Object> rs = db.runQuery("SELECT * FROM tb_matkul");

        for(int i = 0; i < rs.size(); i++){
            // System.out.println(rs.get(i));
            Matkul matkul = new Matkul(rs.get(i));

            listMatkul.add(matkul);
        }
    }

    private void getListDosen(){
        listDosen.clear();
        DB db = new DB();

        List<Object> rs = db.runQuery("SELECT * FROM tb_dosen");

        for(int i = 0; i < rs.size(); i++){
            // System.out.println(rs.get(i));
            Dosen dosen = new Dosen(rs.get(i));

            listDosen.add(dosen);
        }
    }

    private void getListKelas(){
        listKelas.clear();
        DB db = new DB();

        List<Object> rs = db.runQuery("SELECT * FROM tb_kelas");

        for(int i = 0; i < rs.size(); i++){
            // System.out.println(rs.get(i));
            Kelas kelas = new Kelas(rs.get(i));

            listKelas.add(kelas);
        }
    }
}
