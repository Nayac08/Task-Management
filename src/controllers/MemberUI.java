package controllers;

import java.io.IOException;

import app.Main;
import enums.MemberPopupMode;
import enums.RoleMember;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import models.Member;
import models.TeamCard;

public class MemberUI {
	private ModalPopupMemberUI modalPopupMemberUIOwner = null;
	private ModalPopupSelectMemberUI modalPopupSelectMemberUI = null;
	private ModalPopupCardUI modalPopupCardUIOwner = null;
	private MemberPopupMode memberPopupMode;
	private Member member;
	
	@FXML private Text memberName;
	@FXML private Text memberRole;
	@FXML private Button selectButton;
	@FXML private Button deselectedButton;
	@FXML private Button deleteButton;
	@FXML private HBox MemberGUI;
	
	public MemberUI(Member member, ModalPopupMemberUI modalPopupMemberUI) {
		setMember(member);
		setMemberPopupMode(MemberPopupMode.CRUD);
		setModalPopupMemberUIOwner(modalPopupMemberUI);
		loadInitialFXML();
	}
	
	public MemberUI(Member member, ModalPopupCardUI modalPopupCardUI, ModalPopupSelectMemberUI modalPopupSelectMemberUI) {
		setMember(member);
		setMemberPopupMode(MemberPopupMode.Select_Member);
		setModalPopupSelectMemberUI(modalPopupSelectMemberUI);
		setModalPopupCardUIOwner(modalPopupCardUI);
		loadInitialFXML();
	}

	public void loadInitialFXML(){
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Member.fxml"));
            loader.setController(this);
            setMemberGUI(loader.load());
            	updateGUI();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	public void updateGUI() {
		memberName.setText(member.getName());
		if (member.getRole() == RoleMember.Project_Manager) {
			memberRole.setText("Role Project Manager");
		} else if (member.getRole() == RoleMember.Developer) {
			memberRole.setText("Role Developer");
		} else if (member.getRole() == RoleMember.Designer) {
			memberRole.setText("Role Designer");
		} else if (member.getRole() == RoleMember.QA_Tester) {
			memberRole.setText("Role QA Tester");
		} else if (member.getRole() == RoleMember.Intern) {
			memberRole.setText("Role Intern");
		}
		
		if (memberPopupMode == MemberPopupMode.CRUD) {
			handleShowDeleteButton();
			handleHideSelectButton();
			handleHideDeselectedButton();
		} else if (memberPopupMode == MemberPopupMode.Select_Member) {
			if (((TeamCard)modalPopupCardUIOwner.getCardOwner()).isContainMember(member.getId())) {
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
	
	// Select Member mode
	public void handleSelectMember() {
		((TeamCard)modalPopupCardUIOwner.getCardOwner()).addMember(member);
		modalPopupCardUIOwner.updateGUI();
		updateGUI();
	}
	
	public void handleDeselectMember() {
		((TeamCard)modalPopupCardUIOwner.getCardOwner()).removeMember(member.getId());
		modalPopupCardUIOwner.updateGUI();
		updateGUI();
	}
	
	// CRUD Member
	public void handleDeleteMember() {
		member.getTeamDisplayOwner().removeMember(member.getId());
		modalPopupMemberUIOwner.updateGUI();
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

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public HBox getMemberGUI() {
		return MemberGUI;
	}

	public void setMemberGUI(HBox memberGUI) {
		MemberGUI = memberGUI;
	}

	public ModalPopupMemberUI getModalPopupMemberUIOwner() {
		return modalPopupMemberUIOwner;
	}

	public void setModalPopupMemberUIOwner(ModalPopupMemberUI modalPopupMemberUIOwner) {
		this.modalPopupMemberUIOwner = modalPopupMemberUIOwner;
	}

	public ModalPopupCardUI getModalPopupCardUIOwner() {
		return modalPopupCardUIOwner;
	}

	public void setModalPopupCardUIOwner(ModalPopupCardUI modalPopupCardUIOwner) {
		this.modalPopupCardUIOwner = modalPopupCardUIOwner;
	}
	
	public ModalPopupSelectMemberUI getModalPopupSelectMemberUI() {
		return modalPopupSelectMemberUI;
	}

	public void setModalPopupSelectMemberUI(ModalPopupSelectMemberUI modalPopupSelectMemberUI) {
		this.modalPopupSelectMemberUI = modalPopupSelectMemberUI;
	}

	public MemberPopupMode getMemberPopupMode() {
		return memberPopupMode;
	}

	public void setMemberPopupMode(MemberPopupMode memberPopupMode) {
		this.memberPopupMode = memberPopupMode;
	}
}
