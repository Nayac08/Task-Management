package controllers;

import java.io.IOException;

import app.Main;
import enums.RoleMember;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import models.Member;

public class MemberUI {
	private ModalPopupMemberUI modalPopupMemberUIOwner;
	private Member member;
	
	@FXML private Text memberName;
	@FXML private Text memberRole;
	@FXML private HBox MemberGUI;
	
	public MemberUI(Member member, ModalPopupMemberUI modalPopupMemberUI) {
		setMember(member);
		setModalPopupMemberUIOwner(modalPopupMemberUI);
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
	}
	
	public void handleDeleteMember() {
		member.getTeamDisplayOwner().removeMember(member.getId());
		modalPopupMemberUIOwner.updateGUI();
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
}
