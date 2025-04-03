package models;

public class ChecklistItem {
    private int id;
    private String descrption;
    private boolean isChecked;
    
    public ChecklistItem(int id,String description) {
    	this.id = id;
    	setDescrption(description);
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

    // Description
	public String getDescrption() {
		return descrption;
	}

	public void setDescrption(String descrption) {
		this.descrption = descrption;
	}

	// IsChecked
	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}	
}
