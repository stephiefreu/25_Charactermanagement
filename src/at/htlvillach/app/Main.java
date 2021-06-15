package at.htlvillach.app;

import at.htlvillach.bll.Character;
import at.htlvillach.gui.SelectCharacterController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = null;
        SelectCharacterController controller = null;
        Pane root = null;

        loader = new FXMLLoader(getClass().getResource("../gui/selectCharacter.fxml"));
        root = loader.load();
        controller = loader.getController();
        //controller.setActivitySet(new HashSet<>(new ActivityDBDao().getAll()));
        Character c1 = new Character("Steve", 23, "#aaaaaa", "#aaaaaa", "#aaaaaa", "#aaaaaa");
        Character c2 = new Character("Magda", 21, "#bbbbbb", "#bbbbbb", "#bbbbbb", "#bbbbbb");
        Set<Character> characters = new HashSet<>();
        characters.add(c1);
        characters.add(c2);
        controller.setCharacters(characters);

        primaryStage.setTitle("Charactermanagement");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
