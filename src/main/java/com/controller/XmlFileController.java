package com.controller;

import java.io.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

import com.run.Client;

public class XmlFileController {
	private static String FILE = "src/main/ressources/settings.xml";
	private File settingsFile = new File(FILE);
	
	public void getSettings() {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(settingsFile);
			
			doc.getDocumentElement().normalize();
			
			String username = doc.getElementsByTagName("username").item(0).getTextContent();
			String server = doc.getElementsByTagName("server").item(0).getTextContent();
			int port = Integer.parseInt(doc.getElementsByTagName("port").item(0).getTextContent());
			
			System.out.println("Username: " + username);
			System.out.println("Server: " + server);
			System.out.println("Port: " + port);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void setSettings(String username, String server, int port) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(settingsFile);
			
			doc.getDocumentElement().normalize();
			
			doc.getElementsByTagName("username").item(0).setTextContent(username);
			doc.getElementsByTagName("server").item(0).setTextContent(server);
			doc.getElementsByTagName("port").item(0).setTextContent(Integer.toString(port));
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(FILE);
			
			transformer.transform(source, result);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
