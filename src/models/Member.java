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

	@Override
	public JSONObject getJsonObject() {
		// TODO Auto-generated method stub
		return null;
	}
}
