package models;

import java.time.LocalDate;

public class CalendarEvent {
    private int id;
    private Card card;
    private LocalDate dateTime;

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

	public LocalDate getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDate dateTime) {
		this.dateTime = dateTime;
	}
}
