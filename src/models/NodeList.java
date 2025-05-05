package models;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import interfaces.Displayable;
import interfaces.Exportable;
import javafx.scene.paint.Color;

public class NodeList implements Exportable{
    private int id;
    private Displayable displayOwner;
    private int idxCard;
    private String title;
    private List<Card> cards;

    public NodeList(int id, Displayable displayOwner, String title) {
    	this.id = id;
    	setTitle(title);
    	setIdxCard(0);
    	setDisplayOwner(displayOwner);
    	setCards(new ArrayList<>());
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
    	setIdxCard(card.getId() + 1);
    }

    public void removeCard(int id) {
    	for (int i=0;i<cards.size();i++) {
    		if (cards.get(i).getId() == id) {
    			cards.remove(i);
    			break;
    		}
    	}
    }

    public List<Card> getCards() {
    	return cards;
    }

    public void setCards(List<Card> allCards) {
		this.cards = allCards;
	}

	// Display Owner
	public Displayable getDisplayOwner() {
		return displayOwner;
	}

	public void setDisplayOwner(Displayable displayOwner) {
		this.displayOwner = displayOwner;
	}

	public int getIdxCard() {
		return idxCard;
	}

	public void setIdxCard(int idxCard) {
		this.idxCard = idxCard;
	}

	@Override
	public JSONObject getJsonObject() {
		JSONObject nodeListJsonObject = new JSONObject();
		nodeListJsonObject.put("id", id);
		nodeListJsonObject.put("title", title);
		JSONArray cardsJsonArray = new JSONArray();
		if (!cards.isEmpty() && cards.get(0) instanceof TeamCard) {
			for (Card card: cards) {
				cardsJsonArray.put(((TeamCard)card).getJsonObject());
			}
		} else if (!cards.isEmpty() && cards.get(0) instanceof PersonalCard) {
			for (Card card: cards) {
				cardsJsonArray.put(((PersonalCard)card).getJsonObject());
			}
		}
		nodeListJsonObject.put("cards", cardsJsonArray);
		return nodeListJsonObject;
	}


}
