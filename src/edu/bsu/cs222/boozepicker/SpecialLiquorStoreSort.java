package edu.bsu.cs222.boozepicker;

public class SpecialLiquorStoreSort {

	public void priceSortSpecials(SpecialLiquorStore specials) {
		specials.getStoreSpecials().sort(new SpecialPriceComparator());
	}
	
	public void typeSortSpecials(SpecialLiquorStore specials) {
		specials.getStoreSpecials().sort(new SpecialTypeComparator());
	}
}