module com.blinky.istesa {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.blinky.istesa to javafx.fxml;
    exports com.blinky.istesa;
}
