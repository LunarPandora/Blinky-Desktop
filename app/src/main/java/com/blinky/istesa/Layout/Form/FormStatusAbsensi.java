package com.blinky.istesa.Layout.Form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.blinky.istesa.App;
import com.blinky.istesa.Home;
import com.blinky.istesa.Layout.Input.Input;
import com.blinky.istesa.Model.StatusAbsensi;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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

public class FormStatusAbsensi{
    private static Stage window;
    private static Home home;
    private static BorderPane border = new BorderPane();
    private static Scene scene = new Scene(border, 1280, 720);
    private static StatusAbsensi data;

    private static Input[] formInputList = new Input[]{
        new Input("nmStatusAbsensi", new Label("Nama Status Absensi"), new TextField(), 1, "Masukkan nama status absensi").setType("TextField")
    };

    private static List<Input> readyInput = new ArrayList<Input>();

    public FormStatusAbsensi(Stage rStage, Home h, String type){
        home = h;

        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.initOwner(rStage);
        window.initStyle(StageStyle.TRANSPARENT);
        window.setMaximized(true);

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
                (window.getHeight() / 2) - 150, 
                (window.getWidth() / 2) - 250, 
                (window.getHeight() / 2) - 150, 
                (window.getWidth() / 2) - 250
            )
        );
    }

    public FormStatusAbsensi(Stage rStage, Home h, String type, StatusAbsensi obj){
        home = h;
        data = obj;

        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.initOwner(rStage);
        window.initStyle(StageStyle.TRANSPARENT);
        window.setMaximized(true);

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
                (window.getHeight() / 2) - 150, 
                (window.getWidth() / 2) - 250, 
                (window.getHeight() / 2) - 150, 
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

        Text formTitle = new Text("Tambah Status Absensi");
        
        Button btnAcc = new Button("Tambahkan");
        btnAcc.setMaxWidth(Double.MAX_VALUE);
        btnAcc.setOnAction(e -> {
            StatusAbsensi statusAbsensi = new StatusAbsensi();
            statusAbsensi.setNmStatusAbsensi(getInputValue("nmStatusAbsensi"));

            if(statusAbsensi.create()){
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
            input.getTextField().setText(null);
            input.getTextField().setPromptText(input.getPlaceholder());
            input.getTextField().getStyleClass().addAll(new String[]{"input"});
            input.getTextField().setPadding(new Insets(10, 10, 10, 10));

            span.getChildren().addAll(input.getLabel(), input.getTextField());
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

        Text formTitle = new Text("Edit Status Absensi");
        
        Button btnAcc = new Button("Perbarui");
        btnAcc.setMaxWidth(Double.MAX_VALUE);
        btnAcc.setOnAction(e -> {
            data.setNmStatusAbsensi(getInputValue("nmStatusAbsensi"));

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
            input.getTextField().setText(data.getByID(input.getIndex()));
            input.getTextField().setPromptText(input.getPlaceholder());
            input.getTextField().getStyleClass().addAll(new String[]{"input"});
            input.getTextField().setPadding(new Insets(10, 10, 10, 10));

            span.getChildren().addAll(input.getLabel(), input.getTextField());
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

        Text formTitle = new Text("Hapus Status Absensi");
        Text formSubtitle = new Text("Apakah anda yakin ingin menghapus mata kuliah '" + data.getNmStatusAbsensi() + "' ?");
        
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
                return input.getTextField().getText();
            }
        }

        return null;
    }
}
