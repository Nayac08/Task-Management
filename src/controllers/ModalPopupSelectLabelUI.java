package controllers;

import java.io.IOException;

import enums.RoleMember;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Label;
import models.Member;
import models.PersonalDisplay;
import models.TeamCard;
import models.TeamDisplay;

public class ModalPopupSelectLabelUI {
	private ModalPopupCardUI modalPopupCardUIOwner;
	private PersonalDisplay personalDisplayOwner;
	
	@FXML private Button closePopupButton;
	@FXML private VBox labelContainer;
	@FXML private VBox modalPopupLabelGUI;

	public ModalPopupSelectLabelUI(ModalPopupCardUI modalPopupCardUI, PersonalDisplay personalDisplayOwner) {
		setModalPopupCardUIOwner(modalPopupCardUI);
		setPersonalDisplayOwner(personalDisplayOwner);
		loadInitialFXML();
	}

	public void loadInitialFXML(){
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModalPopupSelectLabel.fxml"));
            loader.setController(this);
            setModalPopupLabelGUI(loader.load());

            closePopupButton.setOnAction(e -> {
                Stage stage = (Stage) closePopupButton.getScene().getWindow();
                stage.close();
            });

            	updateGUI();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	public void updateGUI() {
		labelContainer.getChildren().clear();
		for (Label label: personalDisplayOwner.getLabels()) {
			LabelUI labelUI = new LabelUI(label, modalPopupCardUIOwner, this);
			labelContainer.getChildren().add(labelUI.getLabelGUI());
		}
	}

	public ModalPopupCardUI getModalPopupCardUIOwner() {
		return modalPopupCardUIOwner;
	}

	public void setModalPopupCardUIOwner(ModalPopupCardUI modalPopupCardUIOwner) {
		this.modalPopupCardUIOwner = modalPopupCardUIOwner;
	}

	public VBox getModalPopupLabelGUI() {
		return modalPopupLabelGUI;
	}

	public void setModalPopupLabelGUI(VBox modalPopupLabelGUI) {
		this.modalPopupLabelGUI = modalPopupLabelGUI;
	}

	public PersonalDisplay getPersonalDisplayOwner() {
		return personalDisplayOwner;
	}

	public void setPersonalDisplayOwner(PersonalDisplay personalDisplayOwner) {
		this.personalDisplayOwner = personalDisplayOwner;
	}
}
