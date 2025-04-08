package models;


public class PersonalCard extends Card{
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
}
