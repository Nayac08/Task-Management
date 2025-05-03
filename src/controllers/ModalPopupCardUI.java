package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Card;
import models.TaskFile;

public class ModalPopupCardUI {
	private Card cardOwner;
	
	// Card detail component
	@FXML private Text cardTitle;
	
	@FXML private Button closeButton;
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
//            closeButton.setOnAction(e -> {
//                Stage stage = (Stage) closeButton.getScene().getWindow();
//                stage.close();
//            });
            cardTitle.setText(cardOwner.getTitle());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@FXML
	public void handleSaveCardDetail() {
		
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
