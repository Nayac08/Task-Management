package models;

import org.json.JSONObject;

import interfaces.Exportable;
import javafx.scene.paint.Color;

public class Label implements Exportable{
	private int id;
	private String title;
	private Color color;

	public Label(int id,String title,Color color) {
		this.id = id;
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

	@Override
	public JSONObject getJsonObject() {
		// TODO Auto-generated method stub
		return null;
	}
}
