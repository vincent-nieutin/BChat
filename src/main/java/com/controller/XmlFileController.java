package com.controller;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.model.SettingsModel;

public class XmlFileController {
	// private static String FILE = "src/main/ressources/settings.xml";
	private static String FILE = "settings.xml";
	private static String PATH = "/ressources/persistence/";
	//private URL settingsUrl = getClass().getResource(PATH+FILE);//System.getProperty("user.home") + "/bchat/";
	private File settingsFile;

	public XmlFileController() {
		System.out.println();
		if(!Files.exists(Paths.get(PATH+FILE)))
			createSettingsFile();
				
		settingsFile = new File(PATH+FILE);
	}

	public SettingsModel getSettings() {
		SettingsModel settingsModel = new SettingsModel();
		String username = null;
		String server = "localhost";
		int port = 6666;

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(settingsFile);

			doc.getDocumentElement().normalize();

			username = doc.getElementsByTagName("username").item(0).getTextContent();
			server = doc.getElementsByTagName("server").item(0).getTextContent();
			port = Integer.parseInt(doc.getElementsByTagName("port").item(0).getTextContent());
		} catch (Exception e) {
			e.printStackTrace();
		}
		settingsModel.setServer(server);
		settingsModel.setPort(port);
		settingsModel.setUsername(username);

		return settingsModel;
	}

	public void saveSettings(SettingsModel settingsModel) {
		DocumentBuilderFactory dbFactory;
		DocumentBuilder dBuilder;
		Document doc;

		try {
			dbFactory = DocumentBuilderFactory.newInstance();
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.newDocument();

			Element rootElement = doc.createElement("settings");
			doc.appendChild(rootElement);

			Element usernameNode = doc.createElement("username");
			usernameNode.appendChild(doc.createTextNode(settingsModel.getUsername()));
			rootElement.appendChild(usernameNode);

			Element serverNode = doc.createElement("server");
			serverNode.appendChild(doc.createTextNode(settingsModel.getServer()));
			rootElement.appendChild(serverNode);

			Element portNode = doc.createElement("port");
			portNode.appendChild(doc.createTextNode(Integer.toString(settingsModel.getPort())));
			rootElement.appendChild(portNode);

			doc.getDocumentElement().normalize();

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(PATH + FILE));

			transformer.transform(source, result);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createSettingsFolder() {
		new File(PATH).mkdirs();
	}

	private void createSettingsFile() {
		SettingsModel settingsModel = new SettingsModel();
		settingsModel.setServer("localhost");
		settingsModel.setPort(6666);
		settingsModel.setUsername(null);
		saveSettings(settingsModel);
	}
}
