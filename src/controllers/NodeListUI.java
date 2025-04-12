package controllers;

import java.io.IOException;
import java.util.ListResourceBundle;

import app.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import models.Card;
import models.NodeList;
import models.PersonalCard;
import models.TeamCard;

public class NodeListUI{
	private NodeList nodeList;

	@FXML private VBox nodeListGUI;
	@FXML private Text nodeListTitle;
	@FXML private VBox cardContainer;
	@FXML private TextArea titleArea;
	@FXML private VBox addCardDetail;

	public NodeListUI(NodeList nodeList) throws IOException {
		setNodeList(nodeList);
		loadInitialFXML();
	}

    public void loadInitialFXML(){
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/NodeList.fxml"));
            loader.setController(this);
            setNodeListGUI(loader.load());
            nodeListTitle.setText(nodeList.getTitle());
    		handleHideAddDetailButton();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public void updateGUI() {
    	cardContainer.getChildren().clear();
    	for (Card card: nodeList.getCards()) {
    		if (card instanceof TeamCard) {
    			TeamCardUI teamCardUI = new TeamCardUI((TeamCard) card);
        		teamCardUI.updateGUI();
        		cardContainer.getChildren().add(teamCardUI.getTeamCardGUI());
    		} else if (card instanceof PersonalCard) {
    			PersonalCardUI personalCardUI = new PersonalCardUI((PersonalCard) card);
        		personalCardUI.updateGUI();
        		cardContainer.getChildren().add(personalCardUI.getPersonalCardGUI());
    		}
    	}
    }

    @FXML
    public void handleAddCardToNodeList(){
    	// TODO for TeamCard
    	nodeList.addCard(new TeamCard(nodeList.getIdxCard(), nodeList, titleArea.getText()));
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
    	System.out.println("run");
    	for (NodeList list :nodeList.getDisplayOwner().getNodeLists()) {
    		System.out.println(list.getId());
    	}
    	for (NodeList list :nodeList.getDisplayOwner().getNodeLists()) {
    		if (list.getId() == nodeList.getId()) {
    			nodeList.getDisplayOwner().getNodeLists().remove(list);
    			Main.mainInterfaceUI.updateGUI();
    			break;
    		}
    	}
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