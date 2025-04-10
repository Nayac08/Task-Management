package app;


import controllers.BoardUI;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
	public static BoardUI boardUI;
	
    @Override
    public void start(Stage primaryStage){
        // Load FXML file
    	BoardUI boardUI = new BoardUI();
        Parent root = boardUI.getBoardGUI();
        Main.boardUI = boardUI;
        
        // Set up the scene
        primaryStage.setTitle("Task Management");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("/Logo.png"));
        primaryStage.setScene(new Scene(root, 1420, 720));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

