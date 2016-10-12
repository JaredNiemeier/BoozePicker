package edu.bsu.cs222.boozepicker;

import java.util.ArrayList;

public class SpecialBar {

	private Bar bar;
	private ArrayList<Special> barMondaySpecials;
	private ArrayList<Special> barTuesdaySpecials;
	private ArrayList<Special> barWednesdaySpecials;
	private ArrayList<Special> barThurdsdaySpecials;
	private ArrayList<Special> barFridaySpecials;
	private ArrayList<Special> barSaturdaySpecials;
	private ArrayList<Special> barSundaySpecials;

	public SpecialBar(Bar barName) {
		bar = barName;
		barMondaySpecials = bar.getDailySpecials("monday");
		barTuesdaySpecials = bar.getDailySpecials("tuesday");
		barWednesdaySpecials = bar.getDailySpecials("wednesday");
		barThurdsdaySpecials = bar.getDailySpecials("thursday");
		barFridaySpecials = bar.getDailySpecials("friday");
		barSaturdaySpecials = bar.getDailySpecials("saturday");
		barSundaySpecials = bar.getDailySpecials("sunday");
	}

	public ArrayList<Special> getBarMondaySpecials() {
		return barMondaySpecials;
	}

	public ArrayList<Special> getBarTuesdaySpecials() {
		return barTuesdaySpecials;
	}

	public ArrayList<Special> getBarWednesdaySpecials() {
		return barWednesdaySpecials;
	}

	public ArrayList<Special> getBarThursdaySpecials() {
		return barThurdsdaySpecials;
	}

	public ArrayList<Special> getBarFridaySpecials() {
		return barFridaySpecials;
	}

	public ArrayList<Special> getBarSaturdaySpecials() {
		return barSaturdaySpecials;
	}

	public ArrayList<Special> getBarSundaySpecials() {
		return barSundaySpecials;
	}
}