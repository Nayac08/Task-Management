package models;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class PersonalCard extends Card{
	public List<Label> labels;

	public PersonalCard(int id,NodeList nodeListOwner, String title) {
		super(id, nodeListOwner, title);
		setLabels(new ArrayList<>());
	}

	// Label
	public void addLabel(Label label) {
		labels.add(label);
	}

	public void removeLabel(int id) {
		for (int i=0;i<labels.size();i++) {
    		if (labels.get(i).getId() == id) {
    			labels.remove(i);
    			break;
    		}
    	}
	}

	public boolean isContainLabel(int id) {
		for (Label label : labels) {
    		if (label.getId() == id) {
    			return true;
    		}
    	}
		return false;
	}

	public Label getLabel(int id) {
		for (Label label : labels) {
    		if (label.getId() == id) {
    			return label;
    		}
    	}
		return null;
	}

	public List<Label> getLabels() {
		return labels;
	}

	public void setLabels(List<Label> labels) {
		this.labels = labels;
	}

	@Override
	public JSONObject getJsonObject() {
		JSONObject personalCardJsonObject = new JSONObject();
		personalCardJsonObject.put("id", id);
		personalCardJsonObject.put("title", title);
		personalCardJsonObject.put("date", date != null ? date.toString() : JSONObject.NULL);
		personalCardJsonObject.put("description", description);

		JSONArray checklistJsonArray = new JSONArray();
		for (ChecklistItem checklistItem: checklists) {
			checklistJsonArray.put(checklistItem.getJsonObject());
		}
		personalCardJsonObject.put("checklists", checklistJsonArray);

		JSONArray labelJsonArray = new JSONArray();
		for (Label label: labels) {
			labelJsonArray.put(label.getJsonObject());
		}
		personalCardJsonObject.put("labels", labelJsonArray);
		// Add later
		return personalCardJsonObject;
	}
}
