package com.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.gui.Button;
import com.gui.InputField;
import com.gui.Label;
import com.model.ConnectionModel;

@SuppressWarnings("serial")
public class LoginView extends DefaultView {

	protected static int WINDOW_WIDTH = 400;
	protected static int WINDOW_HEIGHT = 220;
	private static String WINDOW_NAME = "Chat connection";
	private static String SETTINGS_BUTTON_NAME = "Settings";

	private InputField usernameField;
	private Button connectButton, settingsButton;

	public LoginView(String username) {
		super(WINDOW_NAME, WINDOW_WIDTH, WINDOW_HEIGHT);
		if(username == null)
			username = "Guest" + new Random().nextInt(99);
		usernameField.setText(username);
	}

	@Override
	protected void build() {

		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

		// Banner panel
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
		headerPanel.setPreferredSize(new Dimension(WINDOW_WIDTH, 30));
		headerPanel.setBackground(new Color(0, 115, 230));

		// Server settings button
		settingsButton = new Button(SETTINGS_BUTTON_NAME);
		settingsButton.setPreferredSize(new Dimension(79, 20));
		settingsButton.setFont(new Font("Tahoma", Font.CENTER_BASELINE, 11));
		headerPanel.add(settingsButton);

		contentPanel.add(headerPanel);

		// Main Panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		// GBC initial settings
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.fill = GridBagConstraints.VERTICAL;

		// Username label
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(20, 0, 0, 0);
		Label usernameLabel = new Label("Username");
		mainPanel.add(usernameLabel, gbc);

		// Username field
		gbc.gridx = 0;
		gbc.gridy = 2;
		usernameField = new InputField("");
		usernameField.setPreferredSize(new Dimension(200, 25));
		mainPanel.add(usernameField, gbc);
		
		// Connect button
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.insets = new Insets(20, 0, 0, 0);
		connectButton = new Button("Connect");
		mainPanel.add(connectButton, gbc);

		contentPanel.add(mainPanel);

		this.add(contentPanel);
		
		usernameField.requestFocus();
	}

	public ConnectionModel getSettings() {
		ConnectionModel settingsModel = new ConnectionModel();

		String username = usernameField.getText();

		if (username.equals("")) {
			errorView = new ErrorView("No username specified", "Please fill the username field.");
			return null;
		}
		settingsModel.setUsername(username);

		return settingsModel;
	}

	public void addConnectButtonListener(ActionListener connectButtonListener) {
		connectButton.addActionListener(connectButtonListener);
	}

	public void addSettingsButtonListener(ActionListener settingsButtonListener) {
		settingsButton.addActionListener(settingsButtonListener);
	}
	
	public void addEnterKeyListener(KeyListener enter) {
		usernameField.addKeyListener(enter);
	}
}
