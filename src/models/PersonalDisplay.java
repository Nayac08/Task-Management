package models;

import java.util.List;

import interfaces.Displayable;

public class PersonalDisplay implements Displayable{
	private int id;
	private String name;
	private List<NodeList> nodeLists;

    public PersonalDisplay(int id) {
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

	// Name
	@Override
	public String getName() {
 		return name;
 	}

 	public void setName(String name) {
 		this.name = name;
 	}

    // NodeLists
    public void addNodeList(NodeList List) {
    	nodeLists.add(List);
    }

    public void removeNodeList(int id) {
    	for (int i=0;i<nodeLists.size();i++) {
    		if (nodeLists.get(i).getId() == id) {
    			nodeLists.remove(i);
    			break;
    		}
    	}
    }

    public NodeList getNodeList(int id) {
    	for (NodeList nodeList : nodeLists) {
    		if (nodeList.getId() == id) {
    			return nodeList;
    		}
    	}
    	return null;
    }

    @Override
	public List<NodeList> getNodeLists() {
    	return nodeLists;
    }

    public void setNodeLists(List<NodeList> nodeLists) {
		this.nodeLists = nodeLists;
	}
}
