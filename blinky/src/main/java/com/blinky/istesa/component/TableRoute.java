package com.blinky.istesa.component;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class TableRoute {
    public String routeName;
    public BorderPane table;
    public Button button;

    public TableRoute(String rn, BorderPane tb, Button btn) {
        routeName = rn;
        table = tb;
        button = btn;
    }
}
