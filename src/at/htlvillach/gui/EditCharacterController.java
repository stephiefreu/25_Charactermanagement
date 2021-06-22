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
import javafx.scene.layout.Pane;
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
    private SVGPath stshirt1;
    @FXML
    private SVGPath stshirt2;
    @FXML
    private SVGPath stshirt3;
    @FXML
    private SVGPath stshirt4;
    @FXML
    private SVGPath strousersleft;
    @FXML
    private SVGPath strousersright;
    @FXML
    private SVGPath shair1;
    @FXML
    private SVGPath shair2;
    @FXML
    private SVGPath sskin1;
    @FXML
    private SVGPath sskin2;
    @FXML
    private SVGPath sskin3;
    @FXML
    private SVGPath sskin4;

    private CharacterDBDao dao = new CharacterDBDao();
    Character editedCharacter;
    Character originalCharacter;
    @FXML
    private SVGPath mskin;
    @FXML
    private SVGPath mhair1;
    @FXML
    private SVGPath mshirt1;
    @FXML
    private SVGPath mtrouser1;
    @FXML
    private SVGPath mtrouser2;
    @FXML
    private SVGPath mshirt2;
    @FXML
    private SVGPath mskin2;
    @FXML
    private SVGPath mskin3;
    @FXML
    private SVGPath mhair2;
    @FXML
    private SVGPath mshirt3;
    @FXML
    private Pane steve;
    @FXML
    private Pane madga;

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

        showCharacter();

        ObservableList genderList = FXCollections.observableList(Arrays.asList(Gender.values().clone()));
        cbGender.setItems(genderList);
        cbGender.getSelectionModel().select(character.getGender());
    }

    private void showCharacter(){
        if(editedCharacter.getGender() == Gender.FEMALE) {
            steve.setVisible(false);
            madga.setVisible(true);
        }
        else{
            madga.setVisible(false);
            steve.setVisible(true);
        }

        setHair(Color.web(editedCharacter.getHairColor()));
        setSkin(Color.web(editedCharacter.getSkinColor()));
        setTshirt(Color.web(editedCharacter.getShirtColor()));
        setTrouser(Color.web(editedCharacter.getTrouserColor()));
    }

    private void setHair(Color web) {
        if(madga.isVisible()){
            mhair1.setFill(web);
            mhair2.setFill(web);
        } else {
            shair1.setFill(web);
            shair2.setFill(web);
        }
    }

    private void setSkin(Color web) {
        if(madga.isVisible()){
            mskin.setFill(web);
            mskin2.setFill(web);
            mskin3.setFill(web);
        } else {
            sskin1.setFill(web);
            sskin2.setFill(web);
            sskin3.setFill(web);
            sskin4.setFill(web);
        }
    }

    private void setTshirt(Color web){
        if(madga.isVisible()){
            mshirt1.setFill(web);
            mshirt2.setFill(web);
            mshirt3.setFill(web);
        } else {
            stshirt1.setFill(web);
            stshirt2.setFill(web);
            stshirt3.setFill(web);
            stshirt4.setFill(web);
        }
    }

    private void setTrouser(Color web){
        if(madga.isVisible()){
            mtrouser1.setFill(web);
            mtrouser2.setFill(web);
        } else {
            strousersleft.setFill(web);
            strousersright.setFill(web);
        }
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
        lbGender.setFont(kiona);
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
                showCharacter();
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
