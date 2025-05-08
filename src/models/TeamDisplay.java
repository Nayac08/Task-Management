package models;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import interfaces.Displayable;
import interfaces.Exportable;

public class TeamDisplay implements Displayable,Exportable{
    private int id;
    private int idxListNode;
    private int idxMember;
    private String name;
    private List<NodeList> nodeLists;
    private List<Member> members; // Member in members can be selected in Card in NodeList

    public TeamDisplay(int id, String name) {
    	this.id = id;
    	setName(name);
    	setIdxListNode(0);
    	setNodeLists(new ArrayList<>());
    	setMembers(new ArrayList<>());
    }

    // Id no setter
  	public int getId() {
  		return id;
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

    // Members
    public void addMember(Member member) {
    	members.add(member);
    	setIdxMember(member.getId() + 1);
    }

    public void removeMember(int id) {
    	for (int i=0;i<members.size();i++) {
    		if (members.get(i).getId() == id) {
    			members.remove(i);
    			break;
    		}
    	}
    }

    public Member getMember(int id) {
    	for (Member member : members) {
    		if (member.getId() == id) {
    			return member;
    		}
    	}
    	return null;
    }

    public List<Member> getMembers() {
    	return members;
    }

    public void setMembers(List<Member> members) {
		this.members = members;
	}

	public int getIdxListNode() {
		return idxListNode;
	}

	public void setIdxListNode(int idxListNode) {
		this.idxListNode = idxListNode;
	}

	public int getIdxMember() {
		return idxMember;
	}

	public void setIdxMember(int idxMember) {
		this.idxMember = idxMember;
	}

	@Override
	public JSONObject getJsonObject() {
		JSONObject teamDisplayJsonObject = new JSONObject();
		teamDisplayJsonObject.put("name", name);
		JSONArray nodeListJsonArray = new JSONArray();
		for (NodeList nodeList: nodeLists) {
			nodeListJsonArray.put(nodeList.getJsonObject());
		}
		teamDisplayJsonObject.put("nodeLists", nodeListJsonArray);
		JSONArray memberJsonArray = new JSONArray();
		for (Member member: members) {
			memberJsonArray.put(member.getJsonObject());
		}
		teamDisplayJsonObject.put("members", memberJsonArray);
		return teamDisplayJsonObject;
	}
}
