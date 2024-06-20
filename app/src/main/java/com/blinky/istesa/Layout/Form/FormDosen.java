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
import com.blinky.istesa.Model.Jabatan;
import com.blinky.istesa.Model.Dosen;
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

public class FormDosen{
    private static Stage window;
    private static Home home;
    private static BorderPane border = new BorderPane();
    private static Scene scene = new Scene(border, 1280, 720);
    private static Dosen data;
    
    private static List<Prodi> listProdi = new ArrayList<Prodi>();
    private static List<Jabatan> listJabatan = new ArrayList<Jabatan>();

    private static Input[] formInputList = new Input[]{
        new Input("nmDosen", new Label("Nama Dosen"), new TextField(), 2, "Masukkan nama dosen").setType("TextField"),
        new Input("nidn", new Label("NIDN Dosen"), new TextField(), 3, "Masukkan NIDN Dosen").setType("TextField"),
        new Input("idProdi", new Label("Prodi"), new ComboBox<String>(), 1, "Pilih Prodi").setType("ComboBox"),
        new Input("idJabatan", new Label("Jabatan"), new ComboBox<String>(), 4, "Pilih Jabatan").setType("ComboBox"),
        new Input("uDosen", new Label("Username Dosen"), new TextField(), 5, "Masukkan username dosen").setType("TextField"),
        new Input("pwDosen", new Label("Password Dosen"), new PasswordField(), 6, "Masukkan password dosen").setType("TextField"),
    
    };

    private static List<Input> readyInput = new ArrayList<Input>();

    public FormDosen(Stage rStage, Home h, String type){
        home = h;

        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.initOwner(rStage);
        window.initStyle(StageStyle.TRANSPARENT);
        window.setMaximized(true);

        getListProdi();
        getListJabatan();

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

    public FormDosen(Stage rStage, Home h, String type, Dosen obj){
        home = h;
        data = obj;

        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.initOwner(rStage);
        window.initStyle(StageStyle.TRANSPARENT);
        window.setMaximized(true);

        getListProdi();
        getListJabatan();

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

        Text formTitle = new Text("Tambah Dosen");
        
        Button btnAcc = new Button("Tambahkan");
        btnAcc.setMaxWidth(Double.MAX_VALUE);
        btnAcc.setOnAction(e -> {
            Dosen dosen = new Dosen();
            dosen.setNmDosen(getInputValue("nmDosen"));
            dosen.setUDosen(getInputValue("uDosen"));
            dosen.setPwDosen(getInputValue("pwDosen"));
            dosen.setNidn(getInputValue("nidn"));

            for(Prodi prodi : listProdi){
                if(prodi.getNmProdi().equals(getInputValue("idProdi"))){
                    dosen.setIdProdi(prodi.getIdProdi());
                }
            }

            for(Jabatan jabatan : listJabatan){
                if(jabatan.getNmJabatan().equals(getInputValue("idJabatan"))){
                    dosen.setIdJabatan(jabatan.getIdJabatan());
                }
            }

            if(dosen.create()){
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
                input.getTextField().setPrefWidth(border.getWidth() / 2);
    
                span.getChildren().addAll(input.getLabel(), input.getTextField());
            }
            else if(input.getType().equals("ComboBox")){
                input.getComboBox().getItems().clear();

                if(input.getName().equals("idProdi")){
                    for(Prodi prodi : listProdi){
                        input.getComboBox().getItems().add(prodi.getNmProdi());
                    }
                }
                else if(input.getName().equals("idJabatan")){
                    for(Jabatan jabatan : listJabatan){
                        input.getComboBox().getItems().add(jabatan.getNmJabatan());
                    }
                }

                input.getComboBox().setPromptText(input.getPlaceholder());
                input.getComboBox().getStyleClass().addAll(new String[]{"input"});
                input.getComboBox().setPadding(new Insets(10, 10, 10, 10));
                input.getComboBox().setPrefWidth(border.getWidth() / 2);

                span.getChildren().addAll(input.getLabel(), input.getComboBox());
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
        // grid.setSpacing(30);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text formTitle = new Text("Edit Dosen");
        
        Button btnAcc = new Button("Perbarui");
        btnAcc.setMaxWidth(Double.MAX_VALUE);
        btnAcc.setOnAction(e -> {
            for(Prodi prodi : listProdi){
                if(prodi.getNmProdi().equals(getInputValue("idProdi"))){
                    data.setIdProdi(prodi.getIdProdi());
                }
            }

            for(Jabatan jabatan : listJabatan){
                if(jabatan.getNmJabatan().equals(getInputValue("idJabatan"))){
                    data.setIdJabatan(jabatan.getIdJabatan());
                }
            }

            data.setNmDosen(getInputValue("nmDosen"));
            data.setUDosen(getInputValue("uDosen"));
            data.setPwDosen(getInputValue("pwDosen"));
            data.setNidn(getInputValue("nidn"));

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

            // TextField txt = ;
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

                if(input.getName().equals("idProdi")){
                    for(Prodi prodi : listProdi){
                        input.getComboBox().getItems().add(prodi.getNmProdi());

                        if(data.getIdProdi().equals(prodi.getIdProdi())){
                            input.getComboBox().setValue(prodi.getNmProdi());
                        }
                    }
                }
                else if(input.getName().equals("idJabatan")){
                    for(Jabatan jabatan : listJabatan){
                        input.getComboBox().getItems().add(jabatan.getNmJabatan());

                        if(data.getIdJabatan().equals(jabatan.getIdJabatan())){
                            input.getComboBox().setValue(jabatan.getNmJabatan());
                        }
                    }
                }

                input.getComboBox().setPromptText(input.getPlaceholder());
                input.getComboBox().getStyleClass().addAll(new String[]{"input"});
                input.getComboBox().setPadding(new Insets(10, 10, 10, 10));
                input.getComboBox().setPrefWidth(border.getWidth() / 2);

                span.getChildren().addAll(input.getLabel(), input.getComboBox());
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

        Text formTitle = new Text("Hapus Dosen");
        Text formSubtitle = new Text("Apakah anda yakin ingin menghapus dosen ini ?");
        
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

    private void getListJabatan(){
        listJabatan.clear();
        DB db = new DB();

        List<Object> rs = db.runQuery("SELECT * FROM tb_jabatan");

        for(int i = 0; i < rs.size(); i++){
            // System.out.println(rs.get(i));
            Jabatan jabatan = new Jabatan(rs.get(i));

            listJabatan.add(jabatan);
        }
    }
}
