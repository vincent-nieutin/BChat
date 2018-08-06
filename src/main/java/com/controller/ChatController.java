package com.controller;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.channels.ClosedByInterruptException;

import com.model.SettingsModel;
import com.view.ChatView;
import com.view.ErrorView;

public class ChatController {
	
	private static String HANDSHAKE = "FirstRequest:/";
	
	private PrintWriter outputStream;
	private BufferedReader inputStream;
	private Socket clientSocket;
	private SettingsModel settingsModel;
	private ChatView chatView;
	private String username;
	private boolean running = true;
	
	public ChatController(SettingsModel settingsModel, Socket clientSocket, BufferedReader inputStream, PrintWriter outputStream) {
		//Setup
		this.clientSocket = clientSocket;
		this.inputStream = inputStream;
		this.outputStream = outputStream;
		this.username = settingsModel.getUsername();
		this.settingsModel = settingsModel;
		
		connect();
	}
	
	public void connect() {
		
		chatView = new ChatView();
		this.chatView.addSendListener(new SendButtonListener());
		this.chatView.addEnterKeyListener(new EnterListener());
		//Perform handshake
		outputStream.println(HANDSHAKE + settingsModel.getUsername());
		new InputThread().start();
	}
	
	public void disconnect() {
		outputStream.println("/disconnect");
		running = false;
		outputStream.close();
		try {
			clientSocket.close();
		} catch (IOException e) {
			System.out.println("Error while closing the connection.");
			e.printStackTrace();
		}
		chatView.addToChat("You disconnected from the chat");
	}
	
	public void sendMessageFromController(String message) {
		outputStream.println(message);
	}
	
	public void sendMessageFromInput() {
		String inputText = chatView.getInputText();
		switch(inputText) {
		case "/disconnect":
			disconnect();
			break;
		case "/exit":
			disconnect();
			chatView.dispose();
			break;
		case "":
			break;
		default:
			outputStream.println(inputText);
			break;
		}
			
	}
	
	class InputThread extends Thread{
		
		public InputThread() {
			super();
		}
		
		public void run() {
			String line;
			
			while(running) {
				try {
					line = inputStream.readLine();
					if(line != null)
						chatView.addToChat(line);
				}catch(SocketException e) {
					
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	class SendButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			sendMessageFromInput();
		}
	}
	
	class EnterListener implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER)
				sendMessageFromInput();
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

