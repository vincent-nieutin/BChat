package com.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.gui.Button;
import com.gui.InputField;
import com.gui.InputSpinner;
import com.gui.Label;
import com.model.SettingsModel;

public class LoginView extends DefaultView {
	
	protected static int WINDOW_WIDTH = 300;
	protected static int WINDOW_HEIGHT = 200;
	private static String WINDOW_NAME = "Connection";
	
	private InputField usernameField;
	private Button connectButton;

	// Initial config
	private String username = "Guest" + new Random().nextInt(99);

	public LoginView() {
		super(WINDOW_NAME, WINDOW_WIDTH, WINDOW_HEIGHT);
	}
	
	@Override
	protected void build() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.gridx = 0;
		gbc.gridy = 0;

		// Username label
		gbc.gridx--;
		gbc.gridy++;
		gbc.insets = new Insets(20, 0, 0, 0);
		Label usernameLabel = new Label("Username");
		mainPanel.add(usernameLabel, gbc);

		gbc.insets = new Insets(0, 0, 0, 0);

		// Username field
		gbc.gridx = 0;
		gbc.gridy++;
		usernameField = new InputField(username);
		usernameField.setPreferredSize(new Dimension(200, 25));
		mainPanel.add(usernameField, gbc);

		// Connect button
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.insets = new Insets(20, 0, 0, 0);
		connectButton = new Button("Connect");
		mainPanel.add(connectButton, gbc);

		this.add(mainPanel);
	}

	public SettingsModel getSettings() {
		SettingsModel settingsModel = new SettingsModel();
		
		String username = usernameField.getText();
		
		if (username.equals("")) {
			errorView = new ErrorView("No username specified", "Please fill the username field.");
			return null;
		}
		settingsModel.setUsername(username);
		
		return settingsModel;
	}

	public void addConnectListener(ActionListener connect) {
		connectButton.addActionListener(connect);
	}
}
