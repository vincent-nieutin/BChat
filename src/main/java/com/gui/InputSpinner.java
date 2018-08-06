package com.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class InputSpinner extends JSpinner {
	
	private static Color FOCUS_COLOR = new Color(64, 191, 64); 
	
	public InputSpinner(int value, int min, int max, int step){
		super(new SpinnerNumberModel(value, min, max, step));
		this.setFont(new Font("Tahoma", 0, 14));
		this.addFocusListener(new FieldFocusListener());
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
