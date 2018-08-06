package com.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.model.ServerSettingsModel;
import com.model.SettingsModel;
import com.view.ChatView;
import com.view.ErrorView;
import com.view.LoginView;

public class LoginController {
	private LoginView loginView;
	private SettingsModel settingsModel;
	private ServerSettingsController serverSettingsController;
	private ServerSettingsModel serverSettingsModel;
	
	private Socket clientSocket;
	private PrintWriter outputStream;
	private BufferedReader inputStream;
	
	public LoginController(LoginView loginView) {
		this.loginView = loginView;
		this.loginView.addConnectButtonListener(new ConnectButtonListener());
		this.loginView.addSettingsButtonListener(new SettingsButtonListener());
		
		serverSettingsController = new ServerSettingsController();
	}
	
	public void startChat() {
		serverSettingsModel = serverSettingsController.getServerSettings();
		settingsModel = loginView.getSettings();
		if (settingsModel == null) {
			return;
		}
		
		settingsModel.setServer(serverSettingsModel.getServer());
		settingsModel.setPort(serverSettingsModel.getPort());
		
		try {
			clientSocket = new Socket(settingsModel.getServer(), settingsModel.getPort());
			outputStream = new PrintWriter(clientSocket.getOutputStream(), true);
			inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (UnknownHostException e) {
			new ErrorView("Unknown host", "Could not find the specified host");
			new RuntimeException("Could not find the specified host", e);
			return;
		} catch (ConnectException e) {
			new ErrorView("Connexion error", "Could not connect to the server");
			new RuntimeException("Cannot connect to the server", e);
			return;
		} catch(IOException e) {
			new ErrorView("Io Exception", "Halp");
			new RuntimeException("IO exception", e);
			return;
		}
		
		new ChatController(this.settingsModel, clientSocket, inputStream, outputStream);
		
		loginView.setVisible(false);
	}
	
	class ConnectButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			startChat();
		}

	}
	
	class SettingsButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			serverSettingsController.showServerSettingsView();;
		}

	}
}
