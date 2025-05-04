package controllers;

import java.io.IOException;

import app.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import models.NodeList;
import models.TeamDisplay;

public class TeamDisplayUI{
	private TeamDisplay teamDisplay;

	@FXML private VBox teamDisplayGUI;
	@FXML private Text displayName;
	@FXML private HBox displayZone;
	@FXML private VBox addListZone;
	@FXML private VBox addListNodeDetail;
	@FXML private TextArea titleArea;
	@FXML private Button addListNodeButton;

	public TeamDisplayUI(TeamDisplay teamDisplay) {
		setTeamDisplay(teamDisplay);
		loadInitialFXML();
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
    	teamDisplay.addNodeList(new NodeList(teamDisplay.getIdxListNode(), teamDisplay, titleArea.getText()));
    	titleArea.setText("");
    	handleHideAddListDetailButton();
    	updateGUI();
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