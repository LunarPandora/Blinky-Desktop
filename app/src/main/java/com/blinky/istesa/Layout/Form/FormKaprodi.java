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
import com.blinky.istesa.Model.Kaprodi;
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

public class FormKaprodi{
    private static Stage window;
    private static Home home;
    private static BorderPane border = new BorderPane();
    private static Scene scene = new Scene(border, 1280, 720);
    private static Kaprodi data;
    private static List<Prodi> listProdi = new ArrayList<Prodi>();

    private static Input[] formInputList = new Input[]{
        new Input("nmKaprodi", new Label("Nama Kaprodi"), new TextField(), 1, "Masukkan nama kaprodi").setType("TextField"),
        new Input("uKaprodi", new Label("Username Kaprodi"), new TextField(), 2, "Masukkan username kaprodi").setType("TextField"),
        new Input("pwKaprodi", new Label("Password Kaprodi"), new PasswordField(), 3, "Masukkan password kaprodi").setType("TextField"),
        new Input("idProdi", new Label("Prodi"), new ComboBox<String>(), 5, "Pilih prodi").setType("ComboBox"),
        new Input("nidn", new Label("NIDN Kaprodi"), new TextField(), 6, "Masukkan NIDN Kaprodi").setType("TextField"),
    };

    private static List<Input> readyInput = new ArrayList<Input>();

    public FormKaprodi(Stage rStage, Home h, String type){
        home = h;

        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.initOwner(rStage);
        window.initStyle(StageStyle.TRANSPARENT);
        window.setMaximized(true);

        getListProdi();

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
                (window.getHeight() / 2) - 375, 
                (window.getWidth() / 2) - 250, 
                (window.getHeight() / 2) - 375, 
                (window.getWidth() / 2) - 250
            )
        );
    }

    public FormKaprodi(Stage rStage, Home h, String type, Kaprodi obj){
        home = h;
        data = obj;

        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.initOwner(rStage);
        window.initStyle(StageStyle.TRANSPARENT);
        window.setMaximized(true);

        getListProdi();

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
                (window.getWidth() / 2) - 250, 
                (window.getHeight() / 2) - (type.equals("edit") ? 375 : 150), 
                (window.getWidth() / 2) - 250
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

    public VBox createFormAdd(){
        VBox grid = new VBox();
        grid.setAlignment(Pos.CENTER_LEFT);
        // grid.setHgap(10);
        // grid.setVgap(30);
        grid.setSpacing(30);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text formTitle = new Text("Tambah Kaprodi");
        
        Button btnAcc = new Button("Tambahkan");
        btnAcc.setMaxWidth(Double.MAX_VALUE);
        btnAcc.setOnAction(e -> {
            Kaprodi kaprodi = new Kaprodi();
            kaprodi.setNmKaprodi(getInputValue("nmKaprodi"));
            kaprodi.setUKaprodi(getInputValue("uKaprodi"));
            kaprodi.setPwKaprodi(getInputValue("pwKaprodi"));
            kaprodi.setNidn(getInputValue("nidn"));

            for(Prodi prodi : listProdi){
                if(prodi.getNmProdi().equals(getInputValue("idProdi"))){
                    kaprodi.setIdProdi(prodi.getIdProdi());
                }
            }

            if(kaprodi.create()){
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

        grid.getChildren().add(formTitle);
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
                if(input.getName().equals("idProdi")){
                    input.getComboBox().getItems().clear();
                    for(Prodi prodi : listProdi){
                        input.getComboBox().getItems().add(prodi.getNmProdi());
                    }

                    input.getComboBox().setPromptText(input.getPlaceholder());
                    input.getComboBox().getStyleClass().addAll(new String[]{"input"});
                    input.getComboBox().setPadding(new Insets(10, 10, 10, 10));
                    input.getComboBox().setMaxWidth(Double.MAX_VALUE);
                }

                span.getChildren().addAll(input.getLabel(), input.getComboBox());
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

    public VBox createFormEdit(){
        VBox grid = new VBox();
        grid.setAlignment(Pos.CENTER_LEFT);
        // grid.setHgap(10);
        // grid.setVgap(30);
        grid.setSpacing(30);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text formTitle = new Text("Edit Kaprodi");
        
        Button btnAcc = new Button("Perbarui");
        btnAcc.setMaxWidth(Double.MAX_VALUE);
        btnAcc.setOnAction(e -> {
            data.setNmKaprodi(getInputValue("nmKaprodi"));
            data.setUKaprodi(getInputValue("uKaprodi"));
            data.setPwKaprodi(getInputValue("pwKaprodi"));
            data.setNidn(getInputValue("nidn"));

            for(Prodi prodi : listProdi){                
                if(prodi.getNmProdi().equals(getInputValue("idProdi"))){
                    data.setIdProdi(prodi.getIdProdi());
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
                if(input.getName().equals("idProdi")){
                    input.getComboBox().getItems().clear();
                    for(Prodi prodi : listProdi){
                        input.getComboBox().getItems().add(prodi.getNmProdi());

                        if(data.getIdProdi().equals(prodi.getNmProdi())){
                            input.getComboBox().setValue(prodi.getNmProdi());
                        }
                    }

                    input.getComboBox().setPromptText(input.getPlaceholder());
                    input.getComboBox().getStyleClass().addAll(new String[]{"input"});
                    input.getComboBox().setPadding(new Insets(10, 10, 10, 10));
                    input.getComboBox().setMaxWidth(Double.MAX_VALUE);
                }

                span.getChildren().addAll(input.getLabel(), input.getComboBox());
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

        Text formTitle = new Text("Hapus Kaprodi");
        Text formSubtitle = new Text("Apakah anda yakin ingin menghapus kaprodi '" + data.getNmKaprodi() + "' ?");
        
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
}
