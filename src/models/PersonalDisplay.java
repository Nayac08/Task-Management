package models;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import interfaces.Displayable;
import interfaces.Exportable;

public class PersonalDisplay implements Displayable,Exportable{
	private int id;
	private int idxListNode;
	private int idxLabel;
	private String name;
	private List<NodeList> nodeLists;
	private List<Label> labels;

    public PersonalDisplay(int id, String name) {
    	this.id = id;
    	setName(name);
    	setIdxListNode(0);
    	setIdxLabel(0);
    	setNodeLists(new ArrayList<>());
    	setLabels(new ArrayList<>());
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

 	// IdxListNode
 	public int getIdxListNode() {
		return idxListNode;
	}

	public void setIdxListNode(int idxListNode) {
		this.idxListNode = idxListNode;
	}

    // NodeLists
    public void addNodeList(NodeList List) {
    	nodeLists.add(List);
    	setIdxListNode(List.getId() + 1);
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

    // IdxLabel
    public int getIdxLabel() {
		return idxLabel;
	}

	public void setIdxLabel(int idxLabel) {
		this.idxLabel = idxLabel;
	}

    // Label
    public void addLabel(Label label) {
    	labels.add(label);
    	setIdxLabel(label.getId() + 1);
    }

	public void removeLabel(int id) {
    	for (int i=0;i<labels.size();i++) {
    		if (labels.get(i).getId() == id) {
    			labels.remove(i);
    			break;
    		}
    	}
    }

    public Label getLabel(int id) {
    	for (Label label : labels) {
    		if (label.getId() == id) {
    			return label;
    		}
    	}
    	return null;
    }

    public List<Label> getLabels() {
		return labels;
	}

	public void setLabels(List<Label> labels) {
		this.labels = labels;
	}

	@Override
	public JSONObject getJsonObject() {
		JSONObject personalDisplayJsonObject = new JSONObject();
		personalDisplayJsonObject.put("name", name);
		JSONArray nodeListJsonArray = new JSONArray();
		for (NodeList nodeList: nodeLists) {
			nodeListJsonArray.put(nodeList.getJsonObject());
		}
		personalDisplayJsonObject.put("nodeLists", nodeListJsonArray);
		JSONArray labelJsonArray = new JSONArray();
		for (Label label: labels) {
			labelJsonArray.put(label.getJsonObject());
		}
		personalDisplayJsonObject.put("labels", labelJsonArray);
		return personalDisplayJsonObject;
	}
}
