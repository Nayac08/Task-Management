package models;

import org.json.JSONObject;

import interfaces.Exportable;

public class Member implements Exportable{
    private int id;
    private String name;
    private String email;

    public Member(int id, String name) {
    	this.id = id;
    	setName(name);
    	setEmail(null);
    }

    public Member(int id, String name, String email) {
    	this.id = id;
    	setName(name);
    	setEmail(email);
    }

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public JSONObject getJsonObject() {
		// TODO Auto-generated method stub
		return null;
	}
}
