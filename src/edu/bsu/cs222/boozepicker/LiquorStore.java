package edu.bsu.cs222.boozepicker;

import java.util.ArrayList;

public class LiquorStore {

	private String nameOfStore = "";
	private ArrayList<Special> specialsList = new ArrayList<Special>();

	public LiquorStore(String name) {
		nameOfStore = name;
	}

	public String getStoreName() {
		return nameOfStore;
	}

	public void addSpecial(Special special) {
		specialsList.add(special);
	}

	public ArrayList<Special> getSpecialsList() {
		return new ArrayList<Special>(specialsList);
	}
}