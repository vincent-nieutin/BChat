package com.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.model.ResponseModel;

@SuppressWarnings("serial")
public class TextPane extends JTextPane {
	
	private static String USERNAME_SEPARATOR = ">";
	private static Color SERVER_MESSAGE_COLOR = Color.RED;
	private static Color SYSTEM_COLOR = Color.BLACK;
	private static Color SELF_COLOR = new Color(26, 26, 255);
	
	public TextPane() {
		super();
		this.setBorder(new EmptyBorder(0, 0, 0, 0));
		this.setFont(new Font("Tahoma", 0, 14));
		this.setEditable(false);
	}

	public void addMessage(ResponseModel responseModel) {
		String username = responseModel.getUsername();
		String message  = responseModel.getMessage();
		
		StyledDocument styledDocument = this.getStyledDocument();
		
		SimpleAttributeSet attributeSet = new SimpleAttributeSet();
		
		try {
			if(responseModel.isSeverMessage())
				StyleConstants.setForeground(attributeSet, SERVER_MESSAGE_COLOR);
			
			//Display username
			if(username.equals("You") && !responseModel.isSeverMessage()) {
				StyleConstants.setForeground(attributeSet, SELF_COLOR);
			}
			
			styledDocument.insertString(styledDocument.getLength(), username, attributeSet);
			
			//Display separator if not server message
			if(!responseModel.isSeverMessage())
				styledDocument.insertString(styledDocument.getLength(), USERNAME_SEPARATOR, attributeSet);
				
			//Display message
			styledDocument.insertString(styledDocument.getLength(), " " + message + "\n", attributeSet);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setCaretPosition(this.getDocument().getLength());
	}
}
