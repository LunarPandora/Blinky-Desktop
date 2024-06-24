package com.blinky.istesa;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

// import java.io.IOException;

import com.blinky.istesa.Components.Account;
import com.blinky.istesa.Components.Login;

/**
 * JavaFX App
 */
@SuppressWarnings("exports")
public class App extends Application {
    private static Stage window;

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        window.setTitle("Blinky");

        // scene = new Scene(loadFXML("primary"), 640, 480);
        // stage.setScene(scene);
        // stage.show();
        
        BorderPane border = new BorderPane();
        border.setCenter(addGridPane());

        border.getStyleClass().addAll(new String[]{"bg-primary"});

        Scene scene = new Scene(border, 1280, 720);
        stage.setScene(scene);
        // stage.setMaximized(true);
        stage.setFullScreen(true);

        scene.getStylesheets().add(App.class.getResource("css/font.css").toExternalForm());
        scene.getStylesheets().add(App.class.getResource("css/style.css").toExternalForm());

        stage.show();
        border.setPadding(new Insets(50, (stage.getWidth() / 2) - 250, 50, (stage.getWidth() / 2) - 250));
        // System.out.println();
    }

    public GridPane addGridPane(){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(30);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text formTitle = new Text("Login");
        Text formSubtitle = new Text("Masukkan akun anda untuk melanjutkan.");
        VBox titleList = new VBox();
        titleList.setSpacing(16);
        titleList.getChildren().addAll(formTitle, formSubtitle);

        Label lbl_uname = new Label("ID : ");
        TextField txt_uname = new TextField();
        txt_uname.setPadding(new Insets(10, 10, 10, 10));
        txt_uname.setPromptText("Masukkan ID anda");
        VBox uname = new VBox();
        uname.setSpacing(5);
        uname.getChildren().addAll(lbl_uname, txt_uname);

        Label lbl_pass = new Label("Password : ");
        PasswordField txt_pass = new PasswordField();
        txt_pass.setPadding(new Insets(10, 10, 10, 10));
        txt_pass.setPromptText("Masukkan password anda");
        VBox pass = new VBox();
        pass.setSpacing(5);
        pass.getChildren().addAll(lbl_pass, txt_pass);

        Button login = new Button("Masuk");
        login.setMaxWidth(Double.MAX_VALUE);

        grid.add(titleList, 0, 0);
        grid.add(uname, 0, 1);
        grid.add(pass, 0, 2);
        grid.add(login, 0, 3);

        grid.getStyleClass().addAll(new String[]{"bg-white", "rounded"});
        login.getStyleClass().addAll(new String[]{"btn", "btn-primary"});
        formTitle.getStyleClass().addAll(new String[]{"title"});
        formSubtitle.getStyleClass().addAll(new String[]{"subtitle"});
        txt_uname.getStyleClass().addAll(new String[]{"input"});
        txt_pass.getStyleClass().addAll(new String[]{"input"});

        titleList.setAlignment(Pos.CENTER);
        GridPane.setHalignment(formSubtitle, HPos.CENTER);
        GridPane.setHalignment(login, HPos.CENTER);

        login.setOnAction(e -> {
            Login auth = new Login(txt_uname.getText(), txt_pass.getText());
            Account acc = auth.authenticate();

            if(acc.getUserType().equals("Denied")){
                Alert a = new Alert(AlertType.ERROR);
                a.setContentText("Maaf, ID atau password anda salah. Harap cek kembali input anda.");
            }
            else{
                Home home = new Home(acc);
                window.getScene().setRoot(home.getRootPane());
            }
        });

        return grid;
    }

    // =========================================================================

    public static void main(String[] args) {
        System.setProperty("prism.lcdtext", "false");

        launch();
    }

    public static Stage getStage(){
        return window;
    }
}