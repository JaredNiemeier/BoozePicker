package edu.bsu.cs222.boozepicker;

import java.util.ArrayList;
import javafx.scene.control.TextArea;

public class BarView {

	private ArrayList<Special> barMondaySpecials;
	private ArrayList<Special> barTuesdaySpecials;
	private ArrayList<Special> barWednesdaySpecials;
	private ArrayList<Special> barThursdaySpecials;
	private ArrayList<Special> barFridaySpecials;
	private ArrayList<Special> barSaturdaySpecials;
	private ArrayList<Special> barSundaySpecials;
	
	private TextArea textArea = new TextArea();

	public BarView(SpecialBar specials) {
		barMondaySpecials = specials.getBarMondaySpecials();
		barTuesdaySpecials = specials.getBarTuesdaySpecials();
		barWednesdaySpecials = specials.getBarWednesdaySpecials();
		barThursdaySpecials = specials.getBarThursdaySpecials();
		barFridaySpecials = specials.getBarFridaySpecials();
		barSaturdaySpecials = specials.getBarSaturdaySpecials();
		barSundaySpecials = specials.getBarSundaySpecials();
	}

	public TextArea getBarTextArea() {
		String barSpecialString = 
				"-------MONDAY-------\n" + getDaySpecials(barMondaySpecials) + "\n" + 
				"-------TUESDAY-------\n" + getDaySpecials(barTuesdaySpecials) + "\n" +
				"-------WEDNESDAY-------\n" + getDaySpecials(barWednesdaySpecials) + "\n" + 
				"-------THURSDAY-------\n" + getDaySpecials(barThursdaySpecials) + "\n" + 
				"-------FRIDAY-------\n" + getDaySpecials(barFridaySpecials) + "\n" + 
				"-------SATURDAY-------\n" + getDaySpecials(barSaturdaySpecials) + "\n" + 
				"-------SUNDAY-------\n" + getDaySpecials(barSundaySpecials) + "\n";
		textArea.setText(barSpecialString);
		return textArea;
	}

	private String getDaySpecials(ArrayList<Special> daySpecialsList) {
		String daySpecial = "";
		for (int i = 0; i < daySpecialsList.size(); i++) {
			daySpecial += daySpecialsList.get(i).getNameOfAlcohol() + " $" + daySpecialsList.get(i).getPriceOfAlcohol()
					+ " " + daySpecialsList.get(i).getTypeOfAlcohol() + "\n";
		}
		return daySpecial;
	}

	public void setTextAreaEditable(boolean isEditable) {
		textArea.setEditable(isEditable);
	}
}