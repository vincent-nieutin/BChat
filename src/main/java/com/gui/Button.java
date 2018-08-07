package com.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Button extends JButton {
	private static int BUTTON_WIDTH = 100;
	private static int BUTTON_HEIGTH = 40;
	
	private Color defaultColor = new Color(0, 115, 230);
	private Color hoverColor = new Color(0, 115, 180);
	private Color clickColor = new Color(0, 65, 130);

	public Button(String text) {
		super(text);
		this.setContentAreaFilled(false);
		this.setFocusPainted(false);
		this.setOpaque(true);
		this.setPreferredSize(new Dimension(BUTTON_WIDTH,BUTTON_HEIGTH));
		this.setBackground(defaultColor);
		this.setForeground(Color.WHITE);
		this.setFont(new Font("Tahoma", Font.CENTER_BASELINE, 15));
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));

		this.addMouseListener(new CustomMouseAdapter());
	}
	
	public class CustomMouseAdapter extends MouseAdapter{
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
	}
}