package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/PersonalDisplay.fxml"));
            loader.setController(this);
            setPersonalDisplayGUI(loader.load());
		} catch (IOException e) {
			e.printStackTrace();
		}
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
