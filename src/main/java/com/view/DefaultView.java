package com.view;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

@SuppressWarnings("serial")
public class DefaultView extends JFrame{
	
	protected static ErrorView errorView;
	
	public DefaultView(String WindowName, int width, int height, boolean isVisible) {
		super(WindowName);	
		this.setSize(width,height);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		/*try {
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
		}*/
		build();
		this.setVisible(isVisible);
	}
	
	public DefaultView(String WindowName, int width, int height) {
		this(WindowName, width, height, true);
	}
	
	protected void build() {
		
	}
}
