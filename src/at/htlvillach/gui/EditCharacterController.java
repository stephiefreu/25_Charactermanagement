package at.htlvillach.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import at.htlvillach.bll.Character;

import java.net.URL;
import java.util.ResourceBundle;

public class EditCharacterController implements Initializable {
    @FXML
    private Label lbHeading;
    Character character;
    @FXML
    private ImageView imgCharacter;
    @FXML
    private Label lbName;
    @FXML
    private Label lbAge;
    @FXML
    private Label lbHairColor;
    @FXML
    private Label lbSkinColor;
    @FXML
    private Label lbTrouserColor;
    @FXML
    private Label lbShirtColor;
    @FXML
    private Slider slAge;
    @FXML
    private TextField tfName;
    @FXML
    private ColorPicker cpHairColor;
    @FXML
    private ColorPicker cpSkinColor;
    @FXML
    private ColorPicker cpTrouserColor;
    @FXML
    private ColorPicker cpShirtColor;

    public void setCharacter(Character character){
        this.character = character;
        tfName.setText(character.getName());
        slAge.setValue(character.getAge());
        lbAge.setText("Age: " + character.getAge());
        cpHairColor.setValue(Color.web(character.getHairColor()));
        cpSkinColor.setValue(Color.web(character.getSkinColor()));
        cpShirtColor.setValue(Color.web(character.getShirtColor()));
        cpTrouserColor.setValue(Color.web(character.getTrouserColor()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setFonts();
        configureChangeListeners();
    }

    private void setFonts(){
        Font luna = Font.loadFont("file:resources/fonts/Luna.ttf", 18);
        Font kiona = Font.loadFont("file:resources/fonts/Kiona.ttf", 14);
        lbHeading.setFont(luna);
        lbAge.setFont(kiona);
        lbName.setFont(kiona);
        lbHairColor.setFont(kiona);
        lbSkinColor.setFont(kiona);
        lbShirtColor.setFont(kiona);
        lbTrouserColor.setFont(kiona);
    }

    private void configureChangeListeners(){
        tfName.setOnAction(e -> {
            character.setName(tfName.getText());
        });
        configureColorPickers();
        configureAgeSlider();
    }

    private void configureColorPickers(){
        cpHairColor.setOnAction(new EventHandler() {
            public void handle(Event t) {
                Color color = cpHairColor.getValue();
                character.setHairColor(color.toString());
            }
        });
        cpSkinColor.setOnAction(new EventHandler() {
            public void handle(Event t) {
                Color color = cpSkinColor.getValue();
                character.setSkinColor(color.toString());
            }
        });
        cpShirtColor.setOnAction(new EventHandler() {
            public void handle(Event t) {
                Color color = cpShirtColor.getValue();
                character.setShirtColor(color.toString());
            }
        });
        cpTrouserColor.setOnAction(new EventHandler() {
            public void handle(Event t) {
                Color color = cpTrouserColor.getValue();
                character.setTrouserColor(color.toString());
            }
        });
    }

    private void configureAgeSlider(){
        slAge.setMin(0);
        slAge.setMax(123);
        slAge.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
                character.setAge(newValue.intValue());
                lbAge.setText(String.format("Age: " + newValue.intValue()));
            }
        });
    }
}
