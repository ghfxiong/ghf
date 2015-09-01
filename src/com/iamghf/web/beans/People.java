package com.iamghf.web.beans;

public class People {
	public static final String tableName = "people";
	public String name;
	public String occupation;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	
}
