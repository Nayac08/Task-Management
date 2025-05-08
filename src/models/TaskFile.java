package models;

import java.time.LocalDate;

import org.json.JSONArray;
import org.json.JSONObject;

import enums.FileType;
import enums.RoleMember;
import interfaces.Displayable;
import interfaces.Exportable;

public class TaskFile implements Exportable{
	private int id;
	private MainInterface mainInterface;
	private Displayable display;

	public TaskFile(int id, String title, FileType fileType, MainInterface mainInterface) {
		this.id = id;
		setMainInterface(mainInterface);
		if (fileType == FileType.Personal) {
			setDisplay(new PersonalDisplay(id, title));
		} else if (fileType == FileType.Team) {
			setDisplay(new TeamDisplay(id, title));
		}
	}

	public TaskFile(int id, JSONObject jsonObject, MainInterface mainInterface) {
		this.id = id;
		setMainInterface(mainInterface);
		setDisplay(jsonToDisplay(jsonObject));
	}

	public Displayable jsonToDisplay(JSONObject jsonTaskFileObject) {
	    String displayType = jsonTaskFileObject.getString("displayType");
	    JSONObject displayObject = jsonTaskFileObject.getJSONObject("display");
	    JSONArray nodeListsArray = displayObject.getJSONArray("nodeLists");

	    Displayable display;
	    boolean isTeam = displayType.equals("TeamDisplay");

	    if (isTeam) {
	        display = new TeamDisplay(id, displayObject.getString("name"));
	    } else if (displayType.equals("PersonalDisplay")) {
	        display = new PersonalDisplay(id, displayObject.getString("name"));
	    } else {
	        return null;
	    }

	    for (int i = 0; i < nodeListsArray.length(); i++) {
	        JSONObject nodeListObject = nodeListsArray.getJSONObject(i);
	        JSONArray cardsArray = nodeListObject.getJSONArray("cards");
	        NodeList nodeList = new NodeList(nodeListObject.getInt("id"), display, nodeListObject.getString("title"));

	        for (int j = 0; j < cardsArray.length(); j++) {
	            JSONObject cardObject = cardsArray.getJSONObject(j);
	            Card card;

	            if (isTeam) {
	                card = new TeamCard(cardObject.getInt("id"), nodeList, cardObject.getString("title"));
	            } else {
	                card = new PersonalCard(cardObject.getInt("id"), nodeList, cardObject.getString("title"));
	            }
	            if (!cardObject.isNull("date")) {
	            		card.setDate(LocalDate.parse(cardObject.getString("date")));
	            }
	            card.setDescription(cardObject.getString("description"));
	            // Checklists
	            for (Object o : cardObject.getJSONArray("checklists")) {
	            		JSONObject checklistJSONObject = (JSONObject) o;
	            		ChecklistItem checklistItem = new ChecklistItem(checklistJSONObject.getInt("id"), checklistJSONObject.getString("title"));
	            		checklistItem.setChecked(checklistJSONObject.getBoolean("isChecked"));
	            		card.addChecklist(checklistItem);
	            }
	            
	            // Members in card
	            if (display instanceof TeamDisplay) {
		            	for (Object o : cardObject.getJSONArray("members")) {
		            		JSONObject memberJsonObject = (JSONObject) o;
		            		String roleString = memberJsonObject.getString("role");
		            		RoleMember roleEnum = null;
		            		if (roleString.equals("Project_Manager")) {
		            			roleEnum = RoleMember.Project_Manager;
		            		} else if (roleString.equals("Developer")) {
		            			roleEnum = RoleMember.Developer;
		            		} else if (roleString.equals("Designer")) {
		            			roleEnum = RoleMember.Designer;
		            		} else if (roleString.equals("QA_Tester")) {
		            			roleEnum = RoleMember.QA_Tester;
		            		} else if (roleString.equals("Intern")) {
		            			roleEnum = RoleMember.Intern;
		            		}
		            		Member member = new Member(memberJsonObject.getInt("id"), (TeamDisplay) display, memberJsonObject.getString("name"), roleEnum);
		            		((TeamCard) card).addMember(member);
		            }
	            }
	            
	            	nodeList.addCard(card);
	        }
	        if (isTeam) {
	            ((TeamDisplay) display).addNodeList(nodeList);
	        } else {
	            ((PersonalDisplay) display).addNodeList(nodeList);
	        }
	    }
	    
	    if (isTeam) {
	    	JSONArray memberJsonArray = displayObject.getJSONArray("members");
	    	for (int i=0;i<memberJsonArray.length();i++) {
	    		JSONObject memberJsonObject = memberJsonArray.getJSONObject(i);
	    		RoleMember roleMember = null;
	    		if (memberJsonObject.getString("role").equals("Project_Manager")) {
	    			roleMember = RoleMember.Project_Manager;
	    		} else if (memberJsonObject.getString("role").equals("Developer")) {
	    			roleMember = RoleMember.Developer;
	    		} else if (memberJsonObject.getString("role").equals("Designer")) {
	    			roleMember = RoleMember.Designer;
	    		} else if (memberJsonObject.getString("role").equals("QA_Tester")) {
	    			roleMember = RoleMember.QA_Tester;
	    		} else if (memberJsonObject.getString("role").equals("Intern")) {
	    			roleMember = RoleMember.Intern;
	    		} 
	    		((TeamDisplay) display).addMember(new Member(memberJsonObject.getInt("id"), ((TeamDisplay) display), memberJsonObject.getString("name"), roleMember));
	    	}
	    }
	    return display;
	}

	public Displayable getDisplay() {
		return display;
	}

	public void setDisplay(Displayable display) {
		this.display = display;
	}

	public int getId() {
		return id;
	}

	public MainInterface getMainInterface() {
		return mainInterface;
	}

	public void setMainInterface(MainInterface mainInterface) {
		this.mainInterface = mainInterface;
	}

	@Override
	public JSONObject getJsonObject() {
		JSONObject taskFileJsonObject = new JSONObject();
		//
		if (display instanceof TeamDisplay) {
			taskFileJsonObject.put("displayType", "TeamDisplay");
			taskFileJsonObject.put("display",((TeamDisplay) display).getJsonObject());
		} else if (display instanceof PersonalDisplay) {
			taskFileJsonObject.put("displayType", "PersonalDisplay");
			taskFileJsonObject.put("display", ((PersonalDisplay) display).getJsonObject());
		}
		return taskFileJsonObject;

	}
}
