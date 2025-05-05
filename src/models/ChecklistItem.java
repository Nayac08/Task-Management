package models;

import org.json.JSONObject;
import interfaces.Exportable;

public class ChecklistItem implements Exportable{
    private int id;
    private String title;
    private boolean isChecked;

    public ChecklistItem(int id,String title) {
    	this.id = id;
    	setTitle(title);
    	setChecked(false);
    }

    // Feature
    public void toggleChecked() {
    	setChecked(!isChecked);
    }

    // Id
    public int getId() {
		return id;
	}

    // Title
    public void setTitle(String title) {
		this.title = title;
	}

    public String getTitle() {
		return title;
	}

	// IsChecked
	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
	
	@Override
	public JSONObject getJsonObject() {
		JSONObject checkListJsonObject = new JSONObject();
		checkListJsonObject.put("id",id);
		checkListJsonObject.put("title", title);
		checkListJsonObject.put("isChecked", isChecked);
		return checkListJsonObject;
	}
}
