package models;

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
		// Add later
		return personalCardJsonObject;
	}
}
