package models;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class TeamCard extends Card{
	private int idxMember;
	private List<Member> members;

	public TeamCard(int id,NodeList nodeListOwner, String title) {
		super(id, nodeListOwner, title);
		setIdxMember(0);
		setMembers(new ArrayList<>());
	}

	// Members
	public void addMember(Member member) {
		members.add(member);
		setIdxMember(member.getId() + 1);
	}

	public void removeMember(int id) {
		for (int i=0;i<members.size();i++) {
    		if (members.get(i).getId() == id) {
    			members.remove(i);
    			break;
    		}
    	}
	}

	public boolean isContainMember(int id) {
		for (Member member : members) {
    		if (member.getId() == id) {
    			return true;
    		}
    	}
		return false;
	}

	public Member getMember(int id) {
		for (Member member : members) {
    		if (member.getId() == id) {
    			return member;
    		}
    	}
		return null;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	public int getIdxMember() {
		return idxMember;
	}

	public void setIdxMember(int idxMember) {
		this.idxMember = idxMember;
	}

	@Override
	public JSONObject getJsonObject() {
		JSONObject teamCardJsonObject = new JSONObject();
		teamCardJsonObject.put("id", id);
		teamCardJsonObject.put("title", title);
		teamCardJsonObject.put("date", date != null ? date.toString() : JSONObject.NULL);
		teamCardJsonObject.put("description", description);

		JSONArray checklistJsonArray = new JSONArray();
		for (ChecklistItem checklistItem: checklists) {
			checklistJsonArray.put(checklistItem.getJsonObject());
		}
		teamCardJsonObject.put("checklists", checklistJsonArray);

		JSONArray memberJsonArray = new JSONArray();
		for (Member member: members) {
			memberJsonArray.put(member.getJsonObject());
		}
		teamCardJsonObject.put("members", memberJsonArray);

		return teamCardJsonObject;
	}
}
