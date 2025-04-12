package controllers;

import java.io.IOException;

import app.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import models.Card;
import models.PersonalCard;
import models.TeamCard;

public class TeamCardUI{
	private TeamCard teamCard;

	@FXML private StackPane teamCardGUI;
	@FXML private Text title;

	public TeamCardUI(TeamCard personalCard) {
		setTeamCard(personalCard);
		loadInitialFXML();
	}

    public void loadInitialFXML(){
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/TeamCard.fxml"));
            loader.setController(this);
            setTeamCardGUI(loader.load());
            	title.setText(teamCard.getTitle());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public void updateGUI() {
    	loadInitialFXML();
    }

    @FXML
    public void handleDeleteCard() {
    	for (Card card: teamCard.getNodeListOwner().getCards()) {
    		if (card.getId() == teamCard.getId()) {
    			teamCard.getNodeListOwner().getCards().remove(card);
    			Main.mainInterfaceUI.updateGUI();
    			break;
    		}
    	}
    }

    public void initializePersonalCard(PersonalCard card){

    }

    public void addChecklistToCard(PersonalCard card){

    }

    public void removeChecklistFromCard(PersonalCard card){

    }

    public void updateChecklistPercentage(PersonalCard card){

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
