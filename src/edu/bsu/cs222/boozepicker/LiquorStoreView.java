package edu.bsu.cs222.boozepicker;

import java.util.ArrayList;
import javafx.scene.control.TextArea;

public class LiquorStoreView {

	private ArrayList<Special> storeSpecials;
	private TextArea textArea = new TextArea();

	public LiquorStoreView(SpecialLiquorStore specials) {
		storeSpecials = specials.getStoreSpecials();
	}

	public TextArea getStoreTextArea() {
		String storeSpecialString = getStoresSpecialInfo(storeSpecials);
		textArea.setText(storeSpecialString);
		return textArea;
	}

	private String getStoresSpecialInfo(ArrayList<Special> specialsList) {
		String specialsInfo = "";
		for (int i = 0; i < specialsList.size(); i++) {
			specialsInfo += specialsList.get(i).getNameOfAlcohol() + " $" + specialsList.get(i).getPriceOfAlcohol()
					+ " " + specialsList.get(i).getTypeOfAlcohol() + "\n";
		}
		return specialsInfo;
	}

	public void setTextAreaEditable(boolean isEditable) {
		textArea.setEditable(isEditable);
	}
}