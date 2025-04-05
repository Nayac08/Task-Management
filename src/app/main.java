package app;

import java.io.File;
import java.io.InputStream;

import controllers.BoardUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load FXML file
    	BoardUI boardUI = new BoardUI();
        Parent root = boardUI.getBoardGUI();
        
        // Set up the scene
        primaryStage.setTitle("JavaFX FXML Example");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

