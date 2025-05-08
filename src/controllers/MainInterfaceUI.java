package controllers;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import org.json.JSONException;
import org.json.JSONObject;

import app.Main;
import enums.FileType;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import models.MainInterface;
import models.PersonalDisplay;
import models.TaskFile;
import models.TeamDisplay;

public class MainInterfaceUI {
	private MainInterface mainInterface;
	
	@FXML private HBox mainInterfaceGUI;
	@FXML private VBox fileContainer;
	@FXML private HBox addFileZone;
	@FXML private ComboBox<String> fileType;
	@FXML private TextField newFileName;
	@FXML private StackPane displayContainer;

	@FXML private StackPane warningNewFileName;
	@FXML private StackPane warningFileType;

	public MainInterfaceUI() {
		setMainInterface(new MainInterface());
		loadInitialFXML();
	}

	public void loadInitialFXML(){
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainInterface.fxml"));
            loader.setController(this);
            setMainInterfaceGUI(loader.load());
            
            handleHideAddFileZone();
    		handleHideWarningFileType();
    		handleHideWarningNewFileName();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	public void updateGUI() {
		Node warningFileType = fileContainer.getChildren().removeLast();
		Node warningFileName = fileContainer.getChildren().removeLast();
		Node addFileNode = fileContainer.getChildren().removeLast();
		fileContainer.getChildren().clear();
		for (TaskFile taskFile: mainInterface.getTaskFiles()) {
			fileContainer.getChildren().add((new TaskFileUI(taskFile)).getTaskFileGUI());
		}
		fileContainer.getChildren().add(addFileNode);
		fileContainer.getChildren().add(warningFileName);
		fileContainer.getChildren().add(warningFileType);
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

	
	public void handleAddFile() {
		if (newFileName.getText().trim().equals("")) {
			new Thread(() -> {
				Platform.runLater(() -> {
					handleHideWarningFileType();
					handleShowWarningNewFileName();
				});
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Platform.runLater(() -> {
					handleHideWarningNewFileName();
				});
			}).start();
		} else if (fileType.getValue() == null) {
			new Thread(() -> {
				Platform.runLater(() -> {
					handleHideWarningNewFileName();
					handleShowWarningFileType();
				});
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Platform.runLater(() -> {
					handleHideWarningFileType();
				});
			}).start();
		} else {
			handleHideWarningNewFileName();
			handleHideWarningFileType();
			if (fileType.getValue().equals("Personal")) {
				mainInterface.addTaskFile(newFileName.getText().trim(),FileType.Personal, mainInterface);
			} else if (fileType.getValue().equals("Team")) {
				mainInterface.addTaskFile(newFileName.getText().trim(),FileType.Team, mainInterface);
			}
			handleHideAddFileZone();
			updateGUI();
		}
	}

	
	public void handleShowAddFileZone() {
		addFileZone.setManaged(true);
		addFileZone.setVisible(true);
	}

	
	public void handleHideAddFileZone() {
		newFileName.setText("");
		fileType.getSelectionModel().clearSelection();
		addFileZone.setManaged(false);
		addFileZone.setVisible(false);
	}

	public void handleHideWarningNewFileName() {
		warningNewFileName.setVisible(false);
		warningNewFileName.setManaged(false);
	}

	public void handleShowWarningNewFileName() {
		warningNewFileName.setVisible(true);
		warningNewFileName.setManaged(true);
	}

	public void handleHideWarningFileType() {
		warningFileType.setVisible(false);
		warningFileType.setManaged(false);
	}

	public void handleShowWarningFileType() {
		warningFileType.setVisible(true);
		warningFileType.setManaged(true);
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
		return mainInterfaceGUI;
	}

	public void setMainInterfaceGUI(HBox mainInterfaceGUI) {
		this.mainInterfaceGUI = mainInterfaceGUI;
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
