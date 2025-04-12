package models;
import java.util.ArrayList;
import java.util.List;

import interfaces.Displayable;

public class TeamDisplay implements Displayable{
    private int id;
    private String name;
    private List<NodeList> nodeLists;
    private List<Member> members; // Member in members can be selected in Card in NodeList

    public TeamDisplay(int id, String name) {
    	this.id = id;
    	setName(name);
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
}
