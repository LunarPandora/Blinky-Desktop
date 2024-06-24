package com.blinky.istesa.Layout.Input;

import javafx.scene.layout.HBox;

public class TimePicker {
    private NumPicker hour;
    private NumPicker minute;
    private HBox inputPane;

    public TimePicker(){
        hour = new NumPicker(0, 23, 10, "h", "Jam", 5, 5);
        minute = new NumPicker(0, 55, 30, "m", "Menit", 1, 1);

        inputPane = new HBox();
        inputPane.getChildren().addAll(hour.getPane(), minute.getPane());
    }

    public TimePicker(int defIncrementVal, int defDecrementVal){
        hour = new NumPicker(0, 23, 10, "h", "Jam", defIncrementVal, defDecrementVal);
        minute = new NumPicker(0, 55, 30, "m", "Menit", defIncrementVal, defDecrementVal);

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
