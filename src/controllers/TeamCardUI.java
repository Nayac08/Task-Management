package controllers;

import java.io.IOException;

import app.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Card;
import models.TeamCard;

public class TeamCardUI{
	private TeamCard teamCard;

	@FXML private StackPane teamCardGUI;
	@FXML private Label title;
	@FXML private Text dateText;
	@FXML private Text checklistStat;
	@FXML private Text memberCount;
	@FXML private CheckBox checkBox;

	public TeamCardUI(TeamCard teamCard) {
		setTeamCard(teamCard);
		loadInitialFXML();
	}

    public void loadInitialFXML(){
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/TeamCard.fxml"));
            loader.setController(this);
            setTeamCardGUI(loader.load());
            	updateGUI();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public void updateGUI() {
    	title.setText(teamCard.getTitle());
    	if (teamCard.getDate() != null) {
        	dateText.setText("Date " + teamCard.getDate().toString());
    	} else {
    		dateText.setText("");
    	}
    	memberCount.setText(Integer.toString(teamCard.getMembers().size()));
    	checklistStat.setText(teamCard.getNumberOfCheckedChecklist() + "/" + teamCard.getChecklists().size());
    }

    public void handleDeleteCard() {
    	for (Card card: teamCard.getNodeListOwner().getCards()) {
    		if (card.getId() == teamCard.getId()) {
    			teamCard.getNodeListOwner().getCards().remove(card);
    			Main.mainInterfaceUI.updateGUI();
    			break;
    		}
    	}
    }

    public void handleModalPopupCard() {
    	ModalPopupCardUI modalPopupCardUI = new ModalPopupCardUI(teamCard);

    	Stage popupStage = new Stage();
        popupStage.setScene(new Scene(modalPopupCardUI.getModalPopupCardGUI()));
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setResizable(false);
        popupStage.show();
    }

	public TeamCard getTeamCard() {
		return teamCard;
	}

	public void setTeamCard(TeamCard teamCard) {
		this.teamCard = teamCard;
	}

	public StackPane getTeamCardGUI() {
		return teamCardGUI;
	}

	public void setTeamCardGUI(StackPane teamCardGUI) {
		this.teamCardGUI = teamCardGUI;
	}
}
