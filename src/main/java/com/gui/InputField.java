package com.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.*;

@SuppressWarnings("serial")
public class InputField extends JTextField{
	
	private static Color FOCUS_COLOR = new Color(64, 191, 64); 
	private static int PADDING = 5;
	
	public InputField(String text){
		super(text);
		this.setBorder(BorderFactory.createCompoundBorder(this.getBorder(), new EmptyBorder(0,PADDING,0,PADDING)));
		this.setFont(new Font("Tahoma", 0, 14));
		this.setHorizontalAlignment(JTextField.CENTER);
		//this.addFocusListener(new FieldFocusListener());
	}
	
	public InputField() {
		this("");
	}
	
	class FieldFocusListener implements FocusListener{

		@Override
		public void focusGained(FocusEvent arg0) {
			setBorder(new LineBorder(FOCUS_COLOR, 1));
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			setBorder(new EmptyBorder(0,0,0,0));
		}
		
	}
}
