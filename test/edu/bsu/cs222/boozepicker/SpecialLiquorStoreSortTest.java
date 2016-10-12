package edu.bsu.cs222.boozepicker;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

import edu.bsu.cs222.boozepicker.LiquorStore;
import edu.bsu.cs222.boozepicker.Special;
import edu.bsu.cs222.boozepicker.SpecialLiquorStore;
import edu.bsu.cs222.boozepicker.SpecialPriceComparator;
import edu.bsu.cs222.boozepicker.SpecialTypeComparator;
import edu.bsu.cs222.boozepicker.XmlParser;

public class SpecialLiquorStoreSortTest {

	private SpecialLiquorStore testSpecials;
	private ArrayList<Special> specials;

	@Before
	public void setUp() {
		XmlParser parser = new XmlParser("Boozespecialstestfile.xml");
		LiquorStore muncieLiquors = parser.parseLiquorStores().get(0);
		testSpecials = new SpecialLiquorStore(muncieLiquors);
		specials = testSpecials.getStoreSpecials();
	}

	@Test
	public void testPriceSortSpecials() {
		testSpecials.getStoreSpecials().sort(new SpecialPriceComparator());
		specials = testSpecials.getStoreSpecials();
		assertEquals("Captain Morgan spiced rum", specials.get(0).getNameOfAlcohol());
		assertEquals("Smirnoff", specials.get(1).getNameOfAlcohol());
	}

	@Test
	public void testTypeSortSpecials() {
		testSpecials.getStoreSpecials().sort(new SpecialTypeComparator());
		specials = testSpecials.getStoreSpecials();
		assertEquals("Captain Morgan spiced rum", specials.get(0).getNameOfAlcohol());
		assertEquals("Smirnoff", specials.get(1).getNameOfAlcohol());
	}
}