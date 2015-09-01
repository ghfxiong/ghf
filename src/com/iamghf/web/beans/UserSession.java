package com.iamghf.web.beans;

public class UserSession {
	private String name;
	private long uid;
	private String password;
	private String email;
	protected String getName() {
		return name;
	}
	protected void setName(String name) {
		this.name = name;
	}
	protected long getUid() {
		return uid;
	}
	protected void setUid(long uid) {
		this.uid = uid;
	}
	protected String getPassword() {
		return password;
	}
	protected void setPassword(String password) {
		this.password = password;
	}
	protected String getEmail() {
		return email;
	}
	protected void setEmail(String email) {
		this.email = email;
	}
	
	
}
