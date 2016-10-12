package edu.bsu.cs222.boozepicker;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlParser {

	private Document doc;
	private Bar bar;
	private Special.Builder specialBuilder = new Special.Builder();

	public XmlParser(String fileName) {
		doc = readFileAsXmlDocument(fileName);
	}

	private Document readFileAsXmlDocument(String fileName) {
		try {
			InputStream sampleFileInputStream = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(fileName);
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			return documentBuilder.parse(sampleFileInputStream);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			throw new XmlParserException();
		}
	}

	public ArrayList<Bar> parseBars() {
		Node bars = goToNode("bars");
		return getBars(bars);
	}

	public ArrayList<LiquorStore> parseLiquorStores() {
		Node stores = goToNode("stores");
		return getLiquorStores(stores);
	}

	private Node goToNode(String nodeName) {
		try {
			XPathExpression xPathExpression = getNodePath("boozesales/" + nodeName);
			return (Node) xPathExpression.evaluate(doc, XPathConstants.NODE);
		} catch (XPathExpressionException e) {
			throw new XmlParserException();
		}
	}

	private XPathExpression getNodePath(String path) {
		try {
			XPath xpath = XPathFactory.newInstance().newXPath();
			String expression = path;
			return xpath.compile(expression);
		} catch (XPathExpressionException e) {
			throw new XmlParserException();
		}
	}

	private ArrayList<Bar> getBars(Node barsNode) {
		NodeList barNodesList = barsNode.getChildNodes();
		return makeBarsList(barNodesList);
	}

	private ArrayList<Bar> makeBarsList(NodeList barNodesList) {
		ArrayList<Bar> barsList = new ArrayList<Bar>();
		for (int i = 0; i < barNodesList.getLength(); i++) {
			makeBar(barNodesList.item(i));
			barsList.add(bar);
		}
		return barsList;
	}

	private void makeBar(Node barNode) {
		bar = new Bar(barNode.getAttributes().getNamedItem("name").getNodeValue());
		NodeList dayNodeList = barNode.getChildNodes();
		for (int i = 0; i < dayNodeList.getLength(); i++) {
			addBarDailySpecials(dayNodeList.item(i));
		}
	}

	private ArrayList<LiquorStore> getLiquorStores(Node stores) {
		NodeList storeNodeList = stores.getChildNodes();
		return makeLiquorStoreList(storeNodeList);
	}

	private ArrayList<LiquorStore> makeLiquorStoreList(NodeList storeNodeList) {
		ArrayList<LiquorStore> storeList = new ArrayList<LiquorStore>();
		for (int i = 0; i < storeNodeList.getLength(); i++) {
			storeList.add(makeLiquorStore(storeNodeList.item(i)));
		}
		return storeList;
	}

	private LiquorStore makeLiquorStore(Node storeNode) {
		LiquorStore store = new LiquorStore(storeNode.getAttributes().getNamedItem("name").getNodeValue());
		NodeList specialsNodeList = storeNode.getChildNodes();
		for (int i = 0; i < specialsNodeList.getLength(); i++) {
			store.addSpecial(makeSpecial(specialsNodeList.item(i)));
		}
		return store;
	}

	private void addBarDailySpecials(Node day) {
		bar.setCurrentDay(day.getAttributes().getNamedItem("name").getNodeValue());
		NodeList specialsNodeList = day.getChildNodes();
		for (int i = 0; i < specialsNodeList.getLength(); i++) {
			bar.addSpecial(makeSpecial(specialsNodeList.item(i)));
		}
	}

	private Special makeSpecial(Node specialNode) {
		NamedNodeMap specialAttributes = specialNode.getAttributes();
		specialBuilder.setNameOfAlcohol(specialAttributes.getNamedItem("item").getNodeValue());
		specialBuilder.setPriceOfAlcohol(specialAttributes.getNamedItem("price").getNodeValue());
		specialBuilder.setTypeOfAlcohol(specialAttributes.getNamedItem("type").getNodeValue());
		return specialBuilder.build();
	}
}