package com.run;

import com.controller.LoginController;
import com.view.LoginView;

public class Client {
	
	private static String VERSION = "1.0.0";
	
	public static void main(String[] args) {
		System.out.println("BChat v" + VERSION);
		new LoginController();
	}
}