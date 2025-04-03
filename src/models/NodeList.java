package models;

import java.util.List;

import javafx.scene.paint.Color;

public class NodeList {
    private int id;
    private String title;
    private List<Card> allCards;
    private Color color;
    
    public void addCard(Card card) {
    	
    }
    
    public void removeCard(Card card) {
    	
    }
    
    public Card getCard(int index) {
    	return allCards.get(index);
    }
    
    public void setColor(String color) {
    	
    }
    
    public void sortCards(String criteria) {
    	
    }
    
    public List<Card> getAllCards() {
    	return allCards;
    }
    
    public String getTitle() {
    	return title;
    }
    
    public void setName(String name) {
    	
    }
    		
}
