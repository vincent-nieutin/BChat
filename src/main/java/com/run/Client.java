package com.run;

import com.controller.LoginController;
import com.view.LoginView;

public class Client {

	public static void main(String[] args){
		LoginView connexionView = new LoginView();
		LoginController connexionController = new LoginController(connexionView);
	}
}