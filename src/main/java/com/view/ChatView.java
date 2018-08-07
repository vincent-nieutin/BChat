package com.view;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.controller.XmlFileController;
import com.gui.Button;
import com.gui.InputField;
import com.gui.TextArea;
import com.model.ResponseModel;

public class ChatView extends JFrame{
	
	private static int WINDOW_WIDTH = 500;
	private static int WINDOW_HEIGHT = 600;
	private static int HEADER_PANEL_HEIGHT = 50;
	
	private TextArea chat;
	private InputField inputField;
	private Button exitButton, usersButton, sendButton;
	private XmlFileController settingsModel;
	
	public ChatView() {
		super("BChat");
		
		this.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS)); 
		
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
		headerPanel.setPreferredSize(new Dimension(WINDOW_WIDTH, HEADER_PANEL_HEIGHT));
		headerPanel.setBackground(new Color(0, 115, 230));
		
		usersButton = new Button("Users");
		headerPanel.add(usersButton);
		
		exitButton = new Button("Exit");
		headerPanel.add(exitButton);
		
		contentPanel.add(headerPanel);
		
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		mainPanel.setBackground(new Color(198, 226, 255));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0,0,0,0);
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		chat = new TextArea();
		chat.setPreferredSize(new Dimension(400,400));
		mainPanel.add(chat,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		inputField = new InputField(); 
		inputField.setPreferredSize(new Dimension(400,30));
		inputField.setHorizontalAlignment(JTextField.LEFT);
		mainPanel.add(inputField, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		
		sendButton = new Button("Send");
		sendButton.setPreferredSize(new Dimension(70,30));
		//mainPanel.add(sendButton,gbc);
		
		contentPanel.add(mainPanel);
		
		this.add(contentPanel);
		
		this.setVisible(true);
		
		inputField.requestFocus();
	}
	
	public void addSendListener(ActionListener send) {
		sendButton.addActionListener(send);
	}
	
	public void addEnterKeyListener(KeyListener enter) {
		inputField.addKeyListener(enter);
	}
	
	public void addUserMessageToChat(ResponseModel responseModel) {
		chat.addMessage(responseModel);
	}
	
	public String getInputText() {
		String result = inputField.getText();
		inputField.setText(null);
		return result;
	}
}
