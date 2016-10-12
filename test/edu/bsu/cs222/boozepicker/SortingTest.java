package edu.bsu.cs222.boozepicker;

import static org.junit.Assert.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

import edu.bsu.cs222.boozepicker.Special;
import edu.bsu.cs222.boozepicker.SpecialPriceComparator;
import edu.bsu.cs222.boozepicker.SpecialTypeComparator;

public class SortingTest {
	private ArrayList<Special> specialsList;

	@Before
	public void setUp() {
		specialsList = new ArrayList<Special>();
		Special.Builder builder = new Special.Builder();
		builder.setNameOfAlcohol("Captain Morgan");
		builder.setPriceOfAlcohol("15.00");
		builder.setTypeOfAlcohol("Rum");
		specialsList.add(builder.build());
		builder.setNameOfAlcohol("Bud Light");
		builder.setPriceOfAlcohol("9.00");
		builder.setTypeOfAlcohol("Beer");
		specialsList.add(builder.build());
		builder.setNameOfAlcohol("Svedka");
		builder.setPriceOfAlcohol("7.00");
		builder.setTypeOfAlcohol("Vodka");
		specialsList.add(builder.build());
		builder.setNameOfAlcohol("Oliver Soft White");
		builder.setPriceOfAlcohol("7.50");
		builder.setTypeOfAlcohol("Wine");
		specialsList.add(builder.build());
	}

	@Test
	public void testPriceSortEvenNumber() {
		specialsList.sort(new SpecialPriceComparator());
		ArrayList<Special> result = specialsList;
		assertTrue(result.get(0).getPriceOfAlcohol().equals(new BigDecimal("7.00")));
		assertTrue(result.get(1).getPriceOfAlcohol().equals(new BigDecimal("7.50")));
		assertTrue(result.get(2).getPriceOfAlcohol().equals(new BigDecimal("9.00")));
		assertTrue(result.get(3).getPriceOfAlcohol().equals(new BigDecimal("15.00")));
	}

	@Test
	public void testPriceSortOddNumber() {
		Special.Builder builder = new Special.Builder();
		builder.setNameOfAlcohol("Jack Daniel's");
		builder.setPriceOfAlcohol("11.00");
		builder.setTypeOfAlcohol("Whiskey");
		specialsList.add(builder.build());
		specialsList.sort(new SpecialPriceComparator());
		ArrayList<Special> result = specialsList;
		assertTrue(result.get(0).getPriceOfAlcohol().equals(new BigDecimal("7.00")));
		assertTrue(result.get(1).getPriceOfAlcohol().equals(new BigDecimal("7.50")));
		assertTrue(result.get(2).getPriceOfAlcohol().equals(new BigDecimal("9.00")));
		assertTrue(result.get(3).getPriceOfAlcohol().equals(new BigDecimal("11.00")));
		assertTrue(result.get(4).getPriceOfAlcohol().equals(new BigDecimal("15.00")));
	}

	@Test
	public void testTypeSort() {
		specialsList.sort(new SpecialTypeComparator());
		ArrayList<Special> result = specialsList;
		assertEquals("Beer", result.get(0).getTypeOfAlcohol());
		assertEquals("Rum", result.get(1).getTypeOfAlcohol());
		assertEquals("Vodka", result.get(2).getTypeOfAlcohol());
		assertEquals("Wine", result.get(3).getTypeOfAlcohol());
	}
}