package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import interfaces.Movable;

public abstract class Card implements Movable{
	protected int id;
	protected NodeList nodeListOwner; // For exporting CSV purpose
	protected String title;
	protected String description; // Add later
	protected LocalDateTime date; // Add later
	protected int idxChecklists;
	protected List<ChecklistItem> checklists; // Add later

	public Card(int id,NodeList nodeListOwner, String title) {
		this.id = id;
		this.idxChecklists = 0;
		setNodeListOwner(nodeListOwner);
		setTitle(title);
		setChecklists(new ArrayList<ChecklistItem>());
	}

	@Override
	public void dragToList(NodeList list) {

	}

	// Id
	public int getId() {
		return id;
	}

	// ListType
	public NodeList getNodeListOwner() {
		return nodeListOwner;
	}

	public void setNodeListOwner(NodeList nodeListOwner) {
		this.nodeListOwner = nodeListOwner;
	}

	// Title
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	// Description
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	// Date
	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	// idxCheckList
	public int getIdxChecklists() {
		return idxChecklists;
	}

	public void setIdxChecklists(int idxChecklists) {
		this.idxChecklists = idxChecklists;
	}

	// AllChecklist
	public void addChecklist(ChecklistItem item) {
		checklists.add(item);
		setIdxChecklists(item.getId() + 1);
	}

	public void removeChecklist(int id) {
		for (int i=0;i<checklists.size();i++) {
    		if (checklists.get(i).getId() == id) {
    			checklists.remove(i);
    			break;
    		}
    	}
	}
	
	public ChecklistItem getChecklist(int id) {
		for (int i=0;i<checklists.size();i++) {
    		if (checklists.get(i).getId() == id) {
    			return checklists.get(i);
    		}
    	}
		return null;
	}

	public List<ChecklistItem> getChecklists() {
		return checklists;
	}

	public void setChecklists(List<ChecklistItem> checklists) {
		this.checklists = checklists;
	}

	// ChecklistPercentage
	public double getChecklistPercentage() {
		int numberOfCheckedChecklist = 0;
		for (ChecklistItem checklistItem: checklists) {
			if (checklistItem.isChecked()) {
				numberOfCheckedChecklist++;
			}
		}
		System.out.println(numberOfCheckedChecklist);
		if (numberOfCheckedChecklist == 0) {
			return 0.;
		} else {
			return numberOfCheckedChecklist/(double) checklists.size();
		}
	}
}
