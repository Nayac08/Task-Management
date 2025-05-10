package controllers;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Label;
import models.PersonalDisplay;

public class ModalPopupLabelUI {
	private PersonalDisplay personalDisplayOwner;

	@FXML private Text header;
	@FXML private TextField textFieldNewLabel;
	@FXML private ColorPicker colorPicker;
	@FXML private Button closePopupButton;
	@FXML private VBox labelContainer;
	@FXML private Text warningLabelName;
	@FXML private Text warningLabelColor;
	@FXML private VBox modalPopupLabelGUI;

	public ModalPopupLabelUI(PersonalDisplay personalDisplay) {
		setPersonalDisplayOwner(personalDisplay);
		loadInitialFXML();
	}

	public void loadInitialFXML(){
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModalPopupLabel.fxml"));
            loader.setController(this);
            setModalPopupLabelGUI(loader.load());

            closePopupButton.setOnAction(e -> {
                Stage stage = (Stage) closePopupButton.getScene().getWindow();
                stage.close();
            });

            	handleHideWarningLabelName();
            	handleHideWarningLabelColor();
            	colorPicker.setValue(null);
            	updateGUI();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	public void updateGUI() {
		labelContainer.getChildren().clear();
		for (Label label: personalDisplayOwner.getLabels()) {
			LabelUI labelUI = new LabelUI(label, this);
			labelContainer.getChildren().add(labelUI.getLabelGUI());
		}
	}

	public void handleAddNewLabel() {
		if (textFieldNewLabel.getText().trim().equals("")) {
			new Thread(() -> {
				Platform.runLater(() -> {
					handleHideWarningLabelColor();
					handleShowWarningLabelName();
				});
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Platform.runLater(() -> {
					handleHideWarningLabelName();
				});
			}).start();
		} else if (colorPicker.getValue() == null) {
			new Thread(() -> {
				Platform.runLater(() -> {
					handleHideWarningLabelName();
					handleShowWarningLabelColor();
				});
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Platform.runLater(() -> {
					handleHideWarningLabelColor();
				});
			}).start();
		} else {
			handleHideWarningLabelColor();
			handleHideWarningLabelName();
			Label label = new Label(personalDisplayOwner.getIdxLabel(), personalDisplayOwner, textFieldNewLabel.getText(), colorPicker.getValue());
	 		personalDisplayOwner.addLabel(label);
	 		textFieldNewLabel.setText("");
	 		colorPicker.setValue(null);
	 		updateGUI();
		}
	}

	public void handleHideWarningLabelName() {
		warningLabelName.setVisible(false);
		warningLabelName.setManaged(false);
	}

	public void handleShowWarningLabelName() {
		warningLabelName.setVisible(true);
		warningLabelName.setManaged(true);
	}

	public void handleHideWarningLabelColor() {
		warningLabelColor.setVisible(false);
		warningLabelColor.setManaged(false);
	}

	public void handleShowWarningLabelColor() {
		warningLabelColor.setVisible(true);
		warningLabelColor.setManaged(true);
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
