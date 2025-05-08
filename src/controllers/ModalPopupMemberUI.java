package controllers;

import java.io.IOException;

import enums.MemberPopupMode;
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
import models.Member;
import models.TeamCard;
import models.TeamDisplay;

public class ModalPopupMemberUI {
	private TeamDisplay teamDisplayOwner;
	
	@FXML private VBox mainVBox;
	@FXML private Text header;
	@FXML private TextField textFieldNewMember;
	@FXML private ComboBox<String> roleNewMemberBox;
	@FXML private Button closePopupButton;
	@FXML private VBox memberContainer;
	@FXML private Text warningMemberName;
	@FXML private Text warningMemberRole;
	@FXML private VBox modalPopupMemberGUI;

	public ModalPopupMemberUI(TeamDisplay teamDisplayable) {
		setTeamDisplayOwner(teamDisplayable);
		loadInitialFXML();
	}

	public void loadInitialFXML(){
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModalPopupMember.fxml"));
            loader.setController(this);
            setModalPopupMemberGUI(loader.load());

            closePopupButton.setOnAction(e -> {
                Stage stage = (Stage) closePopupButton.getScene().getWindow();
                stage.close();
            });
            
            	handleHideWarningMemberName();
            	handleHideWarningMemberRole();

            	updateGUI();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	public void updateGUI() {
		memberContainer.getChildren().clear();
		for (Member member: teamDisplayOwner.getMembers()) {
			MemberUI memberUI = new MemberUI(member , this);
			memberContainer.getChildren().add(memberUI.getMemberGUI());
		}
	}
	
	public void handleAddNewMember() {
		if (textFieldNewMember.getText().trim().equals("")) {
			new Thread(() -> {
				Platform.runLater(() -> {
					handleHideWarningMemberRole();
					handleShowWarningMemberName();
				});
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Platform.runLater(() -> {
					handleHideWarningMemberName();
				});
			}).start();
		} else if (roleNewMemberBox.getValue() == null) {
			new Thread(() -> {
				Platform.runLater(() -> {
					handleHideWarningMemberName();
					handleShowWarningMemberRole();
				});
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Platform.runLater(() -> {
					handleHideWarningMemberRole();
				});
			}).start();
		} else {
			handleHideWarningMemberName();
			handleHideWarningMemberRole();
			Member member = null;
			RoleMember roleMember = null;
			if (roleNewMemberBox.getValue().equals("Project Manager")) {
				roleMember = RoleMember.Project_Manager;
			} else if (roleNewMemberBox.getValue().equals("Developer")) {
				roleMember = RoleMember.Developer;
			} else if (roleNewMemberBox.getValue().equals("Designer")) {
				roleMember = RoleMember.Designer;
			} else if (roleNewMemberBox.getValue().equals("QA Tester")) {
				roleMember = RoleMember.QA_Tester;
			} else if (roleNewMemberBox.getValue().equals("Intern")) {
				roleMember = RoleMember.Intern;
			}
			member = new Member(teamDisplayOwner.getIdxMember(), teamDisplayOwner, textFieldNewMember.getText(), roleMember);
	 		teamDisplayOwner.addMember(member);
	 		textFieldNewMember.setText("");
	 		roleNewMemberBox.setValue(null);
	 		updateGUI();
		}	
	}
	
	public void handleHideWarningMemberName() {
		warningMemberName.setVisible(false);
		warningMemberName.setManaged(false);
	}
	
	public void handleShowWarningMemberName() {
		warningMemberName.setVisible(true);
		warningMemberName.setManaged(true);
	}
	
	public void handleHideWarningMemberRole() {
		warningMemberRole.setVisible(false);
		warningMemberRole.setManaged(false);
	}
	
	public void handleShowWarningMemberRole() {
		warningMemberRole.setVisible(true);
		warningMemberRole.setManaged(true);
	}

	public TeamDisplay getTeamDisplayOwner() {
		return teamDisplayOwner;
	}

	public void setTeamDisplayOwner(TeamDisplay teamDisplayOwner) {
		this.teamDisplayOwner = teamDisplayOwner;
	}

	public VBox getModalPopupMemberGUI() {
		return modalPopupMemberGUI;
	}

	public void setModalPopupMemberGUI(VBox modalPopupMemberGUI) {
		this.modalPopupMemberGUI = modalPopupMemberGUI;
	}
}
