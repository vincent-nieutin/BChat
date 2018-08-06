package com.view;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.controller.XmlFileController;
import com.gui.Button;
import com.gui.TextArea;

public class ChatView extends JFrame{
	
	private TextArea chat;
	private TextField inputField;
	private Button sendButton;
	private XmlFileController settingsModel;
	
	public ChatView() {
		super("BChat");
		
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0,0,0,0);
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		chat = new TextArea();
		chat.setPreferredSize(new Dimension(400,400));
		chat.setEditable(false);
		mainPanel.add(chat,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		inputField = new TextField(); 
		inputField.setPreferredSize(new Dimension(400,30));
		mainPanel.add(inputField, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		
		sendButton = new Button("Send");
		sendButton.setPreferredSize(new Dimension(70,30));
		//mainPanel.add(sendButton,gbc);
		
		this.add(mainPanel);
		
		this.setVisible(true);
		
		inputField.requestFocus();
	}
	
	public void addSendListener(ActionListener send) {
		sendButton.addActionListener(send);
	}
	
	public void addEnterKeyListener(KeyListener enter) {
		inputField.addKeyListener(enter);
	}
	
	public void addToChat(String text) {
		chat.append(text + "\n");
	}
	
	public String getInputText() {
		String result = inputField.getText();
		inputField.setText(null);
		return result;
	}
}
