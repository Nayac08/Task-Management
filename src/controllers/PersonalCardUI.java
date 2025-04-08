package controllers;

import java.io.IOException;

import app.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import models.Card;
import models.PersonalCard;

public class PersonalCardUI{
	private PersonalCard personalCard;
	
	@FXML private StackPane personalCardGUI;	
	@FXML private Text title;
	
	public PersonalCardUI(PersonalCard personalCard) {
		setPersonalCard(personalCard);
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
    
    @FXML
    public void handleDeleteCard() {
    	for (Card card: personalCard.getNodeListOwner().getCards()) {
    		if (card.getId() == personalCard.getId()) {
    			personalCard.getNodeListOwner().getCards().remove(card);
    			Main.boardUI.updateGUI();
    			break;
    		}
    	}
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
