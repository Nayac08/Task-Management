package models;

import org.json.JSONArray;
import org.json.JSONObject;

import interfaces.Exportable;

public class PersonalCard extends Card implements Exportable{
	public Label label; // Add later

	public PersonalCard(int id,NodeList nodeListOwner, String title) {
		super(id, nodeListOwner, title);
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
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
			JSONObject checkListJsonObject = new JSONObject();
			checkListJsonObject.put("id",checklistItem.getId());
			checkListJsonObject.put("title", checklistItem.getTitle());
			checkListJsonObject.put("isChecked", checklistItem.isChecked());
			checklistJsonArray.put(checkListJsonObject);
		}
		personalCardJsonObject.put("checklists", checklistJsonArray);

		// Add later
		return personalCardJsonObject;
	}
}
