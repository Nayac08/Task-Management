package models;

import java.time.LocalDateTime;

public class CalendarEvent {
    private int id;
    private Card card;
    private LocalDateTime dateTime;

    public CalendarEvent(int id,Card card) {
    	this.id = id;
    	setCard(card);
    	setDateTime(card.getDate());
    }

    public int getId() {
		return id;
	}

    public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
}
