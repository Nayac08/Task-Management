package controllers;

import java.io.IOException;

import app.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import models.Card;
import models.NodeList;
import models.PersonalCard;

public class PersonalNodeListUI{
	private NodeList nodeList;

	@FXML private HBox personalNodeListGUI;
	@FXML private Text nodeListTitle;
	@FXML private HBox cardContainer;
	@FXML private TextField titleArea;
	@FXML private VBox addCardDetail;
	@FXML private StackPane warningCardName;

	public PersonalNodeListUI(NodeList nodeList){
		setNodeList(nodeList);
		loadInitialFXML();
		handleHideWarningCardName();
	}

    public void loadInitialFXML(){
    	FXMLLoader loader = null;
    	try {
    		loader = new FXMLLoader(getClass().getResource("/PersonalNodeList.fxml"));
            loader.setController(this);
            setPersonalNodeListGUI(loader.load());
            nodeListTitle.setText(nodeList.getTitle());
    		handleHideAddDetailButton();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public void updateGUI() {
    	Node addCardButton = cardContainer.getChildren().removeLast();
    	Node addCardZone = cardContainer.getChildren().removeLast();
    	cardContainer.getChildren().clear();
    	for (Card card: nodeList.getCards()) {
    		PersonalCardUI personalCardUI = new PersonalCardUI((PersonalCard) card);
    		personalCardUI.updateGUI();
    		cardContainer.getChildren().add(personalCardUI.getPersonalCardGUI());
    	}
    	cardContainer.getChildren().add(addCardZone);
    	cardContainer.getChildren().add(addCardButton);
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
    		nodeList.addCard(new PersonalCard(nodeList.getIdxCard(), nodeList, titleArea.getText().trim()));
        	titleArea.setText("");
        	handleHideAddDetailButton();
        	updateGUI();
    	}

    }

    public void handleShowAddDetailButton() {
    	addCardDetail.setVisible(true);
		addCardDetail.setManaged(true);
    }

    public void handleHideAddDetailButton() {
    	titleArea.setText("");
    	addCardDetail.setVisible(false);
		addCardDetail.setManaged(false);
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

	public HBox getPersonalNodeListGUI() {
		return personalNodeListGUI;
	}

	public void setPersonalNodeListGUI(HBox personalNodeListGUI) {
		this.personalNodeListGUI = personalNodeListGUI;
	}

	public NodeList getNodeList() {
		return nodeList;
	}

	public void setNodeList(NodeList nodeList) {
		this.nodeList = nodeList;
	}
}