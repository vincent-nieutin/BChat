package com.gui;

import java.awt.Font;

import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;

import com.model.ResponseModel;

@SuppressWarnings("serial")
public class TextArea extends JTextPane {
	
	private static String USERNAME_SEPARATOR = ">";
	
	public TextArea() {
		super();
		this.setBorder(new EmptyBorder(0, 0, 0, 0));
		this.setFont(new Font("Tahoma", 0, 14));
		this.setEditable(false);
		// this.setBackground(new Color(135, 194, 255));
	}

	public void addMessage(ResponseModel responseModel) {
		StyledDocument styledDocument = this.getStyledDocument();
		try {
			if(!responseModel.isSeverMessage()) {
			styledDocument.insertString(styledDocument.getLength(), responseModel.getUsername() + USERNAME_SEPARATOR + " ", responseModel.getUsernameAttributeSet());
			}
			styledDocument.insertString(styledDocument.getLength(), responseModel.getMessage() + "\n", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
