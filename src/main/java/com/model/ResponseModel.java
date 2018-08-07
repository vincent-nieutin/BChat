package com.model;

import java.awt.Color;

import javax.swing.text.SimpleAttributeSet;

public class ResponseModel {
	private String username;
	private String message;
	private SimpleAttributeSet usernameAttributeSet;
	private boolean isSeverMessage = false;
	private int id;
	private Color color;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public SimpleAttributeSet getUsernameAttributeSet() {
		return usernameAttributeSet;
	}
	public void setUsernameAttributeSet(SimpleAttributeSet usernameAttributeSet) {
		this.usernameAttributeSet = usernameAttributeSet;
	}
	public boolean isSeverMessage() {
		return isSeverMessage;
	}
	public void setIsSeverMessage(boolean isSeverMessage) {
		this.isSeverMessage = isSeverMessage;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public void setSeverMessage(boolean isSeverMessage) {
		this.isSeverMessage = isSeverMessage;
	}
}
