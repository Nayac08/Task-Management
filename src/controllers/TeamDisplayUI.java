package controllers;

import java.io.IOException;

import app.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import models.NodeList;
import models.TeamDisplay;

public class TeamDisplayUI{
	private TeamDisplay teamDisplay;

	@FXML private VBox teamDisplayGUI;
	@FXML private Label displayName;
	@FXML private HBox displayZone;
	@FXML private VBox addListZone;
	@FXML private VBox addListNodeDetail;
	@FXML private TextField titleArea;
	@FXML private Button addListNodeButton;
	@FXML private StackPane warningNewListName;

	public TeamDisplayUI(TeamDisplay teamDisplay) {
		setTeamDisplay(teamDisplay);
		loadInitialFXML();
		handleHideWarningNewListName();
        displayName.setText(teamDisplay.getName());
	}

    public void loadInitialFXML(){
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/TeamDisplay.fxml"));
            loader.setController(this);
            setTeamDisplayGUI(loader.load());
            handleHideAddListDetailButton();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public void updateGUI() {
    	Node addListNode = displayZone.getChildren().removeLast();
    	displayZone.getChildren().clear();
    	for (NodeList nodeList: teamDisplay.getNodeLists()) {
    		TeamNodeListUI teamNodeListUI;
			teamNodeListUI = new TeamNodeListUI(nodeList);
			teamNodeListUI.updateGUI();
			displayZone.getChildren().add(teamNodeListUI.getNodeListGUI());
    	}
    	displayZone.getChildren().add(addListNode);
    	handleHideAddListDetailButton();
    }

    @FXML
    public void handleAddNodeListToBoard() {
    	if (titleArea.getText().equals("")) {
    		new Thread(() -> {
				Platform.runLater(() -> {
					handleShowWarningNewListName();
				});
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Platform.runLater(() -> {
					handleHideWarningNewListName();
				});
			}).start();
    	} else {
    		handleHideWarningNewListName();
    		teamDisplay.addNodeList(new NodeList(teamDisplay.getIdxListNode(), teamDisplay, titleArea.getText()));
        	titleArea.setText("");
        	handleHideAddListDetailButton();
        	updateGUI();
    	}	
    }

    @FXML
    public void handleShowAddListDetailButton() {
    	addListNodeDetail.setVisible(true);
    	addListNodeDetail.setManaged(true);
    	addListNodeButton.setVisible(false);
    	addListNodeButton.setManaged(false);
    }

    @FXML
    public void handleHideAddListDetailButton() {
    	titleArea.setText("");
    	addListNodeDetail.setVisible(false);
    	addListNodeDetail.setManaged(false);
    	addListNodeButton.setVisible(true);
    	addListNodeButton.setManaged(true);
    }

    @FXML
    public void handleClearDisplay() {
    	Main.taskFileIdOpening = -1;
    	Main.mainInterfaceUI.getDisplayContainer().getChildren().clear();
    }
    
    public void handleHideWarningNewListName() {
    	warningNewListName.setVisible(false);
    	warningNewListName.setManaged(false);
    }
    
    public void handleShowWarningNewListName() {
    	warningNewListName.setVisible(true);
    	warningNewListName.setManaged(true);
    }

	public TeamDisplay getTeamDisplay() {
		return teamDisplay;
	}

	public void setTeamDisplay(TeamDisplay teamDisplay) {
		this.teamDisplay = teamDisplay;
	}

	public VBox getTeamDisplayGUI() {
		return teamDisplayGUI;
	}

	public void setTeamDisplayGUI(VBox teamDisplayGUI) {
		this.teamDisplayGUI = teamDisplayGUI;
	}
}