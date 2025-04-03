package models;

import java.time.LocalDateTime;
import java.util.List;

public abstract class Card {
	private int id;
	private NodeList listType; // for exporting CSV purpose
	private String title;
	private String description;
	private LocalDateTime date;
	private List<ChecklistItem> checklists;
	private double checklistPercentage;
	
	// Feature
	public void updateCard() {
		
	}
	
	public void dragToList(NodeList list) {
		
	}
	
	// Id
	public int getId() {
		return id;
	}
	
	// ListType
	public NodeList getListType() {
		return listType;
	}

	public void setListType(NodeList listType) {
		this.listType = listType;
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
	
	// AllChecklist
	public void addChecklist(ChecklistItem item) {
		checklists.add(item);
	}
	
	public void removeChecklist(int id) {
		for (int i=0;i<checklists.size();i++) {
    		if (checklists.get(i).getId() == id) {
    			checklists.remove(i);
    			break;
    		}
    	}
	}
	
	public List<ChecklistItem> getChecklists() {
		return checklists;
	}

	public void setAllChecklist(List<ChecklistItem> allChecklist) {
		this.checklists = allChecklist;
	}
	
	// ChecklistPercentage
	public double getChecklistPercentage() {
		return checklistPercentage;
	}

	public void setChecklistPercentage(double checklistPercentage) {
		this.checklistPercentage = checklistPercentage;
	}
}
