package com.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.model.SettingsModel;
import com.view.SettingsView;

public class SettingsController {
	
	private SettingsView settingsView;
	private SettingsModel serverSettingsModel;
	
	public SettingsController(SettingsModel serverSettingsModel) {
		this.serverSettingsModel = serverSettingsModel;
		settingsView = new SettingsView(serverSettingsModel);
		settingsView.addOkButtonListener(new OkButtonListener());
		settingsView.addCancelButtonListener(new CancelButtonListener());
	}
	
	public SettingsModel getServerSettings() {
		return serverSettingsModel;
	}
	
	public void hideServerSettingsView() {
		settingsView.setVisible(false);
	}
	
	public void showServerSettingsView() {
		settingsView.setVisible(true);
	}
	
	class OkButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			serverSettingsModel = settingsView.getServerSettings();
			settingsView.dispose();
		}	
	}
	
	class CancelButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			settingsView.dispose();
			settingsView.setServer(serverSettingsModel.getServer());
			settingsView.setPort(serverSettingsModel.getPort());
			//settingsView.set
		}
		
	}
}
