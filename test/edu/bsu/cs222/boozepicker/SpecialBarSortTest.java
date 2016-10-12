package edu.bsu.cs222.boozepicker;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

import edu.bsu.cs222.boozepicker.Bar;
import edu.bsu.cs222.boozepicker.Special;
import edu.bsu.cs222.boozepicker.SpecialBar;
import edu.bsu.cs222.boozepicker.SpecialPriceComparator;
import edu.bsu.cs222.boozepicker.SpecialTypeComparator;
import edu.bsu.cs222.boozepicker.XmlParser;

public class SpecialBarSortTest {

	private SpecialBar testSpecials;
	private ArrayList<Special> fridaySpecials;

	@Before
	public void setUp() {
		XmlParser parser = new XmlParser("Boozespecialstestfile.xml");
		Bar brothersBar = parser.parseBars().get(0);
		testSpecials = new SpecialBar(brothersBar);
		fridaySpecials = testSpecials.getBarFridaySpecials();
	}

	@Test
	public void testPriceSortSpecials() {
		testSpecials.getBarFridaySpecials().sort(new SpecialPriceComparator());
		fridaySpecials = testSpecials.getBarFridaySpecials();
		assertEquals("Vegas Bombs", fridaySpecials.get(0).getNameOfAlcohol());
		assertEquals("Three Olives Vodka Specialty Drinks", fridaySpecials.get(1).getNameOfAlcohol());
		assertEquals("Double Wells", fridaySpecials.get(2).getNameOfAlcohol());
	}

	@Test
	public void testTypeSortSpecials() {
		testSpecials.getBarFridaySpecials().sort(new SpecialTypeComparator());
		fridaySpecials = testSpecials.getBarFridaySpecials();
		assertEquals("Vegas Bombs", fridaySpecials.get(0).getNameOfAlcohol());
		assertEquals("Double Wells", fridaySpecials.get(1).getNameOfAlcohol());
		assertEquals("Three Olives Vodka Specialty Drinks", fridaySpecials.get(2).getNameOfAlcohol());
	}
}