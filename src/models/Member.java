package models;

import org.json.JSONObject;

import enums.RoleMember;
import interfaces.Exportable;

public class Member implements Exportable{
    private int id;
    private TeamDisplay teamDisplayOwner;
    private String name;
    private RoleMember role;

    public Member(int id, TeamDisplay teamDisplayOwner, String name, RoleMember role) {
    	this.id = id;
    	setTeamDisplayOwner(teamDisplayOwner);
    	setName(name);
    	setRole(role);
    }

	public int getId() {
		return id;
	}

	public TeamDisplay getTeamDisplayOwner() {
		return teamDisplayOwner;
	}

	public void setTeamDisplayOwner(TeamDisplay teamDisplayOwner) {
		this.teamDisplayOwner = teamDisplayOwner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RoleMember getRole() {
		return role;
	}

	public void setRole(RoleMember role) {
		this.role = role;
	}

	public String getStringRole() {
		if (role == RoleMember.Project_Manager) {
			return "Project Manager";
		} else if (role == RoleMember.Developer) {
			return "Developer";
		} else if (role == RoleMember.Designer) {
			return "Designer";
		} else if (role == RoleMember.QA_Tester) {
			return "QA Tester";
		} else if (role == RoleMember.Intern) {
			return "Intern";
		}
		return null;
	}

	@Override
	public JSONObject getJsonObject() {
		JSONObject memberJsonObject = new JSONObject();
		memberJsonObject.put("id", id);
		memberJsonObject.put("name", name);
		if (role == RoleMember.Project_Manager) {
			memberJsonObject.put("role", "Project_Manager");
		} else if (role == RoleMember.Developer) {
			memberJsonObject.put("role", "Developer");
		} else if (role == RoleMember.Designer) {
			memberJsonObject.put("role", "Designer");
		} else if (role == RoleMember.QA_Tester) {
			memberJsonObject.put("role", "QA_Tester");
		} else if (role == RoleMember.Intern) {
			memberJsonObject.put("role", "Intern");
		}

		return memberJsonObject;
	}
}
