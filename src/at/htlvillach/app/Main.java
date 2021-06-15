package at.htlvillach.app;

import at.htlvillach.gui.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.HashSet;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = null;
        Controller controller = null;
        BorderPane root = null;

        loader = new FXMLLoader(getClass().getResource("../gui/sample.fxml"));
        root = loader.load();
        controller = loader.getController();
        //controller.setActivitySet(new HashSet<>(new ActivityDBDao().getAll()));

        primaryStage.setTitle("Charactermanagement");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
