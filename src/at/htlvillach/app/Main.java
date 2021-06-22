package at.htlvillach.app;

import at.htlvillach.bll.Character;
import at.htlvillach.dal.dao.CharacterDBDao;
import at.htlvillach.gui.SelectCharacterController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
        controller.setCharacters(new HashSet<>((new CharacterDBDao()).getAll()));

        primaryStage.setTitle("Character Management");
        primaryStage.setScene(new Scene(root));
        primaryStage.getIcons().add(new Image("file:steve.png"));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
