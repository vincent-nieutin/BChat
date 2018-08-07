package com.model;

import java.awt.Color;

public class SettingsModel {
	private String server = "localhost";
	private int port = 6666;
	private Color color = Color.BLACK;
	
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
		return color;
	}
	public void setColor(Color textColor) {
		this.color = textColor;
	}
}
