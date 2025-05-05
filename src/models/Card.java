package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Card {
	protected int id;
	protected NodeList nodeListOwner;
	protected String title;
	protected String description;
	protected LocalDate date;
	protected int idxChecklists;
	protected List<ChecklistItem> checklists;

	public Card(int id,NodeList nodeListOwner, String title) {
		this.id = id;
		this.idxChecklists = 0;
		setNodeListOwner(nodeListOwner);
		setTitle(title);
		setDescription("");
		setDate(null);
		setChecklists(new ArrayList<>());
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
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
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
		for (ChecklistItem checklist : checklists) {
    		if (checklist.getId() == id) {
    			return checklist;
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
	public int getNumberOfCheckedChecklist() {
		int numberOfCheckedChecklist = 0;
		for (ChecklistItem checklistItem: checklists) {
			if (checklistItem.isChecked()) {
				numberOfCheckedChecklist++;
			}
		}
		return numberOfCheckedChecklist;
	}

	public double getChecklistPercentage() {
		double numberOfCheckedChecklist = getNumberOfCheckedChecklist();
		if (numberOfCheckedChecklist == 0) {
			return 0.;
		} else {
			return numberOfCheckedChecklist/checklists.size();
		}
	}
}
