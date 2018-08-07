package com.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.model.SettingsModel;
import com.model.ConnectionModel;
import com.view.ErrorView;
import com.view.LoginView;

public class LoginController {
	private LoginView loginView;
	private ConnectionModel connectionModel;
	private SettingsController settingsController;
	private SettingsModel settingsModel;
	private XmlFileController xmlFileController;
	
	private Socket clientSocket;
	private PrintWriter outputStream;
	private BufferedReader inputStream;
	
	public LoginController() {
		xmlFileController = new XmlFileController();
		connectionModel = xmlFileController.getSettings();
		
		loginView = new LoginView(connectionModel.getUsername());
		loginView.addConnectButtonListener(new ConnectButtonListener());
		loginView.addSettingsButtonListener(new SettingsButtonListener());
		loginView.addEnterKeyListener(new EnterListener());
		
		settingsModel = new SettingsModel();
		settingsModel.setServer(connectionModel.getServer());
		settingsModel.setPort(connectionModel.getPort());
		settingsModel.setColor(connectionModel.getColor());
		settingsController = new SettingsController(settingsModel);
	}
	
	public void startChat() {
		settingsModel = settingsController.getServerSettings();
		connectionModel = loginView.getSettings();
		if (connectionModel == null || settingsModel == null) {
			return;
		}
		
		connectionModel.setServer(settingsModel.getServer());
		connectionModel.setPort(settingsModel.getPort());
		connectionModel.setColor(settingsModel.getColor());
		
		try {
			clientSocket = new Socket(connectionModel.getServer(), connectionModel.getPort());
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
		xmlFileController.saveSettings(connectionModel);
		new ChatController(this.connectionModel, clientSocket, inputStream, outputStream);
		
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
			settingsController.showServerSettingsView();
		}

	}
	
	class EnterListener implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
				startChat();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent e) {
		}

	}
}
