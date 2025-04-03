package models;

import java.time.LocalDateTime;
import java.util.List;

public abstract class Card {
	private int id;
	private String title;
	private String description;
	private LocalDateTime date;
	private List<ChecklistItem> allChecklist;
	private NodeList listType;
	private double checklistPercentage;
	
	public void updateCard() {
		
	}
	
	public String getDescription() {
		return description;
	}
	
	public void dragToList(NodeList list) {
		
	}
	
	public void updateChecklistPercentage() {
		
	}
	
	public void addChecklistItem(ChecklistItem item) {
		
	}
	
	public void removeChecklistItem(ChecklistItem item) {
		
	}
	
	public List<ChecklistItem> getAllChecklist() {
		return allChecklist;
	}
}
