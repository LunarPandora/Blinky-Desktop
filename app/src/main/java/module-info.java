module com.blinky.istesa {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires com.fazecast.jSerialComm;
    requires jbcrypt;

    opens com.blinky.istesa to javafx.fxml;
    exports com.blinky.istesa;
}
