package models;

import java.util.List;

import enums.Priority;

public class TeamCard extends Card{
	private List<Member> members;
	private Priority priority;
	
	public void addMember(Member member) {
		
	}
	
	public void removeMember(Member member) {
		
	}
	
	public void setPriority(Priority priority) {
		
	}
	
	public Priority getPriority() {
		return priority;
	}
	
	public List<Member> getMembers() {
		return members;
	}
}
