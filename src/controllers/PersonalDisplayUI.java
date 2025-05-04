package controllers;

import java.io.IOException;

import app.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import models.NodeList;
import models.PersonalDisplay;

public class PersonalDisplayUI {
	private PersonalDisplay personalDisplay;

	@FXML private VBox personalDisplayGUI;
	@FXML private Label displayName;
	@FXML private VBox displayZone;

	public PersonalDisplayUI(PersonalDisplay personalDisplay) {
		setPersonalDisplay(personalDisplay);
		loadInitialFXML();
		displayName.setText(personalDisplay.getName());
	}

    public void loadInitialFXML(){
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/PersonalDisplay.fxml"));
            loader.setController(this);
            setPersonalDisplayGUI(loader.load());
            	if (personalDisplay.getNodeLists().size() <= 3) {
            		personalDisplay.addNodeList(new NodeList(personalDisplay.getIdxListNode(), personalDisplay, "Study"));
                	personalDisplay.addNodeList(new NodeList(personalDisplay.getIdxListNode(), personalDisplay, "Work"));
                	personalDisplay.addNodeList(new NodeList(personalDisplay.getIdxListNode(), personalDisplay, "Health"));
            	}
        	
        	updateGUI();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public void updateGUI() {
    	displayZone.getChildren().clear();
    	for (NodeList nodeList: personalDisplay.getNodeLists()) {
    		PersonalNodeListUI personalNodeListUI = new PersonalNodeListUI(nodeList);
    		if (nodeList.getTitle().equals("Study")) {
    			personalNodeListUI.getNodeListGUI().getChildren().getFirst().setStyle("-fx-background-color: #A7C7E7;");
    		} else if (nodeList.getTitle().equals("Work")) {
   			 	personalNodeListUI.getNodeListGUI().getChildren().getFirst().setStyle("-fx-background-color: #B7E4C7;");
    		} else if (nodeList.getTitle().equals("Health")) {
   			 	personalNodeListUI.getNodeListGUI().getChildren().getFirst().setStyle("-fx-background-color: #FFDAB9;");
    		}
    		personalNodeListUI.updateGUI();
    		displayZone.getChildren().add(personalNodeListUI.getNodeListGUI());
    	}
    }

    @FXML
    public void handleClearDisplay() {
    	Main.taskFileIdOpening = -1;
    	Main.mainInterfaceUI.getDisplayContainer().getChildren().clear();
    }
    
	public PersonalDisplay getPersonalDisplay() {
		return personalDisplay;
	}

	public void setPersonalDisplay(PersonalDisplay personalDisplay) {
		this.personalDisplay = personalDisplay;
	}

	public VBox getPersonalDisplayGUI() {
		return personalDisplayGUI;
	}

	public void setPersonalDisplayGUI(VBox personalDisplayGUI) {
		this.personalDisplayGUI = personalDisplayGUI;
	}
}
