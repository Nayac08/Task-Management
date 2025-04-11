package controllers;

import java.io.IOException;
import app.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import models.MainInterface;
import models.TaskFile;

public class MainInterfaceUI {
	private MainInterface mainInterface;
	private int idxTest = 0;
	
	@FXML private HBox MainInterfaceGUI;
	@FXML private VBox fileContainer;
	@FXML private StackPane displayContainer;
	
	public MainInterfaceUI() {
		setMainInterface(new MainInterface());
		loadInitialFXML();
	}
	
	public void loadInitialFXML(){
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainInterface.fxml"));
            loader.setController(this);
            setMainInterfaceGUI(loader.load());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	public void updateGUI() {
		fileContainer.getChildren().clear();
		for (TaskFile taskFile: mainInterface.getTaskFiles()) {
			fileContainer.getChildren().add((new TaskFileUI(taskFile)).getTaskFileGUI());
		}
		if (Main.taskFileIdOpening != -1) {
			displayContainer.getChildren().clear();
			TaskFile taskFile = mainInterface.findTaskFile(Main.taskFileIdOpening);
			if (taskFile != null) {
				BoardUI boardUI = new BoardUI(taskFile.getBoard());
				boardUI.updateGUI();
				displayContainer.getChildren().add(boardUI.getBoardGUI());
			}	
		}
	}
	
	@FXML
	public void handleNewFile() {
		mainInterface.addTaskFile("New file" + Integer.toString(idxTest), mainInterface);
		idxTest++;
		updateGUI();
	}

	public HBox getMainInterfaceGUI() {
		return MainInterfaceGUI;
	}

	public void setMainInterfaceGUI(HBox mainInterfaceGUI) {
		MainInterfaceGUI = mainInterfaceGUI;
	}

	public MainInterface getMainInterface() {
		return mainInterface;
	}

	public void setMainInterface(MainInterface mainInterface) {
		this.mainInterface = mainInterface;
	}

	public StackPane getDisplayContainer() {
		return displayContainer;
	}

	public void setDisplayContainer(StackPane displayContainer) {
		this.displayContainer = displayContainer;
	}
}
