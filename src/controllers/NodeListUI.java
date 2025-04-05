package controllers;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Card;
import models.NodeList;

public class NodeListUI{
	public VBox nodeListGUI;
	public NodeList nodeList;
	
	public NodeListUI(NodeList nodeList) throws IOException {
		this.nodeListGUI = FXMLLoader.load(getClass().getResource("NodeList.fxml"));
		this.nodeList = nodeList;
	}
	
    public void updateNodeList(NodeList list){
    	
    }

    public void addCardToNodeList(Card card){

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