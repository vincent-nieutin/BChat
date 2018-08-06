package com.view;

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

public class LoginView extends JFrame {

	private static int windowWidth = 300;
	private static int windowHeight = 300;

	private InputField serverField;
	private InputSpinner portField;
	private InputField usernameField;
	private Button connectButton;

	// Initial config
	private String server = "localhost";
	private int port = 6666;
	private String username = "Guest" + new Random().nextInt(99);
	private ErrorView errorView;

	public LoginView() {
		super("Connect");

		this.setSize(windowWidth, windowHeight);
		this.setLocationRelativeTo(null);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		build();
		this.setVisible(true);
	}

	private void build() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		JPanel decorativePanel = new JPanel();
		decorativePanel.setBackground();
		this.add(decorativePanel);

		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.gridx = 0;
		gbc.gridy = 0;

		// Server label
		Label serverLabel = new Label("Server");
		mainPanel.add(serverLabel, gbc);

		// Server field
		gbc.gridy++;
		serverField = new InputField(server);
		serverField.setPreferredSize(new Dimension(200, 25));
		mainPanel.add(serverField, gbc);

		// Port label
		gbc.gridy++;
		Label portLabel = new Label("Port");
		mainPanel.add(portLabel, gbc);

		// Port field
		gbc.gridy++;
		portField = new InputSpinner(port, 0, 65535, 1);
		portField.setEditor(new JSpinner.NumberEditor(portField, "#"));
		portField.setBorder(new EmptyBorder(0, 1, 0, 0));
		portField.setPreferredSize(new Dimension(55, 25));
		mainPanel.add(portField, gbc);

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
		connectButton.setPreferredSize(new Dimension(90, 30));
		mainPanel.add(connectButton, gbc);

		this.add(mainPanel);
	}

	public SettingsModel getSettings() {
		SettingsModel settingsModel = new SettingsModel();
		
		String server = serverField.getText();
		int port = (Integer) portField.getValue();
		String username = usernameField.getText();
		
		if (server.equals("")) {
			errorView = new ErrorView("No server specified", "Please fill the server field.");
			return null;
		}
		settingsModel.setServer(server);
		
		settingsModel.setPort(port);

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
