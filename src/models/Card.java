package models;

import java.time.LocalDateTime;
import java.util.List;

import interfaces.Movable;

public abstract class Card implements Movable{
	private int id;
	private NodeList listType; // For exporting CSV purpose
	private String title;
	private String description; // Add later
	private LocalDateTime date; // Add later
	private List<ChecklistItem> checklists; // Add later
	private double checklistPercentage; // Add later when checklists is initialized
	
	public Card(int id,NodeList listType, String title) {
		this.id = id;
		setListType(listType);
		setTitle(title);
	}
	
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
