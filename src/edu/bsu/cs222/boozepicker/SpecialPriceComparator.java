package edu.bsu.cs222.boozepicker;

import java.util.Comparator;

public class SpecialPriceComparator implements Comparator<Special> {

	@Override
	public int compare(Special firstSpecial, Special secondSpecial) {
		return firstSpecial.getPriceOfAlcohol().compareTo(secondSpecial.getPriceOfAlcohol());
	}
}