package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Member;
import models.TeamDisplay;

public class ModalPopupSelectMemberUI {
	private ModalPopupCardUI modalPopupCardUIOwner;
	private TeamDisplay teamDisplayOwner;

	@FXML private Button closePopupButton;
	@FXML private VBox memberContainer;
	@FXML private VBox modalPopupMemberGUI;

	public ModalPopupSelectMemberUI(ModalPopupCardUI modalPopupCardUI, TeamDisplay teamDisplayOwner) {
		setModalPopupCardUIOwner(modalPopupCardUI);
		setTeamDisplayOwner(teamDisplayOwner);
		loadInitialFXML();
	}

	public void loadInitialFXML(){
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModalPopupSelectMember.fxml"));
            loader.setController(this);
            setModalPopupMemberGUI(loader.load());

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
		memberContainer.getChildren().clear();
		for (Member member: teamDisplayOwner.getMembers()) {
			MemberUI memberUI = new MemberUI(member, modalPopupCardUIOwner, this);

			memberContainer.getChildren().add(memberUI.getMemberGUI());
		}
	}

	public ModalPopupCardUI getModalPopupCardUIOwner() {
		return modalPopupCardUIOwner;
	}

	public void setModalPopupCardUIOwner(ModalPopupCardUI modalPopupCardUIOwner) {
		this.modalPopupCardUIOwner = modalPopupCardUIOwner;
	}

	public VBox getModalPopupMemberGUI() {
		return modalPopupMemberGUI;
	}

	public void setModalPopupMemberGUI(VBox modalPopupMemberGUI) {
		this.modalPopupMemberGUI = modalPopupMemberGUI;
	}

	public TeamDisplay getTeamDisplayOwner() {
		return teamDisplayOwner;
	}

	public void setTeamDisplayOwner(TeamDisplay teamDisplayOwner) {
		this.teamDisplayOwner = teamDisplayOwner;
	}
}
