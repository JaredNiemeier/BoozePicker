package edu.bsu.cs222.boozepicker;

import static org.junit.Assert.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import edu.bsu.cs222.boozepicker.Bar;
import edu.bsu.cs222.boozepicker.Special;
import edu.bsu.cs222.boozepicker.WebReader;
import edu.bsu.cs222.boozepicker.XmlFileEditor;
import edu.bsu.cs222.boozepicker.XmlParser;

public class UpdateFileTest {

	private WebReader webReader;
	private XmlFileEditor xmlEditor;
	private XmlParser parser;

	@Before
	public void setUp() throws IOException, SAXException, ParserConfigurationException {
		webReader = new WebReader("assets/scottyspecials.html", "http://www.scottysbrewhouse.com/specials/");
		xmlEditor = new XmlFileEditor("Boozespecials.xml");
	}

	@Test
	public void testWriteToNewFile() {
		xmlEditor.updateScottysSpecials(webReader.getNewSpecials());
		xmlEditor.writeUpdatedFile("assets/xmlResult.xml");
		parser = new XmlParser("xmlResult.xml");
		List<Bar> bars = parser.parseBars();
		Bar scottys = bars.get(1);
		List<Special> list = scottys.getDailySpecials("monday");
		assertEquals("moscow mules", list.get(0).getNameOfAlcohol());
		assertTrue(list.get(0).getPriceOfAlcohol().equals(new BigDecimal("5.00")));
		assertEquals("Unkown", list.get(0).getTypeOfAlcohol());
	}

	@Test
	public void testScottysHasAllDays() {
		xmlEditor.updateScottysSpecials(webReader.getNewSpecials());
		xmlEditor.writeUpdatedFile("assets/xmlResult.xml");
		parser = new XmlParser("xmlResult.xml");
		List<Bar> bars = parser.parseBars();
		Bar scottys = bars.get(1);
		assertTrue(!scottys.getDailySpecials("monday").isEmpty());
		assertTrue(!scottys.getDailySpecials("tuesday").isEmpty());
		assertTrue(!scottys.getDailySpecials("wednesday").isEmpty());
		assertTrue(!scottys.getDailySpecials("thursday").isEmpty());
		assertTrue(!scottys.getDailySpecials("friday").isEmpty());
		assertTrue(!scottys.getDailySpecials("saturday").isEmpty());
		assertTrue(!scottys.getDailySpecials("sunday").isEmpty());
	}
}