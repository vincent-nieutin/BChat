package com.gui;

import java.awt.Font;

import javax.swing.*;
import javax.swing.border.*;

public class TextDisplay extends JTextField {
	
	public TextDisplay(String text){
		super(text);
		this.setBorder(new EmptyBorder(0,0,0,0));
		this.setFont(new Font("Tahoma", 0, 14));
		this.setEditable(false);
	}
}
