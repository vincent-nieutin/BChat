package com.gui;

import java.awt.Font;

import javax.swing.JLabel;

public class Label extends JLabel{
	
	public Label(String text) {
		super(text);
		this.setFont(new Font("Tahoma", 0, 16));
	}
}
