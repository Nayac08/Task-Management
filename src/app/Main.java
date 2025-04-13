package app;

import controllers.MainInterfaceUI;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
	public static MainInterfaceUI mainInterfaceUI;
	public static Stage primaryStage;
	public static int taskFileIdOpening = -1;

    @Override
    public void start(Stage primaryStage) {
    	Main.primaryStage = primaryStage;
        // Load FXML file
    	MainInterfaceUI mainInterfaceUI = new MainInterfaceUI();
        Parent root = mainInterfaceUI.getMainInterfaceGUI();
        Main.mainInterfaceUI = mainInterfaceUI;

        // Set up the scene
        primaryStage.setTitle("Task Management");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("/Logo.png"));
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

