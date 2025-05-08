package controllers;

import java.io.IOException;

import app.Main;
import enums.PopupMode;
import enums.RoleMember;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import models.Label;
import models.Member;
import models.PersonalCard;
import models.TeamCard;

public class LabelUI {
	private ModalPopupLabelUI modalPopupLabelUIOwner = null;
	private ModalPopupSelectLabelUI modalPopupSelectLabelUI = null;
	private ModalPopupCardUI modalPopupCardUIOwner = null;
	private PopupMode labelPopupMode;
	private Label label;
	
	@FXML private Text labelName;
	@FXML private Rectangle labelColor;
	@FXML private Button selectButton;
	@FXML private Button deselectedButton;
	@FXML private Button deleteButton;
	@FXML private HBox labelBox;
	@FXML private HBox labelGUI;
	
	public LabelUI(Label label, ModalPopupLabelUI modalPopupLabelUI) {
		setLabel(label);
		setLabelPopupMode(PopupMode.CRUD);
		setModalPopupLabelUIOwner(modalPopupLabelUI);
		loadInitialFXML();
	}
	
	public LabelUI(Label label, ModalPopupCardUI modalPopupCardUI, ModalPopupSelectLabelUI modalPopupSelectLabelUI) {
		setLabel(label);
		setLabelPopupMode(PopupMode.Select);
		setModalPopupSelectLabelUI(modalPopupSelectLabelUI);
		setModalPopupCardUIOwner(modalPopupCardUI);
		loadInitialFXML();
	}

	public void loadInitialFXML(){
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Label.fxml"));
            loader.setController(this);
            setLabelGUI(loader.load());
            	updateGUI();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	public void updateGUI() {
		labelName.setText(label.getTitle());
		labelColor.setFill(label.getColor());
		
		if (labelPopupMode == PopupMode.CRUD) {
			handleShowDeleteButton();
			handleHideSelectButton();
			handleHideDeselectedButton();
		} else if (labelPopupMode == PopupMode.Select) {
			if (((PersonalCard)modalPopupCardUIOwner.getCardOwner()).isContainLabel(label.getId())) {
				handleHideDeleteButton();
				handleHideSelectButton();
				handleShowDeselectedButton();
			} else {
				handleHideDeleteButton();
				handleHideDeselectedButton();
				handleShowSelectButton();
			}
			
		}
	}
	
	// Select Label mode
	public void handleSelectLabel() {
		((PersonalCard)modalPopupCardUIOwner.getCardOwner()).addLabel(label);
		modalPopupCardUIOwner.updateGUI();
		Main.mainInterfaceUI.updateGUI();
		updateGUI();
	}
	
	public void handleDeselectLabel() {
		((PersonalCard)modalPopupCardUIOwner.getCardOwner()).removeLabel(label.getId());
		modalPopupCardUIOwner.updateGUI();
		Main.mainInterfaceUI.updateGUI();
		updateGUI();
	}
	
	// CRUD Label
	public void handleDeleteLabel() {
		label.getPersonalDisplay().removeLabel(label.getId());
		modalPopupLabelUIOwner.updateGUI();
	}
	
	public void handleHideDeleteButton() {
		deleteButton.setVisible(false);
		deleteButton.setManaged(false);
	}
	
	public void handleShowDeleteButton() {
		deleteButton.setVisible(true);
		deleteButton.setManaged(true);
	}
	
	public void handleHideSelectButton() {
		selectButton.setVisible(false);
		selectButton.setManaged(false);
	}
	
	public void handleShowSelectButton() {
		selectButton.setVisible(true);
		selectButton.setManaged(true);
	}
	
	public void handleHideDeselectedButton() {
		deselectedButton.setVisible(false);
		deselectedButton.setManaged(false);
	}
	
	public void handleShowDeselectedButton() {
		deselectedButton.setVisible(true);
		deselectedButton.setManaged(true);
	}

	public ModalPopupLabelUI getModalPopupLabelUIOwner() {
		return modalPopupLabelUIOwner;
	}

	public void setModalPopupLabelUIOwner(ModalPopupLabelUI modalPopupLabelUIOwner) {
		this.modalPopupLabelUIOwner = modalPopupLabelUIOwner;
	}

	public ModalPopupSelectLabelUI getModalPopupSelectLabelUI() {
		return modalPopupSelectLabelUI;
	}

	public void setModalPopupSelectLabelUI(ModalPopupSelectLabelUI modalPopupSelectLabelUI) {
		this.modalPopupSelectLabelUI = modalPopupSelectLabelUI;
	}

	public ModalPopupCardUI getModalPopupCardUIOwner() {
		return modalPopupCardUIOwner;
	}

	public void setModalPopupCardUIOwner(ModalPopupCardUI modalPopupCardUIOwner) {
		this.modalPopupCardUIOwner = modalPopupCardUIOwner;
	}

	public PopupMode getLabelPopupMode() {
		return labelPopupMode;
	}

	public void setLabelPopupMode(PopupMode labelPopupMode) {
		this.labelPopupMode = labelPopupMode;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public HBox getLabelGUI() {
		return labelGUI;
	}

	public void setLabelGUI(HBox labelGUI) {
		this.labelGUI = labelGUI;
	}
}
