package com.blinky.istesa.Layout.Input;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Input {
    private final String name;
    private final Label label;

    private TextField textField;
    private ComboBox<String> comboBox;
    private TimePicker timePicker;

    private final int indexNumber;
    private final String placeholder;
    private String type;

    public Input(String name, Label label, TextField textField, int idx, String placeholder) {
        this.name = name;
        this.label = label;
        this.textField = textField;
        this.indexNumber = idx;
        this.placeholder = placeholder;
    }

    public Input(String name, Label label, ComboBox<String> cb , int idx, String placeholder) {
        this.name = name;
        this.label = label;
        this.comboBox = cb;
        this.indexNumber = idx;
        this.placeholder = placeholder;
    }

    public Input(String name, Label label, TimePicker tp , int idx, String placeholder) {
        this.name = name;
        this.label = label;
        this.timePicker = tp;
        this.indexNumber = idx;
        this.placeholder = placeholder;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public String getName() {
        return name;
    }

    public Label getLabel() {
        return label;
    }

    public TextField getTextField() {
        return textField;
    }

    public ComboBox<String> getComboBox() {
        return comboBox;
    }

    public TimePicker getTimePicker() {
        return timePicker;
    }

    public String getType(){
        return type;
    }

    public int getIndex() {
        return indexNumber;
    }

    public Input setType(String ty){
        type = ty;
        return this;
    }
}
