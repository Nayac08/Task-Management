package controllers;

import java.io.IOException;
import java.time.LocalDate;

import app.Main;
import enums.CheckListViewMode;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Card;
import models.ChecklistItem;
import models.Member;
import models.PersonalCard;
import models.TeamCard;
import models.TeamDisplay;

public class ModalPopupCardUI {
	private Card cardOwner;

	// Card detail component
	@FXML private Label cardTitle;

	@FXML private DatePicker datePicker;
	@FXML private Button saveDateButton;
	@FXML private Button cancelDateButton;
	@FXML private Button editDateButton;

	@FXML private TextArea descriptionDetail;
	@FXML private Button saveDescriptionButton;
	@FXML private Button editDescriptionButton;
	@FXML private Button cancelDescriptionButton;

	@FXML private ProgressBar progressCheckListBar;
	@FXML private Text progressCheckListPercentage;
	@FXML private Button editCheckListButton;
	@FXML private VBox checkListContainer;
	@FXML private HBox addCheckListArea;
	@FXML private TextField textFieldNewCheckList;
	
	// for team
	@FXML private VBox memberZone;
	@FXML private VBox memberContainer;


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

            closePopupButton.setOnAction(e -> {
                Stage stage = (Stage) closePopupButton.getScene().getWindow();
                stage.close();
            });

            	datePicker.setFocusTraversable(false);
        	datePicker.setDisable(true);
        	handleHideSaveDateButton();
        	handleHideCancelDateButton();

            	descriptionDetail.setFocusTraversable(false);
            	handleHideSaveDescriptionButton();
            	handleHideCancelDescriptionButton();
            	handleHideAddCheckListArea();
            	
            	if (cardOwner instanceof TeamCard) {
            		memberZone.setVisible(true);
            		memberZone.setManaged(true); 
            	} else if (cardOwner instanceof PersonalCard) {
            		memberZone.setVisible(false);
            		memberZone.setManaged(false); 
            	}

            	updateGUI();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	public void updateGUI() {
		cardTitle.setText(cardOwner.getTitle());
		datePicker.setValue(cardOwner.getDate());
    	descriptionDetail.setText(cardOwner.getDescription());
        updateGUIChecklist(CheckListViewMode.View);
    	// for team
    	updateGUIMember();
	}
	
	public void updateGUIChecklist(CheckListViewMode checkListViewMode) {
		checkListContainer.getChildren().clear();
    	for (ChecklistItem checklistItem: cardOwner.getChecklists()) {
            CheckBox checkBox = new CheckBox(checklistItem.getTitle());
            checkBox.setUserData(checklistItem.getId());
            checkBox.setMnemonicParsing(false);
            checkBox.setFont(Font.font("Ekkamai New Bold", 14));
            checkBox.setPrefWidth(330);
            checkBox.setOnAction((e) -> {
    			cardOwner.getChecklist((int) checkBox.getUserData()).toggleChecked();
    			updateGUI();
    			Main.mainInterfaceUI.updateGUI();
    		});
    		if (checklistItem.isChecked()) {
    			checkBox.setSelected(true);
    		}
    		Button closeButton = new Button("X");
            closeButton.setFont(Font.font("Ekkamai New Bold", 12));
            closeButton.setStyle("-fx-background-color: none;");
            	closeButton.setOnAction((e) -> {
            		cardOwner.removeChecklist(checklistItem.getId());
            		Main.mainInterfaceUI.updateGUI();
            		updateGUI();
            	});

            HBox hBox = new HBox(checkBox, closeButton);
            hBox.setAlignment(Pos.CENTER_LEFT);
            hBox.setPrefSize(364, 22);
            checkListContainer.getChildren().add(hBox);
    		
    		if (checkListViewMode == CheckListViewMode.Edit) {
        		closeButton.setVisible(true);
        		closeButton.setManaged(true);
    		} else if (checkListViewMode == CheckListViewMode.View) {
    			closeButton.setVisible(false);
    			closeButton.setManaged(false);
    		}
            
    	}

    	double checkListPercentage = cardOwner.getChecklistPercentage();
    	progressCheckListBar.setProgress(checkListPercentage);
    	progressCheckListPercentage.setText((int)(checkListPercentage * 100) + " %");
    	
	}
	
	// for team
	public void updateGUIMember() {
		if (cardOwner instanceof TeamCard) {
    		memberContainer.getChildren().clear();
    		for (int i=0; i<((TeamCard)cardOwner).getMembers().size();i++) {
    			
    			Label memberOrder = new Label((i+1) + ".");
    	        memberOrder.setPrefSize(339, 17);
    	        memberOrder.setFont(Font.font("Ekkamai New Bold", 14));
    	        StackPane orderPane = new StackPane(memberOrder);
    	        orderPane.setAlignment(Pos.CENTER_LEFT);
    	        orderPane.setPrefSize(23, 24);

    	        Label memberName = new Label(((TeamCard)cardOwner).getMembers().get(i).getName());
    	        memberName.setPrefSize(170, 17);
    	        memberName.setFont(Font.font("Ekkamai New Bold", 14));
    	        StackPane namePane = new StackPane(memberName);
    	        namePane.setAlignment(Pos.CENTER_LEFT);
    	        namePane.setPrefSize(200, 17);

    	        Label memberRole = new Label("Role " + ((TeamCard)cardOwner).getMembers().get(i).getStringRole());
    	        memberRole.setPrefSize(140, 17);
    	        memberRole.setFont(Font.font("Ekkamai New Bold", 14));
    	        StackPane rolePane = new StackPane(memberRole);
    	        rolePane.setAlignment(Pos.CENTER_LEFT);
    	        rolePane.setPrefSize(140, 17);

    	        HBox hBox = new HBox(orderPane, namePane, rolePane);
    	        hBox.setPrefSize(364, 17);
    	        
    	        memberContainer.getChildren().add(hBox);
    		}
    	}
	}
	
	// for team
	public void handleShowEditMemberPopup() {
		if (cardOwner instanceof TeamCard) {
			ModalPopupSelectMemberUI modalPopupSelectMemberUI = new ModalPopupSelectMemberUI(this, (TeamDisplay) cardOwner.getNodeListOwner().getDisplayOwner());

	    	Stage popupStage = new Stage();
	        popupStage.setScene(new Scene(modalPopupSelectMemberUI.getModalPopupMemberGUI()));
	        popupStage.initModality(Modality.APPLICATION_MODAL);
	        popupStage.setResizable(false);
	        popupStage.show();
		}  	
    }

	public void handleSaveDate() {
		LocalDate date = datePicker.getValue();
		cardOwner.setDate(date);
		updateGUI();
		Main.mainInterfaceUI.updateGUI();

		handleShowEditDateButton();
		datePicker.setDisable(true);
		handleHideSaveDateButton();
		handleHideCancelDateButton();
	}

	public void handleCancelEditDateMode() {
		updateGUI();
		handleShowEditDateButton();
		datePicker.setDisable(true);
		handleHideSaveDateButton();
		handleHideCancelDateButton();
	}

	public void handleEditDate() {
		handleHideEditDateButton();
		datePicker.setDisable(false);
		handleShowSaveDateButton();
		handleShowCancelDateButton();
	}

	public void handleEditDescriptionMode() {
		descriptionDetail.setEditable(true);
		handleHideEditDescriptionButton();
		handleShowSaveDescriptionButton();
		handleShowCancelDescriptionButton();
	}

	public void handleSaveDescription() {
		cardOwner.setDescription(descriptionDetail.getText());
		descriptionDetail.setEditable(false);
		updateGUI();
		Main.mainInterfaceUI.updateGUI();

		handleHideSaveDescriptionButton();
		handleHideCancelDescriptionButton();
		handleShowEditDescriptionButton();
	}

	public void handleCancelEditDescriptionMode() {
		descriptionDetail.setEditable(false);
		updateGUI();

		handleHideSaveDescriptionButton();
		handleHideCancelDescriptionButton();
		handleShowEditDescriptionButton();
	}

	public void handleEditCheckListMode() {
		updateGUIChecklist(CheckListViewMode.Edit);
		handleHideEditCheckListButton();
		handleShowAddCheckListArea();
	}

	public void handleCancelEditCheckListMode() {
		updateGUIChecklist(CheckListViewMode.View);
		handleShowEditCheckListButton();
		handleHideAddCheckListArea();
	}

	public void handleAddCheckList() {
		ChecklistItem checklistItem = new ChecklistItem(cardOwner.getIdxChecklists(), textFieldNewCheckList.getText());
		textFieldNewCheckList.setText("");
		cardOwner.addChecklist(checklistItem);
		Main.mainInterfaceUI.updateGUI();
		updateGUI();
		handleShowEditCheckListButton();
		handleHideAddCheckListArea();
	}
	
	public void handleHideSaveDateButton() {
		saveDateButton.setVisible(false);
		saveDateButton.setManaged(false);
	}
	
	public void handleShowSaveDateButton() {
		saveDateButton.setVisible(true);
		saveDateButton.setManaged(true);
	}
	
	public void handleHideEditDateButton() {
		editDateButton.setVisible(false);
		editDateButton.setManaged(false);
	}
	
	public void handleShowEditDateButton() {
		editDateButton.setVisible(true);
		editDateButton.setManaged(true);
	}
	
	public void handleHideCancelDateButton() {
		cancelDateButton.setVisible(false);
		cancelDateButton.setManaged(false);
	}
	
	public void handleShowCancelDateButton() {
		cancelDateButton.setVisible(true);
		cancelDateButton.setManaged(true);
	}
	
	public void handleHideSaveDescriptionButton() {
		saveDescriptionButton.setVisible(false);
		saveDescriptionButton.setManaged(false);
	}
	
	public void handleShowSaveDescriptionButton() {
		saveDescriptionButton.setVisible(true);
		saveDescriptionButton.setManaged(true);
	}
	
	public void handleHideEditDescriptionButton() {
		editDescriptionButton.setVisible(false);
		editDescriptionButton.setManaged(false);
	}
	
	public void handleShowEditDescriptionButton() {
		editDescriptionButton.setVisible(true);
		editDescriptionButton.setManaged(true);
	}
	
	public void handleHideCancelDescriptionButton() {
		cancelDescriptionButton.setVisible(false);
		cancelDescriptionButton.setManaged(false);
	}
	
	public void handleShowCancelDescriptionButton() {
		cancelDescriptionButton.setVisible(true);
		cancelDescriptionButton.setManaged(true);
	}
	
	public void handleHideEditCheckListButton() {
		editCheckListButton.setVisible(false);
		editCheckListButton.setManaged(false);
	}
	
	public void handleShowEditCheckListButton() {
		editCheckListButton.setVisible(true);
		editCheckListButton.setManaged(true);
	}
	
	public void handleHideAddCheckListArea() {
		addCheckListArea.setVisible(false);
		addCheckListArea.setManaged(false);
	}
	
	public void handleShowAddCheckListArea() {
		addCheckListArea.setVisible(true);
		addCheckListArea.setManaged(true);
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
