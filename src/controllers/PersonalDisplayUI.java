package controllers;

import java.io.IOException;

import app.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
	}

    public void loadInitialFXML(){
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/PersonalDisplay.fxml"));
            loader.setController(this);
            setPersonalDisplayGUI(loader.load());
            	if (personalDisplay.getNodeLists().size() < 3) {
            		personalDisplay.addNodeList(new NodeList(personalDisplay.getIdxListNode(), personalDisplay, "Study"));
                	personalDisplay.addNodeList(new NodeList(personalDisplay.getIdxListNode(), personalDisplay, "Work"));
                	personalDisplay.addNodeList(new NodeList(personalDisplay.getIdxListNode(), personalDisplay, "Health"));
            	}
            	displayName.setText(personalDisplay.getName());
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
    			personalNodeListUI.getPersonalNodeListGUI().getChildren().getFirst().setStyle("-fx-background-color: #A179F2;");
    		} else if (nodeList.getTitle().equals("Work")) {
   			 	personalNodeListUI.getPersonalNodeListGUI().getChildren().getFirst().setStyle("-fx-background-color: #4170FF;");
    		} else if (nodeList.getTitle().equals("Health")) {
   			 	personalNodeListUI.getPersonalNodeListGUI().getChildren().getFirst().setStyle("-fx-background-color: #01ABFE;");
    		}
    		personalNodeListUI.updateGUI();
    		displayZone.getChildren().add(personalNodeListUI.getPersonalNodeListGUI());
    	}
    }

    public void handleShowModalPopupLabel() {
    	ModalPopupLabelUI modalPopupLabelUI = new ModalPopupLabelUI(personalDisplay);

    	Stage popupStage = new Stage();
        popupStage.setScene(new Scene(modalPopupLabelUI.getModalPopupLabelGUI()));
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setResizable(false);
        popupStage.show();
    }

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
