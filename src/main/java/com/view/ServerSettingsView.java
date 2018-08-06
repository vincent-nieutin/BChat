package com.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;

import com.gui.Button;
import com.gui.InputField;
import com.gui.InputSpinner;
import com.gui.Label;
import com.model.ServerSettingsModel;

@SuppressWarnings("serial")
public class ServerSettingsView extends DefaultView {

	protected static int WINDOW_WIDTH = 300;
	protected static int WINDOW_HEIGHT = 200;
	private static String WINDOW_NAME = "Connection settings";

	private InputField serverField;
	private InputSpinner portField;
	private Button okButton, cancelButton;

	private String server = "localhost";
	private int port = 6666;

	public ServerSettingsView() {
		super(WINDOW_NAME, WINDOW_WIDTH, WINDOW_HEIGHT);
		setServer(this.server);
		setPort(this.port);
	}

	@Override
	protected void build() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.gridwidth = 2;

		// Server label
		gbc.gridx = 1;
		gbc.gridy = 0;
		Label serverLabel = new Label("Server");
		mainPanel.add(serverLabel, gbc);

		// Server field
		gbc.gridx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 1;
		serverField = new InputField(server);
		serverField.setPreferredSize(new Dimension(200, 25));
		mainPanel.add(serverField, gbc);

		// Port label
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.gridx = 1;
		gbc.gridy = 2;
		Label portLabel = new Label("Port");
		mainPanel.add(portLabel, gbc);

		// Port field
		gbc.gridx = 1;
		gbc.gridy = 3;
		portField = new InputSpinner(port, 0, 65535, 1);
		portField.setEditor(new JSpinner.NumberEditor(portField, "#"));
		portField.setBorder(new EmptyBorder(0, 1, 0, 0));
		portField.setPreferredSize(new Dimension(55, 25));
		mainPanel.add(portField, gbc);
		
		// Ok button
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.insets = new Insets(15, 1, 0, 1);
		okButton = new Button("Ok");
		mainPanel.add(okButton, gbc);

		// Cancel button
		gbc.gridx = 2;
		gbc.gridy = 4;
		cancelButton = new Button("Cancel");
		mainPanel.add(cancelButton, gbc);

		this.add(mainPanel);
	}

	public ServerSettingsModel getServerSettings() {
		ServerSettingsModel settingsModel = new ServerSettingsModel();
		settingsModel.setServer(getServer());
		settingsModel.setPort(getPort());
		return settingsModel;
	}

	public void setServer(String server) {
		this.server = server;
		serverField.setText(server);
	}

	public String getServer() {
		String server = serverField.getText();
		if (server.equals("")) {
			errorView = new ErrorView("No server specified", "Please fill the server field.");
			return null;
		}
		return server;
	}

	public void setPort(int port) {
		this.port = port;
		portField.setValue(port);
	}

	public int getPort() {
		return (Integer) portField.getValue();
	}

	public void addOkButtonListener(ActionListener okButtonListener) {
		okButton.addActionListener(okButtonListener);
	}
	
	public void addCancelButtonListener(ActionListener cancelButtonListener) {
		cancelButton.addActionListener(cancelButtonListener);
	}
}
