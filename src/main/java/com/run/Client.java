package com.run;

import com.controller.ConnectionSettingsController;
import com.controller.LoginController;
import com.view.ConnectionSettingsView;
import com.view.LoginView;

public class Client {

	public static void main(String[] args){
		ConnectionSettingsController test = new ConnectionSettingsController(new ConnectionSettingsView());
		//LoginView connexionView = new LoginView();
		//LoginController connexionController = new LoginController(connexionView);
	}
}