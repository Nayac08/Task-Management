package controllers;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Card;
import models.ChecklistItem;
import models.TaskFile;

public class ModalPopupCardUI {
	private Card cardOwner;
	
	// Card detail component
	@FXML private Text cardTitle;
	
	@FXML private TextArea descriptionDetail;
	@FXML private Button saveDescriptionButton;
	@FXML private Button editDescriptionButton;
	@FXML private Button cancelDescriptionButton;
	
	@FXML private ProgressBar progressCheckListBar;
	@FXML private Text progressCheckListPercentage;
	@FXML private VBox checkListContainer;
	@FXML private HBox addCheckListArea;
	@FXML private TextField textFieldNewCheckList;
	
	
	@FXML private Button closePopupButton;
	@FXML private Pane modalPopupCardGUI;
	
	public ModalPopupCardUI(Card card) {
		setCardOwner(card);
		loadInitialFXML();
	}

	public void loadInitialFXML(){
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModalPopupCard.fxml"));
            loader.setController(this);
            setModalPopupCardGUI(loader.load());
            	
            	descriptionDetail.setOnMouseClicked((e) -> {
            		descriptionDetail.setFocusTraversable(false);
            	});
            
            closePopupButton.setOnAction(e -> {
                Stage stage = (Stage) closePopupButton.getScene().getWindow();
                stage.close();
            });
            	
            	descriptionDetail.setFocusTraversable(false);
            	saveDescriptionButton.setVisible(false);
            	saveDescriptionButton.setManaged(false);
            	cancelDescriptionButton.setVisible(false);
            	cancelDescriptionButton.setManaged(false);
            	addCheckListArea.setVisible(false);
            	addCheckListArea.setManaged(false);
            	updateGUI();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	public void updateGUI() {
		cardTitle.setText(cardOwner.getTitle());
    	descriptionDetail.setText(cardOwner.getDescription());
    	
        checkListContainer.getChildren().clear();
    	for (ChecklistItem checklistItem: cardOwner.getChecklists()) {
    		CheckBox checkBox = new CheckBox(checklistItem.getTitle());
    		checkBox.setUserData(checklistItem.getId());
    		checkBox.setMnemonicParsing(false);
    		checkBox.setFont(Font.font("Ekkamai New Bold", 14.0));
    		checkBox.setOnAction((e) -> {
    			cardOwner.getChecklist((int) checkBox.getUserData()).toggleChecked();
    			updateGUI();
    		});
    		if (checklistItem.isChecked()) {
    			checkBox.setSelected(true);
    		}
    		checkListContainer.getChildren().add(checkBox);
    	}
    	
    	double checkListPercentage = cardOwner.getChecklistPercentage();
    	progressCheckListBar.setProgress(checkListPercentage);
    	progressCheckListPercentage.setText((int)(checkListPercentage * 100) + " %");
	}
	
	
	@FXML
	public void handleEditDescriptionMode() {
		descriptionDetail.setEditable(true);
		
		editDescriptionButton.setVisible(false);
		editDescriptionButton.setManaged(false);
		
		saveDescriptionButton.setVisible(true);
    	saveDescriptionButton.setManaged(true);
    	cancelDescriptionButton.setVisible(true);
    	cancelDescriptionButton.setManaged(true);
	}
	
	@FXML
	public void handleSaveDescription() {
		cardOwner.setDescription(descriptionDetail.getText());
		descriptionDetail.setEditable(false);
		updateGUI();
		
		saveDescriptionButton.setVisible(false);
    	saveDescriptionButton.setManaged(false);
    	cancelDescriptionButton.setVisible(false);
    	cancelDescriptionButton.setManaged(false);
    	
    	editDescriptionButton.setVisible(true);
		editDescriptionButton.setManaged(true);
	}
	
	@FXML
	public void handleCancelEditDescriptionMode() {
		descriptionDetail.setEditable(false);
		updateGUI();
		
		saveDescriptionButton.setVisible(false);
    	saveDescriptionButton.setManaged(false);
    	cancelDescriptionButton.setVisible(false);
    	cancelDescriptionButton.setManaged(false);
    	
    	editDescriptionButton.setVisible(true);
		editDescriptionButton.setManaged(true);
	}
	
	@FXML
	public void handleEditCheckListMode() {
		addCheckListArea.setVisible(true);
		addCheckListArea.setManaged(true);
	}
	
	@FXML
	public void handleCancelEditCheckListMode() {
		addCheckListArea.setVisible(false);
		addCheckListArea.setManaged(false);
	}
	
	@FXML 
	public void handleAddCheckList() {
		ChecklistItem checklistItem = new ChecklistItem(cardOwner.getIdxChecklists(), textFieldNewCheckList.getText());
		textFieldNewCheckList.setText("");
		cardOwner.addChecklist(checklistItem);
		updateGUI();
		
		addCheckListArea.setVisible(false);
		addCheckListArea.setManaged(false);
	}
	
	public Card getCardOwner() {
		return cardOwner;
	}

	public void setCardOwner(Card cardOwner) {
		this.cardOwner = cardOwner;
	}

	public Pane getModalPopupCardGUI() {
		return modalPopupCardGUI;
	}

	public void setModalPopupCardGUI(Pane modalPopupCardGUI) {
		this.modalPopupCardGUI = modalPopupCardGUI;
	}
}
