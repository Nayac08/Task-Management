package models;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class TeamCard extends Card{
	private List<Member> members;

	public TeamCard(int id,NodeList nodeListOwner, String title) {
		super(id, nodeListOwner, title);
		setMembers(new ArrayList<>());
	}

	// Members
	public void addMember(Member member) {
		members.add(member);
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
	
	public void syncMemberDisplay() {
		TeamDisplay teamDisplayOwner = (TeamDisplay) getNodeListOwner().getDisplayOwner();
		for (int i=members.size()-1; i>=0;i--) {
			if (!teamDisplayOwner.isContainMember(members.get(i).getId())) {
				members.remove(members.get(i));
			}
		}
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
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
