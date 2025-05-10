package controllers;

import java.io.IOException;

import app.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.NodeList;
import models.TeamDisplay;

public class TeamDisplayUI{
	private TeamDisplay teamDisplay;

	@FXML private VBox teamDisplayGUI;
	@FXML private Label displayName;
	@FXML private HBox displayZone;
	@FXML private VBox addListNodeDetail;
	@FXML private TextField titleArea;
	@FXML private Button addListNodeButton;
	@FXML private StackPane warningNewListName;

	public TeamDisplayUI(TeamDisplay teamDisplay) {
		setTeamDisplay(teamDisplay);
		loadInitialFXML();
	}

    public void loadInitialFXML(){
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/TeamDisplay.fxml"));
            loader.setController(this);
            setTeamDisplayGUI(loader.load());
            handleHideAddListDetail();
            handleHideWarningNewListName();
            displayName.setText(teamDisplay.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public void updateGUI() {
    	Node addListNode = displayZone.getChildren().removeLast();
    	displayZone.getChildren().clear();
    	int i = 0;
    	for (NodeList nodeList: teamDisplay.getNodeLists()) {
    		TeamNodeListUI teamNodeListUI = null;
    		if (i % 4 == 0) {
    			teamNodeListUI = new TeamNodeListUI(nodeList, Color.web("#A179F2"));
    		} else if (i % 4 == 1) {
    			teamNodeListUI = new TeamNodeListUI(nodeList, Color.web("#4170FF"));
    		} else if (i % 4 == 2) {
    			teamNodeListUI = new TeamNodeListUI(nodeList, Color.web("#01ABFE"));
    		} else if (i % 4 == 3) {
    			teamNodeListUI = new TeamNodeListUI(nodeList, Color.web("#97DB0B"));
    		}
			i++;
			teamNodeListUI.updateGUI();
			displayZone.getChildren().add(teamNodeListUI.getTeamNodeListGUI());
    	}
    	displayZone.getChildren().add(addListNode);
    	handleHideAddListDetail();
    }

    public void handleAddNodeListToBoard() {
    	if (titleArea.getText().trim().equals("")) {
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
    		teamDisplay.addNodeList(new NodeList(teamDisplay.getIdxListNode(), teamDisplay, titleArea.getText().trim()));
        	titleArea.setText("");
        	handleHideAddListDetail();
        	updateGUI();
    	}
    }

    public void handleShowModalPopupMember() {
    	ModalPopupMemberUI modalPopupMemberUI = new ModalPopupMemberUI(teamDisplay);

    	Stage popupStage = new Stage();
        popupStage.setScene(new Scene(modalPopupMemberUI.getModalPopupMemberGUI()));
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setResizable(false);
        popupStage.show();
    }

    public void handleShowAddListDetail() {
    	addListNodeDetail.setVisible(true);
    	addListNodeDetail.setManaged(true);
    	addListNodeButton.setVisible(false);
    	addListNodeButton.setManaged(false);
    }

    public void handleHideAddListDetail() {
    	titleArea.setText("");
    	addListNodeDetail.setVisible(false);
    	addListNodeDetail.setManaged(false);
    	addListNodeButton.setVisible(true);
    	addListNodeButton.setManaged(true);
    }

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