package com.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.model.ServerSettingsModel;
import com.view.ServerSettingsView;

public class ServerSettingsController {
	
	private ServerSettingsView serverSettingsView;
	private ServerSettingsModel serverSettingsModel;
	
	public ServerSettingsController() {
		serverSettingsView = new ServerSettingsView();
		serverSettingsView.addOkButtonListener(new OkButtonListener());
		serverSettingsView.addCancelButtonListener(new CancelButtonListener());
		serverSettingsModel = new ServerSettingsModel();
	}
	
	public ServerSettingsModel getServerSettings() {
		return serverSettingsModel;
	}
	
	public void hideServerSettingsView() {
		serverSettingsView.setVisible(false);
	}
	
	public void showServerSettingsView() {
		serverSettingsView.setVisible(true);
	}
	
	class OkButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			serverSettingsModel = serverSettingsView.getServerSettings();
			serverSettingsView.dispose();
		}	
	}
	
	class CancelButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			serverSettingsView.dispose();
			serverSettingsView.setServer(serverSettingsModel.getServer());
			serverSettingsView.setPort(serverSettingsModel.getPort());
		}
		
	}
}
