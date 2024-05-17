package com.blinky.istesa.Layout.Form;

import java.util.ArrayList;
import java.util.List;

import com.blinky.istesa.App;
import com.blinky.istesa.DB;
import com.blinky.istesa.Home;
import com.blinky.istesa.Layout.Input.Input;
import com.blinky.istesa.Layout.Input.NumPicker;
import com.blinky.istesa.Layout.Input.TimePicker;
import com.blinky.istesa.Model.Dosen;
import com.blinky.istesa.Model.Mahasiswa;
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
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FormMahasiswa{
    private static Stage window;
    private static Home home;
    private static BorderPane border = new BorderPane();
    private static Scene scene = new Scene(border, 1280, 720);
    private static Mahasiswa data;

    private static String nimLama;
    
    private static List<Prodi> listProdi = new ArrayList<Prodi>();
    private static List<Kelas> listKelas = new ArrayList<Kelas>();

    private static Input[] formInputList = new Input[]{
        new Input("idMahasiswa", new Label("NIM"), new TextField(), 0, "Masukkan NIM").setType("TextField"),
        new Input("nmMahasiswa", new Label("Nama Mahasiswa"), new TextField(), 3, "Masukkan nama mahasiswa").setType("TextField"),
        new Input("idKelas", new Label("Kelas"), new ComboBox<String>(), 1, "Pilih Kelas").setType("ComboBox"),
        new Input("idProdi", new Label("Prodi"), new ComboBox<String>(), 2, "Pilih prodi").setType("ComboBox"),
        new Input("angkatan", new Label("Angkatan"), new TextField(), 4, "Masukkan tahun angkatan").setType("TextField"),
        new Input("pwMahasiswa", new Label("Password Mahasiswa"), new TextField(), 9, "Masukkan password akun mahasiswa").setType("TextField"),
    };

    private static List<Input> readyInput = new ArrayList<Input>();

    public FormMahasiswa(Stage rStage, Home h, String type){
        home = h;

        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.initOwner(rStage);
        window.initStyle(StageStyle.TRANSPARENT);
        window.setMaximized(true);

        getListProdi();
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

    public FormMahasiswa(Stage rStage, Home h, String type, Mahasiswa obj){
        home = h;
        data = obj;

        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.initOwner(rStage);
        window.initStyle(StageStyle.TRANSPARENT);
        window.setMaximized(true);

        getListProdi();
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
                (window.getHeight() / 2) - (type.equals("edit") ? 275 : 150), 
                (window.getWidth() / 2) - 350, 
                (window.getHeight() / 2) - (type.equals("edit") ? 275 : 150), 
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
        // alertContent.

        grid.getChildren().addAll(formTitle, alertContent, closeDialog);

        return grid;
    }

    public GridPane createFormAdd(){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER_LEFT);
        grid.setHgap(10);
        grid.setVgap(30);
        grid.setPrefWidth(Double.MAX_VALUE);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text formTitle = new Text("Tambah Mahasiswa");
        
        Button btnAcc = new Button("Tambahkan");
        btnAcc.setPrefWidth(Double.MAX_VALUE);
        btnAcc.setOnAction(e -> {
            Mahasiswa mahasiswa = new Mahasiswa();
            mahasiswa.setIdMahasiswa(getInputValue("idMahasiswa"));
            mahasiswa.setNmMahasiswa(getInputValue("nmMahasiswa"));
            mahasiswa.setPwMahasiswa(getInputValue("pwMahasiswa"));
            mahasiswa.setAngkatan(getInputValue("angkatan"));
            mahasiswa.setIdAdmin(home.userAdmin.getIdAdmin());

            for(Prodi prodi : listProdi){
                if(prodi.getNmProdi().equals(getInputValue("idProdi"))){
                    mahasiswa.setIdProdi(prodi.getIdProdi());
                }
            }

            for(Kelas kelas : listKelas){
                if(kelas.getNmKelas().equals(getInputValue("idKelas"))){
                    mahasiswa.setIdKelas(kelas.getIdKelas());
                }
            }

            if(mahasiswa.create()){
                border.setCenter(null);
                border.setCenter(alertBox("Data berhasil ditambahkan!"));
            }
            else{
                border.setCenter(null);
                border.setCenter(alertBox("Data gagal ditambahkan!"));
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

        grid.add(formTitle, 0, 0);

        int x = 0;
        int y = 1;

        for(Input input : formInputList){
            VBox span = new VBox();
            span.setSpacing(5);
            span.setPrefWidth(grid.getWidth() / 2);

            // TextField txt = ;
            if(input.getType().equals("TextField")){
                input.getTextField().setText(null);
                input.getTextField().setPromptText(input.getPlaceholder());
                input.getTextField().getStyleClass().addAll(new String[]{"input"});
                input.getTextField().setPadding(new Insets(10, 10, 10, 10));
                input.getTextField().setPrefWidth(Double.MAX_VALUE);
                
                span.getChildren().addAll(input.getLabel(), input.getTextField());
            }
            else if(input.getType().equals("ComboBox")){
                input.getComboBox().getItems().clear();

                if(input.getName().equals("idProdi")){
                    for(Prodi prodi : listProdi){
                        input.getComboBox().getItems().add(prodi.getNmProdi());
                    }
                }
                else if(input.getName().equals("idKelas")){
                    for(Kelas kelas : listKelas){
                        input.getComboBox().getItems().add(kelas.getNmKelas());
                    }
                }

                input.getComboBox().setPromptText(input.getPlaceholder());
                input.getComboBox().getStyleClass().addAll(new String[]{"input"});
                input.getComboBox().setPadding(new Insets(10, 10, 10, 10));
                input.getComboBox().setPrefWidth(Double.MAX_VALUE);

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

    public GridPane createFormEdit(){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER_LEFT);
        grid.setHgap(10);
        grid.setVgap(30);
        grid.setPrefWidth(Double.MAX_VALUE);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text formTitle = new Text("Edit Mahasiswa");
        
        Button btnAcc = new Button("Perbarui");
        btnAcc.setPrefWidth(Double.MAX_VALUE);
        btnAcc.setOnAction(e -> {
            data.setIdMahasiswa(getInputValue("idMahasiswa"));
            data.setNmMahasiswa(getInputValue("nmMahasiswa"));
            data.setPwMahasiswa(getInputValue("pwMahasiswa"));
            data.setAngkatan(getInputValue("angkatan"));
            data.setIdAdmin(home.userAdmin.getIdAdmin());

            for(Prodi prodi : listProdi){
                if(prodi.getNmProdi().equals(getInputValue("idProdi"))){
                    data.setIdProdi(prodi.getIdProdi());
                }
            }

            for(Kelas kelas : listKelas){
                if(kelas.getNmKelas().equals(getInputValue("idKelas"))){
                    data.setIdKelas(kelas.getIdKelas());
                }
            }

            if(data.update(nimLama)){
                border.setCenter(null);
                border.setCenter(alertBox("Data berhasil diperbarui!"));
            }
            else{
                border.setCenter(null);
                border.setCenter(alertBox("Data gagal diperbarui!"));
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

        grid.add(formTitle, 0, 0);

        int x = 0;
        int y = 1;

        for(Input input : formInputList){
            VBox span = new VBox();
            span.setSpacing(5);
            span.setPrefWidth(grid.getWidth() / 2);

            // TextField txt = ;
            if(input.getType().equals("TextField")){
                input.getTextField().setText(data.getByID(input.getIndex()));
                input.getTextField().setPromptText(input.getPlaceholder());
                input.getTextField().getStyleClass().addAll(new String[]{"input"});
                input.getTextField().setPadding(new Insets(10, 10, 10, 10));
                input.getTextField().setPrefWidth(Double.MAX_VALUE);

                if(input.getName().equals("idMahasiswa")){
                    nimLama = data.getByID(input.getIndex());
                    System.out.println(nimLama);
                }

                span.getChildren().addAll(input.getLabel(), input.getTextField());
            }
            else if(input.getType().equals("ComboBox")){
                input.getComboBox().getItems().clear();

                if(input.getName().equals("idProdi")){
                    for(Prodi prodi : listProdi){
                        input.getComboBox().getItems().add(prodi.getNmProdi());

                        if(data.getIdProdi().equals(prodi.getNmProdi())){
                            input.getComboBox().setValue(prodi.getNmProdi());
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

                input.getComboBox().setPromptText(input.getPlaceholder());
                input.getComboBox().getStyleClass().addAll(new String[]{"input"});
                input.getComboBox().setPadding(new Insets(10, 10, 10, 10));
                input.getComboBox().setPrefWidth(Double.MAX_VALUE);

                span.getChildren().addAll(input.getLabel(), input.getComboBox());
            }
            else if(input.getType().equals("TimePicker")){
                String[] time = data.getByID(input.getIndex()).split(":");

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

        return grid;
    }

    public VBox createFormDelete(){
        VBox grid = new VBox();
        grid.setAlignment(Pos.CENTER_LEFT);
        // grid.setHgap(10);
        // grid.setVgap(30);
        grid.setSpacing(30);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text formTitle = new Text("Hapus Mahasiswa");
        Text formSubtitle = new Text("Apakah anda yakin ingin menghapus mahasiswa ini ?");
        
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
            }
        }

        return null;
    }

    private void getListProdi(){
        listProdi.clear();
        DB db = new DB();

        List<Object> rs = db.runQuery("SELECT * FROM tb_prodi");

        for(int i = 0; i < rs.size(); i++){
            // System.out.println(rs.get(i));
            Prodi prodi = new Prodi(rs.get(i));

            listProdi.add(prodi);
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
