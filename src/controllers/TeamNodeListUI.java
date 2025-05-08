package controllers;

import java.io.IOException;

import app.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import models.Card;
import models.NodeList;
import models.TeamCard;

public class TeamNodeListUI{
	private NodeList nodeList;

	@FXML private VBox teamNodeListGUI;
	@FXML private StackPane header;
	@FXML private Label nodeListTitle;
	@FXML private VBox cardContainer;
	@FXML private TextField titleArea;
	@FXML private VBox addCardDetail;
	@FXML private Button addACardButton;
	@FXML private StackPane warningCardName;

	public TeamNodeListUI(NodeList nodeList, Color color){
		setNodeList(nodeList);
		loadInitialFXML(color);
		handleHideWarningCardName();
	}

    public void loadInitialFXML(Color color){
    	FXMLLoader loader = null;
    	try {
    		loader = new FXMLLoader(getClass().getResource("/TeamNodeList.fxml"));
            loader.setController(this);
            setTeamNodeListGUI(loader.load());
            nodeListTitle.setText(nodeList.getTitle());
            	header.setBackground(new Background(new BackgroundFill(color, null, null)));
    		handleHideAddDetailButton();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public void updateGUI() {
    	cardContainer.getChildren().clear();
    	for (Card card: nodeList.getCards()) {
    		TeamCardUI teamCardUI = new TeamCardUI((TeamCard) card);
    		teamCardUI.updateGUI();
    		cardContainer.getChildren().add(teamCardUI.getTeamCardGUI());
    	}

    	if (nodeList.getCards().size() >= 10) {
    		addACardButton.setVisible(false);
    		addACardButton.setManaged(false);
    	} else {
    		addACardButton.setVisible(true);
    		addACardButton.setManaged(true);
    	}
    }

    public void handleAddCardToNodeList(){
    	if (titleArea.getText().trim().equals("")) {
    		new Thread(() -> {
				Platform.runLater(() -> {
					handleShowWarningCardName();
				});
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Platform.runLater(() -> {
					handleHideWarningCardName();
				});
			}).start();
    	} else {
    		handleHideWarningCardName();
    		nodeList.addCard(new TeamCard(nodeList.getIdxCard(), nodeList, titleArea.getText().trim()));
        	titleArea.setText("");
        	addACardButton.setVisible(true);
    		addACardButton.setManaged(true);
        	handleHideAddDetailButton();
        	updateGUI();
    	}

    }

    public void handleShowAddDetailButton() {
    	addACardButton.setVisible(false);
		addACardButton.setManaged(false);
    	addCardDetail.setVisible(true);
		addCardDetail.setManaged(true);
    }

    public void handleHideAddDetailButton() {
    	titleArea.setText("");
    	addCardDetail.setVisible(false);
		addCardDetail.setManaged(false);
		addACardButton.setVisible(true);
		addACardButton.setManaged(true);
    }

    public void handleDeleteNodeList() {
    	for (NodeList list :nodeList.getDisplayOwner().getNodeLists()) {
    		if (list.getId() == nodeList.getId()) {
    			nodeList.getDisplayOwner().getNodeLists().remove(list);
    			Main.mainInterfaceUI.updateGUI();
    			break;
    		}
    	}
    }

    public void handleHideWarningCardName() {
    	warningCardName.setVisible(false);
    	warningCardName.setManaged(false);
    }

    public void handleShowWarningCardName() {
    	warningCardName.setVisible(true);
    	warningCardName.setManaged(true);
    }

	public VBox getTeamNodeListGUI() {
		return teamNodeListGUI;
	}

	public void setTeamNodeListGUI(VBox teamNodeListGUI) {
		this.teamNodeListGUI = teamNodeListGUI;
	}

	public NodeList getNodeList() {
		return nodeList;
	}

	public void setNodeList(NodeList nodeList) {
		this.nodeList = nodeList;
	}
}