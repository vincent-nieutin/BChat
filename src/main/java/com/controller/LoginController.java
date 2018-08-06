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

import com.model.SettingsModel;
import com.view.ChatView;
import com.view.ErrorView;
import com.view.LoginView;

public class LoginController {
	private LoginView connexionView;
	private SettingsModel settingsModel;
	private ChatController chatController;
	private Socket clientSocket;
	private PrintWriter outputStream;
	private BufferedReader inputStream;
	
	public LoginController(LoginView connexionView) {
		this.connexionView = connexionView;

		this.connexionView.addConnectListener(new ConnexionListener());
	}
	
	public void startChat() {
		SettingsModel settingsModel = connexionView.getSettings();
		if (settingsModel == null) {
			return;
		}
		
		this.settingsModel = settingsModel;		
		
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
		
		chatController = new ChatController(this.settingsModel, clientSocket, inputStream, outputStream);
		
		connexionView.setVisible(false);
	}
	
	class ConnexionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			startChat();
		}

	}
}
