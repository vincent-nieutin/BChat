package com.model;

import java.awt.Color;

public class ConnectionModel {
	private String username;
	private String server;
	private int port;
	
	private Color textColor = Color.BLACK;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public Color getColor() {
		return textColor;
	}
	public String getColorAsString() {
		String color = textColor.toString();
		String rgb = color.substring(color.indexOf("["), color.indexOf("]")+1);
		return rgb;
	}
	public void setColor(Color textColor) {
		this.textColor = textColor;
	}
}
