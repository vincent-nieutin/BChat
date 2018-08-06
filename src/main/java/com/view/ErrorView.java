package com.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

import com.gui.Button;
import com.gui.TextDisplay;
import com.gui.Label;
import com.model.*;

public class ErrorView extends JFrame{
	
	private static int windowWidth = 450;
	private static int windowHeight = 250;
	private String error = "";
	private String errorType = "";
	
	public ErrorView(String errorType, String error){
		super("Error");
		this.error = error;
		this.errorType = errorType;
		this.setSize(windowWidth, windowHeight);
		this.setUndecorated(true);
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
	
	public void build() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBorder(new LineBorder(new Color(0,115,230),3));
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0,0,50,0);
		//Error label
		Label label1 = new Label(errorType);
		label1.setFont(new Font("Tahoma", 0, 30));
		label1.setForeground(Color.RED);
		panel.add(label1, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(0,0,20,0);
		//Error text display
		TextDisplay field1 = new TextDisplay(error);
		field1.setSize(200, 200);
		field1.setFont(new Font("Tahoma", 0,20));
		panel.add(field1, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.insets = new Insets(0,0,0,0);
		//Button
		Button button = new Button("Ok");
		button.setPreferredSize(new Dimension(60,30));
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
			
		});
		panel.add(button, gbc);
		
		this.add(panel);
	}
}
