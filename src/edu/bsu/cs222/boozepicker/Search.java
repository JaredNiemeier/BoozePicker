package edu.bsu.cs222.boozepicker;

import java.util.ArrayList;

public class Search {

	private String keyword = "";
	private ArrayList<String> results = new ArrayList<String>();

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public ArrayList<String> searchSpecials(ArrayList<Special> specials) {
		results = new ArrayList<String>();
		for (int i = 0; i < specials.size(); i++) {
			Special special = specials.get(i);
			addToResults(special);
		}
		return results;
	}

	private void addToResults(Special special) {
		String specialType = special.getTypeOfAlcohol();
		if (isSpecialTypeEqualToKeyword(specialType)) {
			results.add(special.getNameOfAlcohol());
		}
	}

	private boolean isSpecialTypeEqualToKeyword(String specialType) {
		return specialType.equalsIgnoreCase(keyword);
	}
}