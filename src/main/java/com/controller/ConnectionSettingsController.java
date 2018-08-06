package com.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.model.ConnectionSettingsModel;
import com.view.ConnectionSettingsView;

public class ConnectionSettingsController {
	
	private ConnectionSettingsView connectionSettingsView;
	private ConnectionSettingsModel connectionSettingsModel;
	
	public ConnectionSettingsController(ConnectionSettingsView connectionSettingsView) {
		this.connectionSettingsView = connectionSettingsView;
		this.connectionSettingsView.addOkButtonListener(new OkButtonListener());
		this.connectionSettingsView.addCancelButtonListener(new CancelButtonListener());
		connectionSettingsModel = new ConnectionSettingsModel();
	}
	
	public ConnectionSettingsModel getConnectionSettings() {
		return connectionSettingsModel;
	}
	
	class OkButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			connectionSettingsModel = connectionSettingsView.getConnectionSettings();
			connectionSettingsView.dispose();
		}	
	}
	
	class CancelButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			connectionSettingsView.dispose();
		}
		
	}
}
