package edu.bsu.cs222.boozepicker;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

import edu.bsu.cs222.boozepicker.Search;
import edu.bsu.cs222.boozepicker.Special;

public class SearchTest {

	private ArrayList<Special> specialsList;
	private Search searcher = new Search();

	@Before
	public void setUp() {
		specialsList = new ArrayList<Special>();
		Special.Builder builder = new Special.Builder();
		builder.setNameOfAlcohol("Captain Morgan");
		builder.setPriceOfAlcohol("15");
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
		builder.setNameOfAlcohol("Smirnoff");
		builder.setPriceOfAlcohol("13.00");
		builder.setTypeOfAlcohol("Vodka");
		specialsList.add(builder.build());
	}

	@Test
	public void testSearch() {
		searcher.setKeyword("Vodka");
		ArrayList<String> result = searcher.searchSpecials(specialsList);
		assertEquals("Svedka", result.get(0));
		assertEquals("Smirnoff", result.get(1));
	}
}