package models;

import java.util.List;

public class Calendar {
	private int id;
    private List<CalendarEvent> events;
    
    public Calendar(int id) {
    	this.id = id;
    	syncEvents();
    }
    
    // Feature
    public void displayCalendar() {
		
	}
    
	public void syncEvents() {
		
	}
	
	// Id
	public int getId() {
		return this.id;
	}
}
