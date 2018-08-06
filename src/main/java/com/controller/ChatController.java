package com.controller;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.channels.ClosedByInterruptException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

import com.model.SettingsModel;
import com.view.ChatView;
import com.view.ErrorView;

public class ChatController {
	
	private static String HANDSHAKE = "FirstRequest:/";
	private static String PATH_TO_AUDIO = "/ressources/audio/new_message_sound.wav";
	
	private PrintWriter outputStream;
	private BufferedReader inputStream;
	private Socket clientSocket;
	private SettingsModel settingsModel;
	private ChatView chatView;
	private String username;
	private Clip newMessageSound;
	private AudioInputStream audioInputStream;
	
	private boolean running = true;
	
	public ChatController(SettingsModel settingsModel, Socket clientSocket, BufferedReader inputStream, PrintWriter outputStream) {
		//Setup
		this.clientSocket = clientSocket;
		this.inputStream = inputStream;
		this.outputStream = outputStream;
		this.username = settingsModel.getUsername();
		this.settingsModel = settingsModel;
		try {
			newMessageSound = AudioSystem.getClip();
			audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(PATH_TO_AUDIO));
			newMessageSound.open(audioInputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		connect();
	}
	
	public void connect() {
		
		chatView = new ChatView();
		this.chatView.addSendListener(new SendButtonListener());
		this.chatView.addEnterKeyListener(new EnterListener());
		//Perform handshake
		outputStream.println(HANDSHAKE + settingsModel.getUsername());
		new WaitForInputThread().start();
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
	
	private void playNewMessageSound() {
		new Thread(new NewMessageSound()).start();
	}
	
	class NewMessageSound implements Runnable {
		
		public void run() {
			newMessageSound.start();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			newMessageSound.setFramePosition(0);
		}
	}
	
	class WaitForInputThread extends Thread{
		
		public WaitForInputThread() {
			super();
		}
		
		public void run() {
			String line;
			
			while(running) {
				try {
					line = inputStream.readLine();
					if(line != null) {
						chatView.addToChat(line);
						if(!line.startsWith(username))
							playNewMessageSound();
					}
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

