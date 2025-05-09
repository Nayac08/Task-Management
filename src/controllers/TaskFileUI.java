package controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

import app.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.PersonalDisplay;
import models.TaskFile;
import models.TeamDisplay;

public class TaskFileUI {
	private TaskFile taskFile;

	@FXML private HBox taskFileGUI;
	@FXML private Button deleteButton;
	@FXML private Label fileName;

	public TaskFileUI(TaskFile taskFile) {
		setTaskFile(taskFile);
		loadInitialFXML();
		fileName.setText(taskFile.getDisplay().getName());
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

	public void handleDeleteFile() {
        
		taskFile.getMainInterface().deleteTaskFile(taskFile.getId());
		Main.mainInterfaceUI.updateGUI();
	}

	public void handleOpenFile() {
		Main.mainInterfaceUI.getDisplayContainer().getChildren().clear();
		if (taskFile.getDisplay() instanceof TeamDisplay) {
			TeamDisplayUI teamDisplayUI = new TeamDisplayUI((TeamDisplay) taskFile.getDisplay());
			teamDisplayUI.updateGUI();
			Main.mainInterfaceUI.getDisplayContainer().getChildren().add(teamDisplayUI.getTeamDisplayGUI());
		} else if (taskFile.getDisplay() instanceof PersonalDisplay) {
			PersonalDisplayUI personalDisplayUI = new PersonalDisplayUI((PersonalDisplay) taskFile.getDisplay());
			personalDisplayUI.updateGUI();
			Main.mainInterfaceUI.getDisplayContainer().getChildren().add(personalDisplayUI.getPersonalDisplayGUI());
		}
		Main.taskFileIdOpening = taskFile.getId();
		Main.mainInterfaceUI.handleHideAddFileZone();
	}

	public void handleExportFile() {
		Main.mainInterfaceUI.handleHideAddFileZone();
		FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a destination");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON Files", "*.json"));
        fileChooser.setInitialFileName(fileName.getText());
        File selectedFile = fileChooser.showSaveDialog(Main.primaryStage);
        if (selectedFile != null) {
        	try (FileWriter writer = new FileWriter(selectedFile)) {
                writer.write(taskFile.getJsonObject().toString(4)); // pretty print with indentation
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}

	public TaskFile getTaskFile() {
		return taskFile;
	}

	public void setTaskFile(TaskFile taskFile) {
		this.taskFile = taskFile;
	}

	public HBox getTaskFileGUI() {
		return taskFileGUI;
	}

	public void setTaskFileGUI(HBox taskFileGUI) {
		this.taskFileGUI = taskFileGUI;
	}
}
