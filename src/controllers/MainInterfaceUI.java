package controllers;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import org.json.JSONException;
import org.json.JSONObject;

import app.Main;
import enums.FileType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import models.MainInterface;
import models.PersonalDisplay;
import models.TaskFile;
import models.TeamDisplay;

public class MainInterfaceUI {
	private MainInterface mainInterface;

	@FXML private HBox MainInterfaceGUI;
	@FXML private VBox fileContainer;
	@FXML private HBox addFileZone;
	@FXML private ComboBox<String> fileType;
	@FXML private TextField newFileName;
	@FXML private StackPane displayContainer;

	public MainInterfaceUI() {
		setMainInterface(new MainInterface());
		loadInitialFXML();
		handleHideAddFileZone();
		fileType.getItems().addAll("Personal","Team");
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
		Node addFileNode = fileContainer.getChildren().removeLast();
		fileContainer.getChildren().clear();
		for (TaskFile taskFile: mainInterface.getTaskFiles()) {
			fileContainer.getChildren().add((new TaskFileUI(taskFile)).getTaskFileGUI());
		}
		fileContainer.getChildren().add(addFileNode);
		if (Main.taskFileIdOpening != -1) {
			displayContainer.getChildren().clear();
			TaskFile taskFile = mainInterface.findTaskFile(Main.taskFileIdOpening);
			if (taskFile != null) {
				if (taskFile.getDisplay() instanceof TeamDisplay) {
					TeamDisplayUI teamDisplayUI = new TeamDisplayUI((TeamDisplay) taskFile.getDisplay());
					teamDisplayUI.updateGUI();
					displayContainer.getChildren().add(teamDisplayUI.getTeamDisplayGUI());
				} else if (taskFile.getDisplay() instanceof PersonalDisplay) {
					PersonalDisplayUI personalDisplayUI = new PersonalDisplayUI((PersonalDisplay) taskFile.getDisplay());
					personalDisplayUI.updateGUI();
					displayContainer.getChildren().add(personalDisplayUI.getPersonalDisplayGUI());
				}
			}
		}
	}

	@FXML
	public void handleAddFile() {
		if (fileType.getValue().equals("Personal")) {
			mainInterface.addTaskFile(newFileName.getText(),FileType.Personal, mainInterface);
		} else if (fileType.getValue().equals("Team")) {
			mainInterface.addTaskFile(newFileName.getText(),FileType.Team, mainInterface);
		}
		fileType.setValue(null);
		handleHideAddFileZone();
		updateGUI();
	}

	@FXML
	public void handleShowAddFileZone() {
		addFileZone.setManaged(true);
		addFileZone.setVisible(true);
	}

	@FXML
	public void handleHideAddFileZone() {
		newFileName.setText("");
		addFileZone.setManaged(false);
		addFileZone.setVisible(false);
	}

	@FXML
	public void handleImportFile() {
		handleHideAddFileZone();
		FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON Files", "*.json"));
        File selectedFile = fileChooser.showOpenDialog(Main.primaryStage);
        if (selectedFile != null) {
            try {
                String content = new String(Files.readAllBytes(selectedFile.toPath()), StandardCharsets.UTF_8);
                JSONObject importedJson = new JSONObject(content);
                	mainInterface.addTaskFile(importedJson, mainInterface);
                	updateGUI();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                System.err.println("Invalid JSON format.");
                e.printStackTrace();
            }
        }
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
