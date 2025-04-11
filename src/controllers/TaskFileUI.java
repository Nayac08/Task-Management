package controllers;

import java.io.File;
import java.io.IOException;
import app.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import models.TaskFile;

public class TaskFileUI {
	private TaskFile taskFile;
	
	@FXML private StackPane taskFileGUI;
	@FXML private Text fileName;
	
	public TaskFileUI(TaskFile taskFile) {
		setTaskFile(taskFile);
		loadInitialFXML();
		fileName.setText(taskFile.getBoard().getName());
	}
	
	public void loadInitialFXML(){
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/TaskFile.fxml"));
            loader.setController(this);
            setTaskFileGUI(loader.load());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@FXML
	public void handleDeleteFile() {
		taskFile.getMainInterface().deleteTaskFile(taskFile.getId());
		Main.mainInterfaceUI.updateGUI();
	}
	
	@FXML
	public void handleOpenFile() {
		Main.mainInterfaceUI.getDisplayContainer().getChildren().clear();
		BoardUI boardUI = new BoardUI(taskFile.getBoard());
		boardUI.updateGUI();
		Main.mainInterfaceUI.getDisplayContainer().getChildren().add(boardUI.getBoardGUI());
		Main.taskFileIdOpening = taskFile.getId();
	}
	
	public void updateGUI() {
		
	}

	public TaskFile getTaskFile() {
		return taskFile;
	}

	public void setTaskFile(TaskFile taskFile) {
		this.taskFile = taskFile;
	}

	public StackPane getTaskFileGUI() {
		return taskFileGUI;
	}

	public void setTaskFileGUI(StackPane taskFileGUI) {
		this.taskFileGUI = taskFileGUI;
	}
}
