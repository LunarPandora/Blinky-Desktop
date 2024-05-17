package com.blinky.istesa.Components;

import javafx.scene.control.Button;

public class TableRoute {
    public String routeName;
    public Button button;

    public Object table;

    public TableRoute(String rn, Object tb, Button btn) {
        routeName = rn;
        table = tb;
        button = btn;
    }
}
