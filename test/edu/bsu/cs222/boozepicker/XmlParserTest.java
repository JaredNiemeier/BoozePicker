package edu.bsu.cs222.boozepicker;

import static org.junit.Assert.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.xml.sax.SAXException;

import edu.bsu.cs222.boozepicker.Bar;
import edu.bsu.cs222.boozepicker.LiquorStore;
import edu.bsu.cs222.boozepicker.XmlParser;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.junit.Before;
import org.junit.Test;

public class XmlParserTest {

	private XmlParser xmlParser;

	@Before
	public void setUp() throws SAXException, IOException, ParserConfigurationException {
		xmlParser = new XmlParser("testspecials.xml");
	}

	@Test
	public void testCountBars()
			throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {
		ArrayList<Bar> result = xmlParser.parseBars();
		assertEquals(2, result.size());
	}

	@Test
	public void testCountLiquorStores()
			throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {
		ArrayList<LiquorStore> result = xmlParser.parseLiquorStores();
		assertEquals(2, result.size());
	}

	@Test
	public void testBarNames()
			throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {
		ArrayList<Bar> result = xmlParser.parseBars();
		assertEquals("Brothers", result.get(0).getBarName());
		assertEquals("Scotty's Brewhouse", result.get(1).getBarName());
	}

	@Test
	public void testLiquorStoreNames()
			throws XPathExpressionException, SAXException, IOException, ParserConfigurationException {
		ArrayList<LiquorStore> result = xmlParser.parseLiquorStores();
		assertEquals("Muncie Liquors", result.get(0).getStoreName());
		assertEquals("Friendly Package", result.get(1).getStoreName());
	}

	@Test
	public void testCountBrothersFridaySpecials()
			throws XPathExpressionException, SAXException, IOException, ParserConfigurationException {
		ArrayList<Bar> result = xmlParser.parseBars();
		assertEquals(3, result.get(0).getDailySpecials("friday").size());
	}

	@Test
	public void testCountMuncieLiquorSpecials()
			throws XPathExpressionException, SAXException, IOException, ParserConfigurationException {
		ArrayList<LiquorStore> result = xmlParser.parseLiquorStores();
		assertEquals(2, result.get(0).getSpecialsList().size());
	}

	@Test
	public void testSpecialInfo()
			throws XPathExpressionException, SAXException, IOException, ParserConfigurationException {
		ArrayList<LiquorStore> result = xmlParser.parseLiquorStores();
		assertEquals("Captain Morgan spiced rum", result.get(0).getSpecialsList().get(0).getNameOfAlcohol());
		assertTrue(result.get(0).getSpecialsList().get(0).getPriceOfAlcohol().equals(new BigDecimal("15.00")));
		assertEquals("Rum", result.get(0).getSpecialsList().get(0).getTypeOfAlcohol());
	}
}