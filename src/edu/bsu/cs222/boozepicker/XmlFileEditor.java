package edu.bsu.cs222.boozepicker;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlFileEditor {

	private final DayOfWeek[] DAYS_OF_WEEK = DayOfWeek.values();
	private Document doc;
	private Node nodeBeingUpdated;
	private Element newDayNode;

	public XmlFileEditor(String file) {
		try {
			InputStream fileInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(file);
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			doc = documentBuilder.parse(fileInputStream);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			throw new FileUpdateException();
		}
	}

	public void updateScottysSpecials(HashMap<DayOfWeek, List<Special>> specialsList) {
		nodeBeingUpdated = doc.getElementsByTagName("bar").item(1);
		removeOldNodes();
		addNewNodes(specialsList);
	}

	private void removeOldNodes() {
		NodeList nodeList = nodeBeingUpdated.getChildNodes();
		for (int i = 0; i < nodeList.getLength();) {
			nodeBeingUpdated.removeChild(nodeList.item(i));
		}
	}

	private void addNewNodes(HashMap<DayOfWeek, List<Special>> specialsHashMap) {
		for (DayOfWeek day : DAYS_OF_WEEK) {
			List<Special> specialsList = specialsHashMap.get(day);
			newDayNode = doc.createElement("day");
			newDayNode.setAttribute("name", day.toString());
			nodeBeingUpdated.appendChild(newDayNode);
			addSpecialNodesToDayNode(specialsList);
		}
	}

	private void addSpecialNodesToDayNode(List<Special> specialList) {
		for (int i = 0; i < specialList.size(); i++) {
			Element specialNode = doc.createElement("special");
			specialNode.setAttribute("item", specialList.get(i).getNameOfAlcohol());
			specialNode.setAttribute("price", specialList.get(i).getPriceOfAlcohol().toString());
			specialNode.setAttribute("type", specialList.get(i).getTypeOfAlcohol());
			newDayNode.appendChild(specialNode);
		}
	}
	
	public void writeUpdatedFile(String destination) {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(destination));
			transformer.transform(source, result);
		} catch (TransformerException e) {
			throw new FileUpdateException();
		}
	}
}