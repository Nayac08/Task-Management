package models;

public class ChecklistItem {
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

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	// IsChecked
	public boolean isChecked() {
		return isChecked;
	}

	public String getTitle() {
		return title;
	}	
}
