package com.blinky.istesa.Layout.Input;

import javafx.scene.layout.HBox;

public class TimePicker {
    private NumPicker hour;
    private NumPicker minute;
    private HBox inputPane;

    public TimePicker(){
        hour = new NumPicker(0, 23, 10, "h", "Jam");
        minute = new NumPicker(0, 55, 30, "m", "Menit");

        inputPane = new HBox();
        inputPane.getChildren().addAll(hour.getPane(), minute.getPane());
    }

    public HBox getInput(){
        return inputPane;
    }

    public NumPicker getHour(){
        return hour;
    }

    public NumPicker getMinute(){
        return minute;
    }

    public String getTime(){
        String jamString;

        jamString = hour.valueProperty().getValue().toString() + ":" + minute.valueProperty().getValue().toString() + ":00";

        return jamString;
    }
}
