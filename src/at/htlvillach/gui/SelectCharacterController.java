package at.htlvillach.gui;

import at.htlvillach.dal.dao.CharacterDBDao;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import at.htlvillach.bll.Character;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Set;

public class SelectCharacterController implements Initializable {
    @FXML
    private Label lbHeading;
    @FXML
    private Label lbSubheading;
    @FXML
    private ListView lvCharacters;
    private Character currentCharacter = null;
    private Set<Character> characters;
    private CharacterDBDao dao = new CharacterDBDao();

    public void setCharacters(Set<Character> characters){
        this.characters = characters;
        ObservableList<Character> observableList = FXCollections.observableList(new ArrayList<>(characters));
        lvCharacters.setItems(observableList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setFonts();
        configureListView();
    }

    private void setFonts(){
        Font luna = Font.loadFont("file:resources/fonts/Luna.ttf", 18);
        Font kiona = Font.loadFont("file:resources/fonts/Kiona.ttf", 14);
        lbHeading.setFont(luna);
        lbSubheading.setFont(kiona);
    }

    private void configureListView(){
        lvCharacters.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        lvCharacters.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldObject, Object newObject) {
                currentCharacter = (Character) newObject;
            }
        });
    }

    @FXML
    private void editCharacter(ActionEvent actionEvent) throws IOException {
        openEditCharacterView();
    }

    @FXML
    private void removeCharacter(ActionEvent actionEvent) {
        if(currentCharacter != null){
            characters.remove(currentCharacter);
            dao.delete(currentCharacter);

            ObservableList<Character> observableList = FXCollections.observableList(new ArrayList<>(characters));
            lvCharacters.setItems(observableList);
        }
    }

    @FXML
    private void createCharacter(ActionEvent actionEvent) {
        Character newCharacter = new Character("Susi", 15, "FEMALE", "#ffffff", "#ffffff", "#ffffff", "#ffffff", "#ffffff");
        characters.add(newCharacter);
        dao.insert(newCharacter);

        ObservableList<Character> observableList = FXCollections.observableList(new ArrayList<>(characters));
        lvCharacters.setItems(observableList);
        lvCharacters.getSelectionModel().select(newCharacter);
    }

    private void openEditCharacterView() throws IOException {
        if(this.currentCharacter != null){
            FXMLLoader loader = null;
            Pane root = null;
            EditCharacterController controller = null;

            loader = new FXMLLoader(getClass().getResource("editCharacter.fxml"));
            root = loader.load();
            controller = loader.getController();
            controller.setCharacter(currentCharacter);

            Stage stage = new Stage();
            Scene scene = new Scene(root, 700, 600);
            stage.setScene(scene);
            stage.setTitle("Edit Character");
            stage.getIcons().add(new Image("file:magda.png"));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            lvCharacters.refresh();
        }
    }
}
