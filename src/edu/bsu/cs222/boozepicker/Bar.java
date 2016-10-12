package edu.bsu.cs222.boozepicker;

import java.util.ArrayList;
import java.util.HashMap;

public class Bar {

	private enum Day {
		MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
	}

	private HashMap<Day, ArrayList<Special>> dayOfWeekSpecials = new HashMap<Day, ArrayList<Special>>();
	private Day currentDay;
	private String nameOfBar = "";

	public Bar(String name) {
		nameOfBar = name;
		for (Day d : Day.values()) {
			dayOfWeekSpecials.put(d, new ArrayList<Special>());
		}
	}

	public String getBarName() {
		return nameOfBar;
	}

	public void addSpecial(Special special) {
		dayOfWeekSpecials.get(currentDay).add(special);
	}

	public ArrayList<Special> getDailySpecials(String day) {
		return new ArrayList<Special>(dayOfWeekSpecials.get(Day.valueOf(day.toUpperCase())));
	}

	public void setCurrentDay(String day) {
		currentDay = Day.valueOf(day.toUpperCase());
	}
}