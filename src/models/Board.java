package models;
import java.io.File;
import java.util.*;

public class Board {
    private int id;
    private String name;
    private List<NodeList> lists;
    private List<Member> members;

    public void addList(NodeList List) {

    }

    public void removeList(NodeList List) {

    }

    public NodeList getList(int index) {
    	return new NodeList();
    }

    public List<NodeList> getAllLists() {
    	return new ArrayList<NodeList>();
    }

    public void exportData(File file) {

    }

    public void importData(File file) {

    }

    public void addMember(Member member) {

    }

    public void removeMember(Member member) {

    }

    public List<Member> getAllMembers() {
    	return new ArrayList<Member>();
    }
}
