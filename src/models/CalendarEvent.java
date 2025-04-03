package models;

import java.time.LocalDateTime;

public class CalendarEvent {
    private int id;
    private Card card;
    private LocalDateTime dateTime;

    public LocalDateTime getEventDate() {
    	return dateTime;
    }

    public String getCardDetails() {
    	return card.getDescription();
    }
}
