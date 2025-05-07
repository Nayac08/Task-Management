package models;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import enums.Priority;
import interfaces.Exportable;

public class TeamCard extends Card{
	private List<Member> members; // Add later , This members is assigned to finish this Card
	private Priority priority; // Add later

	public TeamCard(int id,NodeList nodeListOwner, String title) {
		super(id, nodeListOwner, title);
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

	// Priority
	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Priority getPriority() {
		return priority;
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

		// Add later
		return teamCardJsonObject;
	}
}
