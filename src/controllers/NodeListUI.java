package controllers;

import java.io.IOException;
import java.util.Arrays;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Card;
import models.NodeList;
import models.PersonalCard;

public class NodeListUI{
	private NodeList nodeList;
	
	@FXML private VBox nodeListGUI;
	@FXML private Text nodeListTitle;
	@FXML private VBox cardContainer;
	@FXML private TextArea titleArea;
	@FXML private VBox addCardDetail;
	
	public NodeListUI(NodeList nodeList) throws IOException {
		this.nodeList = nodeList;
		loadInitialFXML();
	}
	
    public void loadInitialFXML(){
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/NodeList.fxml"));
            loader.setController(this);
            this.nodeListGUI = loader.load();
            nodeListTitle.setText(nodeList.getTitle());
    		handleHideAddDetailButton();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void updateGUI() {
    	cardContainer.getChildren().clear();
    	for (Card card: nodeList.getCards()) {
    		PersonalCardUI personalCardUI = new PersonalCardUI((PersonalCard) card);
    		personalCardUI.updateGUI();
    		cardContainer.getChildren().add(personalCardUI.getPersonalCardGUI());
    	}
    }

    @FXML
    public void handleAddCardToNodeList(){
    	System.out.println("Click add card");
    	nodeList.addCard(new PersonalCard(0, null, titleArea.getText()));
    	titleArea.setText("");
    	handleHideAddDetailButton();
    	updateGUI();
    	System.out.println(nodeList.getCards().size());
    }
    
    @FXML
    public void handleShowAddDetailButton() {
    	addCardDetail.setVisible(true);
		addCardDetail.setManaged(true);
    }
    
    @FXML 
    public void handleHideAddDetailButton() {
    	addCardDetail.setVisible(false);
		addCardDetail.setManaged(false);
    }
    
    public void removeCardFromNodeList(Card card){
    
    }

    public void sortCardsInNodeList(String criteria){

    }

	public VBox getNodeListGUI() {
		return nodeListGUI;
	}

	public void setNodeListGUI(VBox nodeListGUI) {
		this.nodeListGUI = nodeListGUI;
	}

	public NodeList getNodeList() {
		return nodeList;
	}

	public void setNodeList(NodeList nodeList) {
		this.nodeList = nodeList;
	}
}