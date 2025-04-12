package controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import models.PersonalDisplay;

public class PersonalDisplayUI {
	private PersonalDisplay personalDisplay;
	private int idxListNode = 0;

	@FXML private VBox personalDisplayGUI;

	public PersonalDisplayUI(PersonalDisplay personalDisplay) {
		setPersonalDisplay(personalDisplay);
		loadInitialFXML();
	}

    public void loadInitialFXML(){
    }

    public void updateGUI() {
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
