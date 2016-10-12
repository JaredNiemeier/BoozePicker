package edu.bsu.cs222.boozepicker;

import java.util.Comparator;

public class SpecialTypeComparator implements Comparator<Special> {

	@Override
	public int compare(Special arg0, Special arg1) {
		return arg0.getTypeOfAlcohol().compareTo(arg1.getTypeOfAlcohol());
	}
}