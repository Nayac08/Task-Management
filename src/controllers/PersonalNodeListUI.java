package controllers;

import java.io.IOException;

import app.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import models.Card;
import models.NodeList;
import models.PersonalCard;
import models.PersonalDisplay;
import models.TeamCard;
import models.TeamDisplay;

public class PersonalNodeListUI{
	private NodeList nodeList;
	
	@FXML private HBox nodeListGUI;
	@FXML private Text nodeListTitle;
	@FXML private HBox cardContainer;
	@FXML private TextField titleArea;
	@FXML private VBox addCardDetail;

	public PersonalNodeListUI(NodeList nodeList){
		setNodeList(nodeList);
		loadInitialFXML();
	}

    public void loadInitialFXML(){
    	FXMLLoader loader = null;
    	try {
    		loader = new FXMLLoader(getClass().getResource("/PersonalNodeList.fxml"));
            loader.setController(this);
            setNodeListGUI(loader.load());
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

    @FXML
    public void handleAddCardToNodeList(){
    	nodeList.addCard(new PersonalCard(nodeList.getIdxCard(), nodeList, titleArea.getText()));
    	titleArea.setText("");
    	handleHideAddDetailButton();
    	updateGUI();
    }

    @FXML
    public void handleShowAddDetailButton() {
    	addCardDetail.setVisible(true);
		addCardDetail.setManaged(true);
    }

    @FXML
    public void handleHideAddDetailButton() {
    	titleArea.setText("");
    	addCardDetail.setVisible(false);
		addCardDetail.setManaged(false);
    }

    @FXML
    public void handleDeleteNodeList() {
    	for (NodeList list :nodeList.getDisplayOwner().getNodeLists()) {
    		if (list.getId() == nodeList.getId()) {
    			nodeList.getDisplayOwner().getNodeLists().remove(list);
    			Main.mainInterfaceUI.updateGUI();
    			break;
    		}
    	}
    }

	public HBox getNodeListGUI() {
		return nodeListGUI;
	}

	public void setNodeListGUI(HBox nodeListGUI) {
		this.nodeListGUI = nodeListGUI;
	}

	public NodeList getNodeList() {
		return nodeList;
	}

	public void setNodeList(NodeList nodeList) {
		this.nodeList = nodeList;
	}
}