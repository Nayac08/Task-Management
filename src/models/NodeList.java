package models;

import java.util.ArrayList;
import java.util.List;

import enums.SortCriteria;
import interfaces.Displayable;
import interfaces.Sortable;
import javafx.scene.paint.Color;

public class NodeList implements Sortable{
    private int id;
    private Displayable displayOwner;
    private String title;
    private List<Card> cards;
    private Color color = Color.GRAY;

    public NodeList(int id, Displayable displayOwner, String title) {
    	this.id = id;
    	setTitle(title);
    	setDisplayOwner(displayOwner);
    	setCards(new ArrayList<>());
    }

    // Feature
    @Override
	public void sortCards(SortCriteria criteria) {
    	// TODO
    	if (criteria == SortCriteria.ASCENDING_DATE) {

    	} else if (criteria == SortCriteria.DESCENDING_DATE) {

    	} else if (criteria == SortCriteria.ASCENDING_PRIORITY) {

    	} else if (criteria == SortCriteria.DESCENDING_PRIORITY) {

    	}
    }

    // Id
 	public int getId() {
 		return id;
 	}

 	// Title
    public String getTitle() {
    	return title;
    }

    public void setTitle(String title) {
		this.title = title;
	}

    // Cards
    public void addCard(Card card) {
    	cards.add(card);
    }

    public void removeCard(int id) {
    	for (int i=0;i<cards.size();i++) {
    		if (cards.get(i).getId() == id) {
    			cards.remove(i);
    			break;
    		}
    	}
    }

    public Card getCard(int index) {
    	return cards.get(index);
    }

    public List<Card> getCards() {
    	return cards;
    }

    public void setCards(List<Card> allCards) {
		this.cards = allCards;
	}

    // Color
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	// Display Owner
	public Displayable getDisplayOwner() {
		return displayOwner;
	}

	public void setDisplayOwner(Displayable displayOwner) {
		this.displayOwner = displayOwner;
	}


}
