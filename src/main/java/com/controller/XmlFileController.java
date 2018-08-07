package com.controller;

import java.awt.Color;
import java.io.File;
import java.nio.file.Files;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.model.ConnectionModel;

public class XmlFileController {
	private static String FILE = "settings.xml";
	private static String PATH = "/ressources/persistence/";
	private File settingsFile;

	public XmlFileController() {
		System.out.println();

		settingsFile = new File(PATH + FILE);

		if (!Files.exists(settingsFile.toPath()))
			createSettingsFile();
	}

	public ConnectionModel getSettings() {
		ConnectionModel settingsModel = new ConnectionModel();
		String username = null;
		String server = "localhost";
		int port = 6666;
		Color textColor = Color.BLACK;

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(settingsFile);

			doc.getDocumentElement().normalize();

			username = doc.getElementsByTagName("username").item(0).getTextContent();
			server = doc.getElementsByTagName("server").item(0).getTextContent();
			port = Integer.parseInt(doc.getElementsByTagName("port").item(0).getTextContent());

			// Extract color
			String rgbColor = doc.getElementsByTagName("color").item(0).getTextContent();

			// Get r
			int rIndex = rgbColor.indexOf("r=") + "r=".length();
			int rCloserIndex = rgbColor.indexOf(",", rIndex);
			int r = Integer.parseInt(rgbColor.substring(rIndex, rCloserIndex));

			// Get g
			int gIndex = rgbColor.indexOf("g=") + "g=".length();
			int gCloserIndex = rgbColor.indexOf(",", gIndex);
			int g = Integer.parseInt(rgbColor.substring(gIndex, gCloserIndex));

			// Get b
			int bIndex = rgbColor.indexOf("b=") + "b=".length();
			int bCloserIndex = rgbColor.indexOf("]", bIndex);
			int b = Integer.parseInt(rgbColor.substring(bIndex, bCloserIndex));

			textColor = new Color(r, g, b);

		} catch (Exception e) {
			e.printStackTrace();
		}
		settingsModel.setServer(server);
		settingsModel.setPort(port);
		settingsModel.setUsername(username);
		settingsModel.setColor(textColor);

		return settingsModel;
	}

	public void saveSettings(ConnectionModel settingsModel) {
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

			Element colorNode = doc.createElement("color");
			colorNode.appendChild(doc.createTextNode(settingsModel.getColorAsString()));
			rootElement.appendChild(colorNode);
			System.out.println("Saved color: " + settingsModel.getColorAsString());
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

	private void createSettingsFile() {
		ConnectionModel settingsModel = new ConnectionModel();
		settingsModel.setServer("localhost");
		settingsModel.setPort(6666);
		settingsModel.setUsername(null);
		saveSettings(settingsModel);
		System.out.println("Settings file created");
	}
}
