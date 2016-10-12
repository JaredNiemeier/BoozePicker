package edu.bsu.cs222.boozepicker;

import java.util.ArrayList;

public class SpecialLiquorStore {

	private LiquorStore store;
	private ArrayList<Special> storeSpecials;

	public SpecialLiquorStore(LiquorStore storeName) {
		store = storeName;
		storeSpecials = store.getSpecialsList();
	}

	public SpecialLiquorStore(ArrayList<Special> list) {
		this.storeSpecials = list;
	}

	public ArrayList<Special> getStoreSpecials() {
		return storeSpecials;
	}
}