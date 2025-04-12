package controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import models.PersonalCard;

public class PersonalCardUI{
	private PersonalCard personalCard;

	@FXML private StackPane personalCardGUI;

	public PersonalCardUI(PersonalCard personalCard) {
		setPersonalCard(personalCard);
		loadInitialFXML();
	}

    public void loadInitialFXML(){
    }

    public void updateGUI() {
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