package controllers;

import java.io.IOException;

import app.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Board;
import models.NodeList;
import models.PersonalCard;

public class PersonalCardUI{
	private PersonalCard personalCard;
	
	@FXML private StackPane personalCardGUI;	
	@FXML private Text title;
	
	public PersonalCardUI(PersonalCard personalCard) {
		this.personalCard = personalCard;
		loadInitialFXML();
	}
	
    public void loadInitialFXML(){
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/PersonalCard.fxml"));
            loader.setController(this);
            this.personalCardGUI = loader.load();
            	title.setText(personalCard.getTitle());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void updateGUI() {
    	loadInitialFXML();
    }
	
    public void initializePersonalCard(PersonalCard card){

    }

    public void addChecklistToCard(PersonalCard card){

    }

    public void removeChecklistFromCard(PersonalCard card){

    }

    public void updateChecklistPercentage(PersonalCard card){
        
    }

	public PersonalCard getPersonalCard() {
		return personalCard;
	}

	public void setPersonalCard(PersonalCard personalCard) {
		this.personalCard = personalCard;
	}

	public StackPane getPersonalCardGUI() {
		return personalCardGUI;
	}

	public void setPersonalCardGUI(StackPane personalCardGUI) {
		this.personalCardGUI = personalCardGUI;
	}  
}
