package com.blinky.istesa.TableLayout;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

interface Table{
    public final BorderPane rootPane = new BorderPane();

    public HBox filterPane();
}
