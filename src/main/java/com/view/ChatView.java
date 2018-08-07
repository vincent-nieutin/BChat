package com.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.controller.XmlFileController;
import com.gui.Button;
import com.gui.InputField;
import com.gui.ScrollPane;
import com.gui.TextPane;
import com.model.ResponseModel;

@SuppressWarnings("serial")
public class ChatView extends JFrame {

	private static int WINDOW_WIDTH = 500;
	private static int WINDOW_HEIGHT = 600;
	private static int HEADER_PANEL_HEIGHT = 50;

	private TextPane chat;
	private InputField inputField;
	private Button exitButton, usersButton, sendButton;
	private XmlFileController settingsModel;

	public ChatView() {
		super("BChat");

		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
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
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.gridx = 0;
		gbc.gridy = 0;

		chat = new TextPane();
		//chat.setPreferredSize(new Dimension(400, 400));
		
		// mainPanel.add(chat,gbc);
		ScrollPane scrollPane = new ScrollPane(chat);
		scrollPane.setPreferredSize(new Dimension(400, 400));
		mainPanel.add(scrollPane);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		inputField = new InputField();
		inputField.setPreferredSize(new Dimension(400, 30));
		inputField.setHorizontalAlignment(JTextField.LEFT);
		mainPanel.add(inputField, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;

		sendButton = new Button("Send");
		sendButton.setPreferredSize(new Dimension(70, 30));
		// mainPanel.add(sendButton,gbc);

		contentPanel.add(mainPanel);

		this.add(contentPanel);

		this.setVisible(true);

		inputField.requestFocus();
	}

	public void addSendButtonListener(ActionListener send) {
		sendButton.addActionListener(send);
	}
	
	public void addExitButtonListener(ActionListener send) {
		exitButton.addActionListener(send);
	}

	public void addEnterKeyListener(KeyListener enter) {
		inputField.addKeyListener(enter);
	}

	public void addMessageToChat(ResponseModel responseModel) {
		chat.addMessage(responseModel);
	}

	public String getInputText() {
		String result = inputField.getText();
		inputField.setText(null);
		return result;
	}
}
