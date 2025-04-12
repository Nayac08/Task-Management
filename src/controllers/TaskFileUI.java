package controllers;

import java.io.IOException;

import app.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import models.PersonalDisplay;
import models.TaskFile;
import models.TeamDisplay;

public class TaskFileUI {
	private TaskFile taskFile;

	@FXML private HBox taskFileGUI;
	@FXML private Text fileName;

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

	@FXML
	public void handleDeleteFile() {
		taskFile.getMainInterface().deleteTaskFile(taskFile.getId());
		Main.mainInterfaceUI.updateGUI();
	}

	@FXML
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

	public void updateGUI() {

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
