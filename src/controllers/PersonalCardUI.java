package controllers;

import java.io.IOException;

import app.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Card;
import models.PersonalCard;

public class PersonalCardUI{
	private PersonalCard personalCard;

	@FXML private VBox personalCardGUI;
	@FXML private Label title;
	@FXML private Text date;
	@FXML private Text checklistStat;
	@FXML private HBox labelContainer;
	@FXML private Label description;

	public PersonalCardUI(PersonalCard personalCard) {
		setPersonalCard(personalCard);
		loadInitialFXML();
	}

    public void loadInitialFXML(){
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/PersonalCard.fxml"));
            loader.setController(this);
            setPersonalCardGUI(loader.load());
            	title.setText(personalCard.getTitle());
            	if (personalCard.getDate() != null) {
                	date.setText("Date " + personalCard.getDate().toString());
            	} else {
            		date.setText("Date ");
            	}
            	checklistStat.setText(personalCard.getNumberOfCheckedChecklist() + "/" + personalCard.getChecklists().size());
            	description.setText(personalCard.getDescription());
            	
            	labelContainer.getChildren().clear();
    			int maxWidthContainer = 140;
    			int labelCount = personalCard.getLabels().size();
    			double widthPerRectangle = (double) maxWidthContainer/labelCount;
    			for (models.Label label: personalCard.getLabels()) {
    				Rectangle rectangle = new Rectangle(widthPerRectangle, 10);
    				rectangle.setArcWidth(5.0);
    				rectangle.setArcHeight(5.0);
    				rectangle.setFill(label.getColor());
    				rectangle.setStrokeWidth(0);
    				labelContainer.getChildren().add(rectangle);
    			}	
    	} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public void updateGUI() {
    	loadInitialFXML();
    }

    public void handleDeleteCard() {
    	for (Card card: personalCard.getNodeListOwner().getCards()) {
    		if (card.getId() == personalCard.getId()) {
    			personalCard.getNodeListOwner().getCards().remove(card);
    			Main.mainInterfaceUI.updateGUI();
    			break;
    		}
    	}
    }

    public void handleModalPopupCard() {
    	ModalPopupCardUI modalPopupCardUI = new ModalPopupCardUI(personalCard);

    	Stage popupStage = new Stage();
        popupStage.setScene(new Scene(modalPopupCardUI.getModalPopupCardGUI()));
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setResizable(false);
        popupStage.show();
    }

	public PersonalCard getPersonalCard() {
		return personalCard;
	}

	public void setPersonalCard(PersonalCard personalCard) {
		this.personalCard = personalCard;
	}

	public VBox getPersonalCardGUI() {
		return personalCardGUI;
	}

	public void setPersonalCardGUI(VBox personalCardGUI) {
		this.personalCardGUI = personalCardGUI;
	}
}