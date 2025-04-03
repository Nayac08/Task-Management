package models;


public class PersonalCard extends Card{
	public Label label; // Add later
	
	public PersonalCard(int id,NodeList listType, String title) {
		super(id, listType, title);
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}
}
