package app;


import controllers.BoardUI;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	public static Stage primaryStage;
	
    @Override
    public void start(Stage primaryStage){
    	Main.primaryStage = primaryStage;
        // Load FXML file
    	BoardUI boardUI = new BoardUI();
        Parent root = boardUI.getBoardGUI();
        
        // Set up the scene
        primaryStage.setTitle("Task Management");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

