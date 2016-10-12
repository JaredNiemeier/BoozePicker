package edu.bsu.cs222.boozepicker;

public class SpecialBarSort {

	public void priceSortSpecials(SpecialBar specials) {
		specials.getBarMondaySpecials().sort(new SpecialPriceComparator());
		specials.getBarTuesdaySpecials().sort(new SpecialPriceComparator());
		specials.getBarWednesdaySpecials().sort(new SpecialPriceComparator());
		specials.getBarThursdaySpecials().sort(new SpecialPriceComparator());
		specials.getBarFridaySpecials().sort(new SpecialPriceComparator());
		specials.getBarSaturdaySpecials().sort(new SpecialPriceComparator());
		specials.getBarSundaySpecials().sort(new SpecialPriceComparator());
	}

	public void typeSortSpecials(SpecialBar specials) {
		specials.getBarMondaySpecials().sort(new SpecialTypeComparator());
		specials.getBarTuesdaySpecials().sort(new SpecialTypeComparator());
		specials.getBarWednesdaySpecials().sort(new SpecialTypeComparator());
		specials.getBarThursdaySpecials().sort(new SpecialTypeComparator());
		specials.getBarFridaySpecials().sort(new SpecialTypeComparator());
		specials.getBarSaturdaySpecials().sort(new SpecialTypeComparator());
		specials.getBarSundaySpecials().sort(new SpecialTypeComparator());
	}
}