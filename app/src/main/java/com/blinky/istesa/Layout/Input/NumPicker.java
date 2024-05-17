package com.blinky.istesa.Layout.Input;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

/**
 * A DateTimePicker with configurable datetime format where both date and time can be changed
 * via the text field and the date can additionally be changed via the JavaFX default date picker.
 */
@SuppressWarnings("unused")
public class NumPicker extends TextField {
    private IntegerProperty num;

    private int minValue;
    private int maxValue;

    private Button decValue;
    private Button incValue;

    private HBox numberPane;

    public int getNum() {
        return num.getValue();
    }

    public void setNum(int newValue) {
        num.setValue(newValue);

        if(getNum() < 10){
            this.setText("0" + this.valueProperty().getValue().toString());
        }
        else{
            this.setText(this.valueProperty().getValue().toString());
        }
    }

    public IntegerProperty valueProperty(){
        return num;
    }

    public void increaseValue(String type){
        int currentVal = getNum(); 
        int x = (type == "m" ? 5 : 1);

        if(this.maxValue > currentVal){
            setNum(currentVal + x);
        }
        else{
            setNum(minValue);
        }

        if(getNum() < 10){
            this.setText("0" + this.valueProperty().getValue().toString());
        }
        else{
            this.setText(this.valueProperty().getValue().toString());
        }
    }

    public void decreaseValue(String type){
        int currentVal = getNum();
        int x = (type == "m" ? 5 : 1);

        if(this.minValue < currentVal){
            setNum(currentVal - x);
        }
        else{
            setNum(maxValue);
        }

        if(getNum() < 10){
            this.setText("0" + this.valueProperty().getValue().toString());
        }
        else{
            this.setText(this.valueProperty().getValue().toString());
        }
    }

    public Boolean checkValue(){
        int currentVal = getNum();

        if(this.maxValue > currentVal && this.minValue < currentVal){
            return true;
        }
        else{
            return false;
        }
    }

    public NumPicker(int minValue, int maxValue, int initialValue, String type, String pl){
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.numberPane = new HBox();

        num = new SimpleIntegerProperty(initialValue);

        this.setText(this.valueProperty().getValue().toString());
        this.setEditable(false);
        this.setPromptText(pl);

        this.decValue = new Button("-");
        this.incValue = new Button("+");

        decValue.setOnAction(e -> decreaseValue(type));
        incValue.setOnAction(e -> increaseValue(type));

        // restrict key input to numerals.
        // this.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
        //     @Override public void handle(KeyEvent keyEvent) {
        //         if (!"0123456789".contains(keyEvent.getCharacter())) {
        //             keyEvent.consume();
        //         }
                
        //         if(!checkValue()){
        //             keyEvent.consume();
        //             setNum(0);
        //         }
        //     }
        // });

        numberPane.getChildren().addAll(decValue, this, incValue);
    }

    public HBox getPane(){
        return numberPane;
    }
}
