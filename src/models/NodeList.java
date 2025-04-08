package models;

import java.util.ArrayList;
import java.util.List;

import enums.SortCriteria;
import interfaces.Sortable;
import javafx.scene.paint.Color;

public class NodeList implements Sortable{
    private int id;
    private Board boardOwner;
    private String title;
    private List<Card> cards;
    private Color color = Color.GRAY;
    
    public NodeList(int id, Board boardOwner, String title) {
    	this.id = id;
    	setTitle(title);
    	setBoardOwner(boardOwner);
    	setCards(new ArrayList<Card>());
    }
    
    // Feature
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

	// Board Owner
	public Board getBoardOwner() {
		return boardOwner;
	}

	public void setBoardOwner(Board boardOwner) {
		this.boardOwner = boardOwner;
	}

	
}
