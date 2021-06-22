package at.htlvillach.gui;

import at.htlvillach.bll.Gender;
import at.htlvillach.dal.dao.CharacterDBDao;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;

import at.htlvillach.bll.Character;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.ResourceBundle;

public class EditCharacterController implements Initializable {
    @FXML
    private Label lbHeading;
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
    @FXML
    private Button btnSaveAndClose;
    @FXML
    private Label lbGender;
    @FXML
    private ChoiceBox cbGender;

    @FXML
    private SVGPath tshirt1;
    @FXML
    private SVGPath tshirt2;
    @FXML
    private SVGPath tshirt3;
    @FXML
    private SVGPath tshirt4;
    @FXML
    private SVGPath trousersleft;
    @FXML
    private SVGPath trousersright;
    @FXML
    private SVGPath hair1;
    @FXML
    private SVGPath hair2;
    @FXML
    private SVGPath skin1;
    @FXML
    private SVGPath skin2;
    @FXML
    private SVGPath skin3;
    @FXML
    private SVGPath skin4;

    private CharacterDBDao dao = new CharacterDBDao();
    Character editedCharacter;
    Character originalCharacter;

    public void setCharacter(Character character){
        this.originalCharacter = character;
        this.editedCharacter = new Character(character.getName(), character.getAge(), character.getGender().toString(), character.getHairColor(), character.getSkinColor(), character.getShirtColor(), character.getTrouserColor());

        tfName.setText(character.getName());
        slAge.setValue(character.getAge());
        lbAge.setText("Age: " + character.getAge());
        cpHairColor.setValue(Color.web(character.getHairColor()));
        cpSkinColor.setValue(Color.web(character.getSkinColor()));
        cpShirtColor.setValue(Color.web(character.getShirtColor()));
        cpTrouserColor.setValue(Color.web(character.getTrouserColor()));

        ObservableList genderList = FXCollections.observableList(Arrays.asList(Gender.values().clone()));
        cbGender.setItems(genderList);
        cbGender.getSelectionModel().select(character.getGender());

        setHair(Color.web(character.getHairColor()));
        setSkin(Color.web(character.getSkinColor()));
        setTshirt(Color.web(character.getShirtColor()));
        setTrouser(Color.web(character.getTrouserColor()));
    }

    private void setHair(Color web) {
        hair1.setFill(web);
        hair2.setFill(web);
    }

    private void setSkin(Color web) {
        skin1.setFill(web);
        skin2.setFill(web);
        skin3.setFill(web);
        skin4.setFill(web);
    }

    private void setTshirt(Color web){
        tshirt1.setFill(web);
        tshirt2.setFill(web);
        tshirt3.setFill(web);
        tshirt4.setFill(web);
    }

    private void setTrouser(Color web){
        trousersleft.setFill(web);
        trousersright.setFill(web);
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
            editedCharacter.setName(tfName.getText());
        });
        configureColorPickers();
        configureAgeSlider();
        cbGender.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldVal, Object newVal) {
                editedCharacter.setGender((Gender) newVal);
            }
        });
    }

    private void configureColorPickers() {
        cpHairColor.setOnAction(new EventHandler() {
            public void handle(Event t) {
                Color color = cpHairColor.getValue();
                String hex = toHexString(color);
                editedCharacter.setHairColor(hex);
                setHair(Color.web(hex));
            }
        });
        cpSkinColor.setOnAction(new EventHandler() {
            public void handle(Event t) {
                Color color = cpSkinColor.getValue();
                editedCharacter.setSkinColor(toHexString(color));
                setSkin(Color.web(toHexString(color)));
            }
        });
        cpShirtColor.setOnAction(new EventHandler() {
            public void handle(Event t) {
                Color color = cpShirtColor.getValue();
                editedCharacter.setShirtColor(toHexString(color));
                setTshirt(Color.web(toHexString(color)));
            }
        });
        cpTrouserColor.setOnAction(new EventHandler() {
            public void handle(Event t) {
                Color color = cpTrouserColor.getValue();
                editedCharacter.setTrouserColor(toHexString(color));
                setTrouser(Color.web(toHexString(color)));
            }
        });
    }

    private void configureAgeSlider(){
        slAge.setMin(0);
        slAge.setMax(123);
        slAge.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
                editedCharacter.setAge(newValue.intValue());
                lbAge.setText(String.format("Age: " + newValue.intValue()));
            }
        });
    }

    @FXML
    private void saveAndClose(ActionEvent actionEvent) {
        originalCharacter.setName(editedCharacter.getName());
        originalCharacter.setAge(editedCharacter.getAge());
        originalCharacter.setGender(editedCharacter.getGender());
        originalCharacter.setHairColor(editedCharacter.getHairColor());
        originalCharacter.setSkinColor(editedCharacter.getSkinColor());
        originalCharacter.setShirtColor(editedCharacter.getShirtColor());
        originalCharacter.setTrouserColor(editedCharacter.getTrouserColor());

        dao.update(originalCharacter);
        Stage stage = (Stage) btnSaveAndClose.getScene().getWindow();
        stage.close();
    }

    private String format(double val) {
        String in = Integer.toHexString((int) Math.round(val * 255));
        return in.length() == 1 ? "0" + in : in;
    }

    public String toHexString(Color value) {
        return "#" + (format(value.getRed()) + format(value.getGreen()) + format(value.getBlue())).toUpperCase();
    }
}
