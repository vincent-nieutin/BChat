package com.gui;

import java.awt.Font;

import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class TextArea extends JTextArea {
	
	public TextArea(){
		super();
		this.setBorder(new EmptyBorder(0,0,0,0));
		this.setFont(new Font("Tahoma", 0, 14));
		this.setEditable(false);
	}
	
	public void addText(String text) {
		this.append(text + "\n");
	}
}
