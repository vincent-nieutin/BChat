package com.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.border.*;

public class Button extends JButton{
	private Color defaultColor = new Color(0,115,230);
	private Color hoverColor = new Color(0,115,180);
	private Color clickColor = new Color(0,65,130);
	
	public Button(String text) {
		super(text);
		this.setContentAreaFilled(false);
		this.setFocusPainted(false);
		this.setOpaque(true);
		
		this.setBackground(defaultColor);
		this.setForeground(Color.WHITE);
		this.setFont(new Font("Tahoma", Font.CENTER_BASELINE, 13));
		//this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(hoverColor);
			}
	
			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(defaultColor);
			}
	
			@Override
			public void mousePressed(MouseEvent e) {
				setBackground(clickColor);
			}
	
			@Override
			public void mouseReleased(MouseEvent e) {
				setBackground(defaultColor);
			}
		});
	}
}