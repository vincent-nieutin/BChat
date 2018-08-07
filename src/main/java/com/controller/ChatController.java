package com.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import com.model.ResponseModel;
import com.model.SettingsModel;
import com.view.ChatView;

public class ChatController {

	private static String HANDSHAKE = "FirstRequest:/";
	private static String AUDIO_PATH = "/ressources/audio/";
	private static String NEW_MESSAGE_AUDIO_FILE = "new_message_sound.wav";
	private static String USERNAME_PARAMETER = "username=";
	private static String MESSAGE_PARAMETER = "message=";

	private PrintWriter outputStream;
	private BufferedReader inputStream;
	private Socket clientSocket;
	private SettingsModel settingsModel;
	private ChatView chatView;
	private String username;
	private Clip newMessageSound;
	private AudioInputStream audioInputStream;
	//private ResponseModel responseModel;

	private boolean running = true;

	public ChatController(SettingsModel settingsModel, Socket clientSocket, BufferedReader inputStream,
			PrintWriter outputStream) {
		// Setup
		this.clientSocket = clientSocket;
		this.inputStream = inputStream;
		this.outputStream = outputStream;
		this.username = settingsModel.getUsername();
		this.settingsModel = settingsModel;
		try {
			newMessageSound = AudioSystem.getClip();
			InputStream audioSource = getClass().getResourceAsStream(AUDIO_PATH + NEW_MESSAGE_AUDIO_FILE);
			InputStream bufferedInputStream = new BufferedInputStream(audioSource);
			audioInputStream = AudioSystem.getAudioInputStream(bufferedInputStream);
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
		// Perform handshake
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
	}

	public void sendMessageFromController(String message) {
		outputStream.println(message);
	}

	public void sendMessageFromInput() {
		String inputText = chatView.getInputText();
		switch (inputText) {
		case "/exit":
			disconnect();
			chatView.dispatchEvent(new WindowEvent(chatView, WindowEvent.WINDOW_CLOSING));
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
			if (newMessageSound.isRunning()) {
				newMessageSound.stop();
				newMessageSound.setFramePosition(0);
			} else if (!newMessageSound.isRunning()) {
				newMessageSound.setFramePosition(0);
			}

			newMessageSound.start();
		}
	}

	class WaitForInputThread extends Thread {

		public WaitForInputThread() {
			super();
		}

		public void run() {
			String line;
			
			while (running) {
				try {
					line = inputStream.readLine();
					System.out.println(line);
					if (line != null) {
						ResponseModel responseModel = new ResponseModel();
						if (line.contains(username)) {
							line = line.replace(username, "You");
						}
						
						if(line.contains("SERVERMESSAGE;")) {
							line = line.substring(line.indexOf("SERVERMESSAGE;")+"SERVERMESSAGE;".length());
							responseModel.setIsSeverMessage(true);
						}

						//Retrieve username
						int usernameIndex = line.indexOf(USERNAME_PARAMETER)+USERNAME_PARAMETER.length();
						int usernameCloserIndex = line.indexOf(";", usernameIndex);
						responseModel.setUsername(line.substring(usernameIndex,usernameCloserIndex));
						
						//Retrieve username attribute set
						responseModel.setUsernameAttributeSet(null);
						
						//Retrieve message
						int messageIndex = line.indexOf(MESSAGE_PARAMETER)+MESSAGE_PARAMETER.length();
						int messageIndexCloserIndex = line.indexOf(";", messageIndex);
						responseModel.setMessage(line.substring(messageIndex, messageIndexCloserIndex));
						chatView.addUserMessageToChat(responseModel);
						
						if (!responseModel.getUsername().equals(username) && !responseModel.getUsername().equals("You"))
							playNewMessageSound();
					}
				} catch (SocketException e) {

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	class SendButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			sendMessageFromInput();
		}
	}

	class EnterListener implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
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
