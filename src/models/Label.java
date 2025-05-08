package models;

import org.json.JSONObject;

import interfaces.Exportable;
import javafx.scene.paint.Color;

public class Label implements Exportable{
	private int id;
	PersonalDisplay personalDisplay;
	private String title;
	private Color color;

	public Label(int id, PersonalDisplay personalDisplay,String title,Color color) {
		this.id = id;
		setPersonalDisplay(personalDisplay);
		setTitle(title);
		setColor(color);
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public PersonalDisplay getPersonalDisplay() {
		return personalDisplay;
	}

	public void setPersonalDisplay(PersonalDisplay personalDisplay) {
		this.personalDisplay = personalDisplay;
	}

	@Override
	public JSONObject getJsonObject() {
		JSONObject labelJsonObject = new JSONObject();
		labelJsonObject.put("id", id);
		labelJsonObject.put("title", title);
		labelJsonObject.put("color", color.toString());
		return labelJsonObject;
	}
}
